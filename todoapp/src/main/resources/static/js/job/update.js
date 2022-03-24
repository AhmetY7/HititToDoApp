function updateJob() {
    var job = [];
    var validation = true;
    job["id"] = document.getElementById("updateID").innerText;
    job["description"] = $("#updateDescription").val();
    job["status"] = parseInt($("#updateStatus").val());
    job["targetDate"] = $("#updateTargetDate").val();
    if (job.id === "") {
        validation = false;
    }
    if (job.description === "") {
        document.getElementById("add-description-error").innerHTML = "<p>* Required Information</p>";
        validation = false;
    }
    if (job.status === "Select Status") {
        document.getElementById("add-status-error").innerHTML = "<p>* Required Information</p>";
        validation = false;
    }
    if (job.targetDate === "") {
        document.getElementById("add-target-date-error").innerHTML = "<p>* Required Information</p>";
        validation = false;
    }
    if (validation) {
        $.ajax({
            type:"POST",
            contentType: "application/json",
            url: "/api/job/update",
            data: JSON.stringify({id:job.id,description:String(job.description), status:{id:job.status},targetDate:String(job.targetDate)}),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                list_jobs();
            },
            error: function (e) {

                console.log("ERROR : ", e);

            }
        });
    }
}

function getJobById(id) {
    $.ajax({
        type:"GET",
        url: "/api/status/list",
        cache: false,
        timeout: 600000,
        success: function (data) {
            var select = document.getElementById("updateStatus");
            select.innerText = null;
            for (index in data.data) {
                select.options[select.options.length] = new Option(data.data[index].name, data.data[index].id);
            }
            document.getElementById("updateID").innerText = id;
            if(id !== "") {
                $.ajax({
                    type:"POST",
                    contentType: "application/json",
                    url: "/api/job/find",
                    data: JSON.stringify({id:id}),
                    dataType: 'json',
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        document.getElementById("updateDescription").innerText = data.data.description;
                        document.getElementById("updateTargetDate").value = data.data.targetDate.split('T')[0];
                        const $select = document.querySelector('#updateStatus');
                        $select.value = data.data.status.id;
                    },
                    error: function (e) {
                        console.log("ERROR : ", e);
                    }
                });
            }
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });

}


function list_jobs() {
    $.ajax({
        type:"GET",
        url: "/api/job/list",
        cache: false,
        timeout: 600000,
        success: function (data) {
            document.body.innerHTML = '';
            document.body.innerHTML = data;
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}