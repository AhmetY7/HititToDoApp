function deleteJobById(id) {
    $.ajax({
        type:"POST",
        contentType: "application/json",
        url: "/api/job/delete",
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