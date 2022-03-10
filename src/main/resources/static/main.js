var welcomeMsg = 'Welcome to the Registry!';

document.querySelector('h1').innerText = welcomeMsg;

fetch('/items').then(resp => resp.json()).then(items => {
document.querySelector('#items').innerHTML = listItems(items);
}
);

let listItem = function(item){
    return '<p>' + item.itemId + ": " +item.name + '</p';
}


let listItems = function(json){
return `
    <div id="itemsList">
    ${json.map(listItem).join('\n')}
    </div>
`
}