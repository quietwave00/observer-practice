window.onload = () => {
    let eventSource = new EventSource('http://localhost:8080/alerts/1');

    eventSource.addEventListener('message', function(e) {
        let message = e.data;
        console.log("message from Subject: " + message);
    });
}

