let count = 0

function add() {
    count++;
    document.getElementById("counter").innerHTML = count
}


function setContent() {
    let gridEl = document.getElementById("gridder")
    if (gridEl.style.display != "none") {
        gridEl.style.display = "none"
    } else {
        gridEl.style.display = "grid"
    }
}


let array = ["div", "body", "stickman"]

let listEl = document.getElementById("list")
for (let i = 0; i < array.length; i++) {
    let listItemEl = document.createElement("li")
    listItemEl.innerHTML = array[i]
    listEl.appendChild(listItemEl)
}