$(document).ready(function() {

    let auth = null;
    $('#login-button').click(function (ev) {
        ev.preventDefault();
        let username = $('#username').val();
        let password = $('#password').val();

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
            localStorage.setItem("token", auth);
            window.location.href = "D:/Projects/NGPuppies/src/main/resources/templates/home.html";
    
        });
    });

});