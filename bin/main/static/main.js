var welcomeMsg = 'Hello World!'

document.querySelector('h1').innerText = welcomeMsg


//fetch('/artists').then(resp => resp.json()).then(artists => console.log(artists));

var req = new XMLHttpRequest();
req.open('GET', '/artists');
req.onload = function(){
    if(req.status == 200){
        let respText
        console.log(req.responseText);
    }
}
req.onerror = function(){
    console.log('Oops');
}
req.send();

