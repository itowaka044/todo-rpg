document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('.create-quest form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // verifica se a missão é diaria
        const isDaily = document.getElementById('questType').value === 'daily';

        const formData = {
            questName: document.getElementById('questName').value,
            questDesc: document.getElementById('questDesc').value,
            questDifficult: parseInt(document.getElementById('questDifficult').value),
            questStatus: false,
            xpGained: (parseInt(document.getElementById('questDifficult').value)) * 100,
            questType: document.getElementById('questTypeAttributes').value
        };

        if (isDaily) {
            formData.refresh = true;
            formData.lastComplete =  new Date();
        }

        const url = isDaily ? '/quest/create-daily' : '/quest/create';


        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na resposta do servidor ' + response.status);
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
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao buscar quests.');
        }
        return response.json();
    })
    .then(data => {
        const questList = document.getElementById('quest-list');
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
                <div class="quest-name">
                            <p>ID: ${quest.id}</p>
                            <h3>${quest.questName}</h3>
                            <button class="change-name">TROCAR</button>
                        </div>
                <p>${quest.questDesc}</p>
                <p><strong>Nivel:</strong> ${quest.questDifficult}</p>
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