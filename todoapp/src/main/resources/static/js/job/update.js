function updateJob(id) {
    var job = [];
    var validation = true;
    job["id"] = id
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
            cache: false,
            timeout: 600000,
            success: function (data) {
                document.getElementById("content").innerHTML = data
            },
            error: function (e) {

                console.log("ERROR : ", e);

            }
        });
    }
}

function getJobById(id) {
    $.ajax({
        type:"POST",
        contentType: "application/json",
        url: "/api/job/find",
        data: JSON.stringify({id:id}),
        cache: false,
        timeout: 600000,
        success: function (data) {
            document.getElementById("content").innerHTML = data
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}