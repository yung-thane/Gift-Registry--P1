//var welcomeMsg = 'Welcome to the Registry!';

//document.querySelector('h1').innerText = welcomeMsg;

var dupeSet = new Set();


fetch('/items').then(resp => resp.json()).then(items => {

document.querySelector('#items').innerHTML = listItems(items);
    }
);

let listItem = function(item){
    return '<p>' + item.id + ": " +item.name + '</p';
};

let listItems = function(json){
return `${json.map(listItem).join('\n')}`
};

function postItem(){
    let item = {
        "id": document.getElementById("id").value,
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



fetch('/cart').then(resp => resp.json()).then(items => {
    document.querySelector('#cart').innerHTML = listItems(items);
        }
    );
    

    function postCart(){
        let item = {
            "id": document.getElementById("id").value,
            "name": document.getElementById("name").value
        }
        console.log(item);
        if(!dupeSet.has(item.id)){
            fetch("/cart", {
                method: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(item)
            }).then((result) => {
                console.log("then")
                if (result.status != 200) {
                    console.log("if")
                    throw new Error("Bad Server Response");
                }
                console.log(JSON.stringify(result));
                fetch('/cart').then(resp => resp.json()).then(items => {
                    document.querySelector('#cart').innerHTML = listItems(items);
                    console.log("final")
                    dupeSet.add(item.id);
                    console.log('test in post 2 ' + JSON.stringify(item));
                    }
                );
            }).catch((error) => { console.log(error); })
            deleteItem();
        }
    }
    function deleteItem(){
        let item = {
            "id": document.getElementById("id").value,
            "name": document.getElementById("name").value
        }
        console.log('test in delete ' +item)
        fetch("/items", {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        }).then((result) => {
            console.log("then")
            if (result.status != 200) { 
                console.log("if")
                throw new Error("Bad Server Response");
            }
            console.log(JSON.stringify(result));
        }).catch((error) => { console.log(error); })
        fetch('/items').then(resp => resp.json()).then(items => {
            document.querySelector('#items').innerHTML = listItems(items);
            console.log("final")
            }
        );
    }

let button = document.querySelector('button');

button.addEventListener('mouseenter', function() {
    button.textContent = "Confirm Contribution!";
})
button.addEventListener('mouseout', function() {
    button.textContent = "Contribute Gift";
})