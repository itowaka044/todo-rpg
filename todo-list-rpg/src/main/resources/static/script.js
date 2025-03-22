document.getElementById("player-name-btn").addEventListener("click", function() {
    const playerData = {
        playerName: document.getElementById("player-name").value
    };

    fetch('/player/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(playerData)
    })
        .then(response => {
            if (!response.ok) {
                response.text().then(text => console.log('server response:', text));
                throw new Error('network response was not ok');
            }
            if (response.status === 204 || response.headers.get('content-length') === '0') {
              return Promise.reject("json not loaded :( ");
            } else {
              return response.json();
            }
        })
        .then(data => {
            console.log("Data:", data);
            alert("player created with success!");
            window.location.href = "main-page.html"; 
        })
        .catch(error => {
            console.log("error:", error);
            alert("error in create player :(");
        });
});

console.log("ok");