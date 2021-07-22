# Yahtzee

## Compiling and Running
*Note: Ensure you have the latest version of Java installed* 
1. Open up a terminal or command prompt and go to the Yahtzee folder
2. Run `yahtzee.sh`
*If you cannot run yahtzee.sh then change the permissions of the file using `chmod +x yahtzee.sh`. Then try again.*
> This will remove any .class files, compile all .java files, 
> move to the parent directory, create a `hwx.jar` file, 
> and run `hwx.jar` with the following command: `java -jar hwx.jar`

## Yahtzee Rules
**[Click Here](https://www.ultraboardgames.com/yahtzee/game-rules.php)**

## How To Play The Game!
### The Menu
> Add players by typing a player name into the text box and clicking `Add Player` or by hitting the `enter` key.
> Remove all players from the list with the `Clear Players` button.
> Make sure you have at least 2 players (maximum of 10) and then hit the `Start Game` button to begin playing!

![Yahtzee Menu](https://github.com/WTsaur/Yahtzee/blob/main/images/menu.png)

### The Board
> A window will pop up letting you know whose turn it is.

![Turn Indicator](https://github.com/WTsaur/Yahtzee/blob/main/images/turnIndicator.png)

> Now it's time to play!

![Yahtzee Board](https://github.com/WTsaur/Yahtzee/blob/main/images/board.png)

> In the top left you can see whose turn it is and how many of their rolls they have used.

![Player Info](https://github.com/WTsaur/Yahtzee/blob/main/images/playerAndRollCount.png)

> Click the dice on the right hand side to make your first roll!

![Roll Dice](https://github.com/WTsaur/Yahtzee/blob/main/images/rollDice.png)

> Click on the dice to "lock" it in place so that they don't change while you re-roll some other dice!

![Lock Dice](https://github.com/WTsaur/Yahtzee/blob/main/images/lockDice.png)

> When you like what you have or have run out of rolls, head over to the scorecard!
> Here you can see a preview of what the score would look like on your card as well as your current totals based on your previous turns.
> `Select` a score if you think the number to the left of it is to your liking!
> `Mark as 0` if you don't like any of your options or if you don't think you'll get lucky with that particular type of roll.

![Scorecard](https://github.com/WTsaur/Yahtzee/blob/main/images/scorecardImg.png)

> Lastly, click the pause button in the top right corner if you would like to restart the game or quit the game!

![Pause Button](https://github.com/WTsaur/Yahtzee/blob/main/images/pauseImg.png)

![Pause Menu](https://github.com/WTsaur/Yahtzee/blob/main/images/pauseMenu.png)

## Extra Features Implemented
- Save/Load a game

## Credits
### Iris Pounsberry
- Authored the project wiki
- Drafted ScorecardPanel's scoring logic
- GameData.java
- SaveGame logic
- LoadGame logic
- EndGame logic

### Lucas Zavalia
- 

### William Tsaur
- Panel.java
- Board.java
- Menu.java
- Yahtzee.java
- Player.java
- Scorecard.java
- Constants.java
- DiceModel.java
- ScorecardPanel.java
- Implemented Dice rolling
- Implemented Player turns
- Designed and Implemented Menu, Board, and Scoreboard layout and logic
