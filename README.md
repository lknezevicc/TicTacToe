# TicTacToe

Implementation of the classic game Tic Tac Toe in Java and JavaFX, designed with a focus on simplicity and user experience. It features a simple user interface and [MiniMax algorithm](https://en.wikipedia.org/wiki/Minimax) under the hood, a decision-making algorithm used for optimization and game theory. This ensures that the computer opponent is challenging, making each game interesting and unpredictable.
<p align="center">
  <img width="330" alt="welcome-screen" src="https://github.com/lknezevicc/TicTacToe/assets/126179039/7df1713b-9d16-44f5-9b98-447d4d748fd8">
  <img width="320" alt="play-screen" src="https://github.com/lknezevicc/TicTacToe/assets/126179039/0b8ee9c8-a07e-40b3-bcae-b742ec308e13">
<p/>

## How to Run

To run this application, follow these steps:

1. Ensure you have the latest version of Java JDK (JDK 22) and JavaFX (22).

2. Clone the repository:
```bash
git clone https://github.com/lknezevicc/TicTacToe.git
```
3. Navigate into project directory.

4. Build the application using Maven:
```bash
mvn clean install
```
5. Run using this command:
```bash
java -jar target/TicTacToe-1.0-SNAPSHOT.jar
```
* NOTE: Please replace `TicTacToe-1.0-SNAPSHOT.jar` with the actual name of your generated jar file.

## Features

- **Play against the computer**: The game uses the MiniMax algorithm for AI, providing a challenging opponent for players.
- **Choose your symbol**: Players have the option to choose between 'X' or 'O' as their symbol before starting the game.
- **Score tracking**: The game keeps track of the number of wins and losses, allowing players to keep track of their performance over multiple games.
