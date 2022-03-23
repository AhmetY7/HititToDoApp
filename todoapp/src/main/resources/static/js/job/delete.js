var willDeletedId;
function deleteJob() {
    if(willDeletedId !== "") {
        $.ajax({
            type:"POST",
            contentType: "application/json",
            url: "/api/job/delete",
            data: JSON.stringify({id:willDeletedId}),
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
    willDeletedId="";
}

function deleteJobById(id) {
    willDeletedId = id;
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