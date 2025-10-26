# 🌍 MemoryGame

## 🧭 Overview

MemoryGame is an educational game framework built with Java and Spring Boot.
Players experience key life events from different stories in chronological order — earning points for correct choices and losing points for incorrect ones.

Currently, the system runs entirely in-memory, using hardcoded story data for Jacob’s Journey. 
Features supported:
- Bilingual gameplay – English & Chinese support
- Score tracking – Gain/lose points based on player's move
- Clean MVC design
- Frontend – Lightweight HTML/CSS interface with JS event handling

See `Next Steps` for a discussion of what I hope to do.


## 🖥️ Tech Stack

| Layer                | Technology                  |
| -------------------- | --------------------------- |
| **Backend**          | Java 17+, Spring Boot 3.x   |
| **Frontend**         | HTML, CSS, JavaScript       |
| **Build Tool**       | Maven                       |
| **Architecture**     | MVC     |
| **Persistence**      | In-memory (no database yet) |


### 🧩 Current Features
- Single story model
- Bilingual gameplay – English & Chinese support
- Score tracking – Gain/lose points based on player's move
- Clean MVC design – Ready for data layer or service expansion
- Frontend – Lightweight HTML/CSS interface with JS event handling

## 🚀 Running the Application
#### Prerequisites
1. Java 17+
2. Maven

Run Command
```bash
mvn spring-boot:run
```

Then visit:
```bash
http://localhost:8080/
```


### 🎮 Gameplay Flow
1. Choose a language (English or 中文)
2. Redirects to `game.html?language=english` or `?language=chinese`
3. Uses arrow keys to move Jacob (🙂) across the board
4. The backend checks if the move is correct and updates the score
5. Once all events are completed, the backend returns the final result

## 🏗 Project Structure (Current)
MemoryGame is currently a modular monolith built with Spring Boot.
```
memorygame/
├── MemorygameApplication.java       # Main Spring Boot application
├── controller/
│   └── GameController.java         # Handles REST endpoints for the game
├── model/
│   ├── Board.java                  # Board representation
│   ├── BoardState.java             # Tracks current state of the board
│   ├── MoveRequest.java            # Player move request payload
│   ├── Position.java               # Coordinates for board positions
│   ├── BoardConfig.java            # Board setup/configuration
│   ├── Events.java                 # Hardcoded story events (currently Jacob)
│   ├── Player.java                 # Player data model (in-memory)
│   └── Status.java                 # Game or move status info
└── service/
    └── GameLogic.java              # Core game logic (scoring, validation, moves)
```

### 🧩 Next Steps

A future structure could look like this:

```
memorygame/
├── engine/           # GameLogic
├── story/            # StoryService, Events, StoryController
├── player/           # PlayerService, PlayerController
├── controller/       # Shared API endpoints
├── model/            # Data models
└── config/           # Spring Boot configuration, CORS, beans, etc.
```

#### Future Modules / Services

1. Story Module – manages stories and events; replace hardcoded Events.java with dynamic fetching.
2. Player Module – tracks player progress and scores (currently in-memory).
3. Frontend – HTML/CSS/JS UI, already fetching data from the backend.
4. Analytics Service – tracks gameplay metrics (time, performance, engagement); could become a separate service later.
5. Story Builder / Admin Service – allows educators or creators to add new stories dynamically; also a potential separate service in the future.