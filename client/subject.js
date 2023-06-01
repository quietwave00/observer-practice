function subscribeButtonClickHandler(i) {
    console.log("Subscribing");
    let eventSource = new EventSource('http://localhost:8080/subscribe/' + i);

    
}

document.getElementById("subscribe-button1").addEventListener('click', function() {
    subscribeButtonClickHandler(1);
});

document.getElementById("subscribe-button2").addEventListener('click', function() {
    subscribeButtonClickHandler(2);
});

document.getElementById("subscribe-button3").addEventListener('click', function() {
    subscribeButtonClickHandler(3);
});

document.getElementById('upload-button').addEventListener('click', function() {
    fetch('http://localhost:8080/upload')
    .then((response) =>
        console.log("Success")
    );

    eventSource.onmessage = function(event) {
        const eventData = event.data;
        console.log(eventData);
    };
})
