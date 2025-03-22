document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.create-quest form');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // obrigatorio preencher o forms

        const formData = {
            questName: document.getElementById('questName').value,
            questDesc: document.getElementById('questDesc').value,
            questValue: parseInt(document.getElementById('questValue').value),
            questStatus: false,
            xpGained: (parseInt(document.getElementById('questValue').value) * 100), //xp da quest, depende do quest value
            questType: document.getElementById('questType').value
        };

        fetch('/quest/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('erro na resposta do servidor ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log('Missão cadastrada com sucesso!', data);
            form.reset();
            alert('Missão cadastrada com sucesso!');
            loadAllQuests();
        })
        .catch(error => {
            console.error('Erro ao criar missão:', error);
            alert('Erro ao cadastrar missão: ' + error.message);
        });
    });
});

function loadAllQuests() {
    fetch('/quest/list-all')
    .then(response => response.json())
    .then(data => {
        const questList = document.getElementById('questList');
        questList.innerHTML = '';
        // se não existir quest
        if (data.length === 0) {
            questList.innerHTML = '<p>Nenhuma missão cadastrada.</p>';
            return;
        }
        // cria uma div para cada missão
        data.forEach(quest => {
            const questElement = document.createElement('div');
            questElement.className = 'quest-item';
            questElement.innerHTML = `
                <h3>${quest.questName}</h3>
                <p>${quest.questDesc}</p>
                <p><strong>Nivel:</strong> ${quest.questValue}</p>
                <p><strong>Tipo:</strong> ${quest.questType}</p>
                <p><strong>XP ganho:</strong> ${quest.xpGained}</p>
            `;
            questList.appendChild(questElement);
        });
    })
    .catch(error => {
        console.error('erro ao encontrar quests:', error);
        document.getElementById('questList').innerHTML = '<p>Erro ao encontrar quests.</p>';
    });
}
loadAllQuests();
console.log("ok");