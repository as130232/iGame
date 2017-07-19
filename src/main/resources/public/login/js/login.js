$(document).ready(function ($) {
    $('#loginform').submit(function (event) {
        event.preventDefault();
        var data = 'username=' + $('#username').val() + '&password=' + $('#password').val();
        $.ajax({
            data: data,
            timeout: 1000,
            type: 'POST',
            url: '/login'
 
        }).done(function(data, textStatus, jqXHR) {
            var preLoginInfo = JSON.parse($.cookie('dashboard.pre.login.request'));
            window.location = preLoginInfo.url;
 
        }).fail(function(jqXHR, textStatus, errorThrown) {
            alert('Booh! Wrong credentials, try again!');
        });
        
        
//        $.ajax({
//            url: "/login",
//            type: "POST",
//            data: data,
//            dataType: "json",
//            async: false,
//            success: function (data) {
//                result = data.result;
//            },
//            error: function () {
//                alert("Wrong credentials, try again!");
//            }
//        });
        
    });
    
    
    
});