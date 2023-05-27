document.getElementById("subscribe-button").addEventListener('click', function() {
    for(let i = 1; i <= 3; i++) {
        console.log("subscribe run");
        fetch("http://localhost:8080/subscribe/" + i, {
            method: "GET",
            mode: "cors"
            })
        .then(console.log('complete'))
    }
});
    
document.getElementById("upload-button").addEventListener("click", function() {
    fetch('http://localhost:8080/upload', {
        method: "GET",
        mode: "cors"
    })
    .then((response) => response.json())
    .then((response) => {
        console.log(response);
    })
});