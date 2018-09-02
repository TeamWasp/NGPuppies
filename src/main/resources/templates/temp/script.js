$(document).ready(function() {

    var auth = null;
    var role = null;
    $('#login-button').click(function (ev) {
        ev.preventDefault();
        var username = $('#username').val();
        var password = $('#password').val();

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/login',
            headers: {
                "Content-Type": "application/json"
            },
            data: JSON.stringify({
                "username": username,
                "password": password
            })
        }).done(function (body) {
            auth = body["token"];
            role = body["role"];
            localStorage.setItem("token", auth);
            localStorage.setItem("role", role);
        });
    });

});