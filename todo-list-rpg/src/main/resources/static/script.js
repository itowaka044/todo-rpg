document.getElementById("player-name-btn").addEventListener("click", function() {
    const playerName = document.getElementById("player-name").value;
    const player = {
        playerName: playerName
    };
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(player)
    }

    const url = 'http://localhost:8080/player/create';

    fetch(url, options)
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

//-------------------------------------------

async function fetchQuests() {
    try {
        const response = await fetch('https://localhost:8080/quest/list-all'); 
        if (!response.ok) {
            throw new Error('error in found quests :(');
        }

        const quests = await response.json();
        displayQuests(quests);
    } catch (error) {
        console.error("error:", error);
    }
}

function displayQuests(quests) {
    const questList = document.getElementById("questList");
    questList.innerHTML = "";

    quests.forEach(quest => {
        const questItem = document.createElement("div");
        questItem.classList.add("quest-item");
        questItem.innerHTML = `
            <strong>${quest.questName}</strong> - <em>${quest.questType}</em><br>
            ${quest.questDesc}<br>
            <small>Valor: ${quest.questValue} | Atributo: ${quest.questAttributes}</small>
        `;
        questList.appendChild(questItem);
    });
}

fetchQuests();

console.log("ok");