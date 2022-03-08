var welcomeMsg = 'Welcome to the registry page!'

document.querySelector('h1').innerText = welcomeMsg

fetch('/items').then(resp => resp.json()).then(items => {
        document.querySelector('body').innerHTML = listItems(items);
    }
);

let listItem = function(item) {
    return '<p>' + item.itemId + ": " + item.name + '</p>';
}

function listItems(json) {
    return `
        <div id="itemsList">
            ${json.map(listItem).join('\n')}
        </div>
    `
}

// function get(url) {
//     return new Promise(function (resolve, reject) {
//         var req = new XMLHttpRequest();
//         req.open('GET', url);
//         req.onload = function() {
//             if (req.status == 200) {
//                 resolve(req.response);
//             } else {
//                 reject(Error(req.statusText));
//             }
//         }
//         req.onerror = function() {
//             reject(Error("Network error"));
//         }
//         req.send();
//     });
// }

// get('/artists').then(function(response) {
//     console.log(JSON.parse(response));
// }, function (err) {

// })