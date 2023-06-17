window.onload = () => {
   let eventSource = new EventSource('http://localhost:8080/alerts/1');

    eventSource.addEventListener('message', function(e) {
        let message = e.data
        console.log("message from Subject: " + message);
        showMessage(message);
    });
}

function showMessage(message) {
    document.getElementById('msg-from-subject-div').innerHTML += 
        `
            <h4>${message}</h4>
        `;

};
