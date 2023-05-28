document.getElementById("subscribe-button").addEventListener('click', function() {
    for(let i = 1; i <= 3; i++) {
        const eventSource = new EventSource('http://localhost:8080/subscribe/' + i);

        eventSource.addEventListener('message', (e) => {
            const message = e.data;
            console.log("message: "  + message);
        })
    }
});
    
document.getElementById("upload-button").addEventListener("click", function() {
    fetch('http://localhost:8080/upload', {
        method: "GET"
    })
    .then((response) => {
        return response.text();
    })
    .then((data) => {
        const msg = data;
    })
});