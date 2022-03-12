var welcomeMsg = 'Welcome to the Registry!';

document.querySelector('h1').innerText = welcomeMsg;

fetch('/items').then(resp => resp.json()).then(items => {
document.querySelector('#items').innerHTML = listItems(items);
    }
);

let listItem = function(item){
    return '<p>' + item.itemId + ": " +item.name + '</p';
};


let listItems = function(json){
return `${json.map(listItem).join('\n')}`
};

function postItem(){
    let item = {
        "itemId": document.getElementById("itemId").value,
        "name": document.getElementById("name").value
    }
    console.log(item);
    fetch("/items", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item)
    }).then((result) => {
        if (result.status != 200) {
            throw new Error("Bad Server Response");
        }
        console.log(result.text());
    }).catch((error) => { console.log(error); })
    fetch('/items').then(resp => resp.json()).then(items => {
        document.querySelector('#items').innerHTML = listItems(items);
        }
    );
}

let button = document.querySelector('button');
button.addEventListener('mouseenter', function() {
    button.textContent = "Go!";
})