$.ajax({
    url: "/api/users/count",
    type: "GET",
    dataType: "json",
    success: function (data) {
        $('#userCount').html("registered users count " + data)
    }
});