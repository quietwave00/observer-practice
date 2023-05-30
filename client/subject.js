function subscribeButtonClickHandler(i) {
    const eventSource = new EventSource('http://localhost:8080/subscribe/' + i);

    eventSource.addEventListener('message', (e) => {
        const message = e.data;
        console.log("message: " + message);
    });
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