document.addEventListener("DOMContentLoaded", async () => {
    const params = new URLSearchParams(window.location.search);
    const language = params.get("language");

    const gridSize = 5;
    const events = {
        "english": [
            "Jacob and Esau are born",
            "Jacob sells his soup for Esau's birthright",
            "Jacob tricks Isaac for his blessing",
            "Jacob goes to uncle Laban",
            "Jacob works 7 years",
            "Laban tricks Jacob to marry Leah",
            "Jacob marries Rachel",
            "Jacob works another 7 years",
            "Jacob goes back to his homeland"
        ], 
        "chinese" : [
            "雅各和以扫出生",
            "雅各用一碗汤换取以扫的长子名分",
            "雅各欺骗以撒获得祝福",
            "雅各去拉班叔叔家",
            "雅各工作七年",
            "拉班欺骗雅各娶了利亚",
            "雅各娶了拉结",
            "雅各又工作七年",
            "雅各回到自己的家乡"
        ]
    }
        
    let lastMoveStatus = "NA";
    let { board, startPosition: playerPos } = await initBoard(gridSize, gridSize, events[language]);
    renderBoard(gridSize, gridSize, board, playerPos);

    document.addEventListener("keydown", async (e) => {
        switch (e.key) {
            case "ArrowUp": await move("Up"); break;
            case "ArrowDown": await move("Down"); break;
            case "ArrowLeft": await move("Left"); break;
            case "ArrowRight": await move("Right"); break;
        }
    });

    function rerender (newPos, newScore, moveStatus) {
        // Update player position
        if ((playerPos.x != newPos.x) || (playerPos.y != newPos.y)) {
            const prevCell = document.getElementById(`cell-${playerPos.x}-${playerPos.y}`);
            // If player made the right move, then the board text should disappear
            if (lastMoveStatus == "CORRECT") {  board[playerPos.x][playerPos.y] = "";}
            prevCell.textContent = board[playerPos.x][playerPos.y] ?? "";
            prevCell.classList.remove("playerPos");

            // Update move status
            lastMoveStatus = moveStatus;

            const newCell = document.getElementById(`cell-${newPos.x}-${newPos.y}`);
            newCell.classList.add("playerPos");
            newCell.innerHTML = `<div>🙂</div><div>${board[newPos.x][newPos.y] ?? ""}</div>`;
        }
    
        // Update score
        const prevScore = document.getElementById("score");
        if (newScore != Number(prevScore.textContent)){
            prevScore.textContent = newScore;
        }
    }

    async function move(dir) {
        try {
            const response = await fetch('/game/update', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ action: dir })
            });
            const {position: newPos, score : newScore, isDone, lastMoveStatus : moveStatus} = await response.json();
            console.log("isDone: ", isDone);
            if (isDone) {
                const boardDiv = document.getElementById("score-container");
                boardDiv.innerHTML = `<h2>🎉 Congrats!</h2> <h3>You completed the game with a score of: ${newScore}</h3>`;
                boardDiv.classList.add("congrats");
                document.getElementById("board").style.display = "none";
                return; // Stop further rerendering
            }
            rerender(newPos, newScore, moveStatus);
            playerPos = newPos;
        } catch (error) {
            console.error('Error:', error);
            throw error;
        }
    }
});

function renderBoard(height, width, board, playerPos) {
    const grid = document.getElementById("board");

    for (let x = 0; x < height; x++) {
        for (let y = 0; y < width; y++) {
            const cell = document.createElement("div");
            cell.id = `cell-${x}-${y}`;
            cell.className = 'cell';

            const eventText = board[x][y] ?? "";
            if (x === playerPos.x && y === playerPos.y) {
                cell.classList.add("playerPos");
                cell.classList.remove("board");
                cell.innerHTML = `<div>🙂</div><div>${eventText}</div>`;
            } else {
                cell.textContent = eventText;
            }
            grid.appendChild(cell);
        }
    }
}

async function initBoard(height, width, events) {
    try {
        const response = await fetch('/game/init', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ height, width, events })
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
}