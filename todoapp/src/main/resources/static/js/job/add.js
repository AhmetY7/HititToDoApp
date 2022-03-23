function addJobConfig() {
    $.ajax({
        type:"GET",
        url: "/api/status/list",
        cache: false,
        timeout: 600000,
        success: function (data) {
            var select = document.getElementById("addStatus");
            select.innerText = null;
            for (index in data.data) {
                select.options[select.options.length] = new Option(data.data[index].name, data.data[index].id);
            }
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
function addJob() {
    var job = [];
    var validation = true;
    job["description"] = $("#addDescription").val();
    job["status"] = $("#addStatus").val();
    job["targetDate"] = $("#addTargetDate").val();
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
            url: "/api/job/add",
            data: JSON.stringify({description:String(job.description), status:{id:job.status},targetDate:String(job.targetDate)}),
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