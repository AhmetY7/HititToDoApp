function addJobConfig() {
    $.ajax({
        type:"GET",
        url: "/api/job/add-page",
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