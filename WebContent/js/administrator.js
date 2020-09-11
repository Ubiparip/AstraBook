$(document).ready(function(){

	document.getElementById('searchDiv').style.visibility = "hidden";
	document.getElementById('sortirajRezervacije').style.visibility = "hidden";
	document.getElementById('statusRezervacije').style.visibility = "hidden";
	
	$('#odjava').click(logout());
	$('#pregledKorisnika').click(prikaziKorisnike());
	$('#pregledDomacina').click(prikaziDomacine());
	
	$('#apartmani').click(prikaziApartmane());
	$('#rezervacije').click(prikaziRezervacije());
	
	$('#pretrazi').click(provjeriPretragu());
	
	
	$('#inputDolazak').datepicker({              
		format: "yyyy-mm-dd"
	});
	$('#inputOdlazak').datepicker({
		format: "yyyy-mm-dd"
	});

})

function logout(){
	return function(event){
		event.preventDefault();
		
		$.ajax({
			url : '../AstraBook/rest/logout',
			type : 'GET',
			success : function(){
				alert('Vasa sesija je istekla!');
				window.location = './index.html';
			}
		});
	}
}

function prikaziKorisnike(){
	
	return function(event){
		event.preventDefault();

		$('#domaciniDIV').html('');
		$('#korisniciDIV').html('');
		$('#podaciDiv').html('');
		document.getElementById('searchDiv').style.visibility = "hidden";
		document.getElementById('sortirajRezervacije').style.visibility = "hidden";
		document.getElementById('statusRezervacije').style.visibility = "hidden";
		
		$.ajax({
			url: 'rest/user/korisnik',
			type: 'GET',
			contentType : 'application/json',
			success : function(users){
				//for(let user of users){
					ispisiKorisnike(users);
				//}
			}
		});
	}
}
