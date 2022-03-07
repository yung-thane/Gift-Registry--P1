var welcomeMsg = 'Welcome to the Grand Exchange'

document.querySelector('h1').innerText = welcomeMsg


//fetch('/artists').then(resp => resp.json()).then(artists => console.log(artists));

var req = new XMLHttpRequest();
req.open('GET', '/items');
req.onload = function(){
    if(req.status == 200){
        let respText = req.responseText;
        let items = JSON.parse(respText);
        console.log(req.responseText);
    }
}
req.onerror = function(){
    console.log('Oops');
}
req.send();

