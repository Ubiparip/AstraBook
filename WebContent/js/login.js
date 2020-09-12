$(document).ready(function(){
	
	$('#loginForm').submit( (event)=>{
		event.preventDefault();
				
		var userName = $('#loginUserName').val();
		let password = $('#loginPassword').val();
		if(userName == "" || userName == undefined || userName == null){
			document.getElementById("alertUsername").innerHTML = "Niste uneli korisnicko ime";
			document.getElementById("alertPassword").innerHTML = "";
		}else if(password == "" || password == undefined || password == null){
			document.getElementById("alertUsername").innerHTML = "";
			document.getElementById("alertPassword").innerHTML = "Niste uneli lozinku";
		}else{
			var obj = {"userName":userName, "password" : password};
			console.log(obj);
			
			$.ajax({
	        	contentType: 'application/json',
	            url: 'rest/login',
	            type : 'POST',
	            data: JSON.stringify(obj),
	            success: function(response){
	            	if(response==null){
	            		console.log('NULL');
	            		alert('Pogresno Korisnicko Ime ili Lozinka.');
	            	}else{
	            		console.log('Success in login!');
	            		
	            		if(response.uloga == 0){
	            			window.location = './korisnik.html';
	            		}else if(response.uloga == 1){
	            			window.location = './administrator.html';
	            		}else if(response.uloga == 2){
	            			window.location = './domacin.html';
	            		}
	            		
	            	}

	              }
	          });
		}
		
		
		
	  }); 
});