$(document).ready(function($) {
	
	var user = getUsers();
	$("#helloweenMessage").html(user.email);
	
//    $.ajax({
//        type: 'GET',
//        url: '/hello'
// 
//    }).done(function (data, textStatus, jqXHR) {
//        $('#helloweenMessage').html(data.message);
// 
//    }).fail(function (jqXHR, textStatus, errorThrown) {
//        if (jqXHR.status === 401) { // HTTP Status 401: Unauthorized
//            var preLoginInfo = JSON.stringify({method: 'GET', url: '/'});
//            $.cookie('restsecurity.pre.login.request', preLoginInfo);
//            window.location = '/login.html';
// 
//        } else {
//            alert('Houston, we have a problem...');
//        }
//    });
    
    
    
    
    function getUsers(){
    	var result = [];
		$.ajax({
		   url: "/loginUser",
		   type: "GET",
		   dataType: "json",
		   async: false,
		   success: function (data) {
			   result = data;
		   },
		   error: function () {
		       alert("Wrong credentials, try again!");
		   }
		});
		return result;
    }
});