# [Yahtzee](https://github.com/WTsaur/Yahtzee)
## Created by William Tsaur, Iris Pounsberry, Lucas Zavalia

## Compiling and Running
*Note: Ensure you have the latest version of Java installed* 
1. Open up a terminal or command prompt and go to the Yahtzee folder
2. Run `yahtzee.sh`
*If you cannot run yahtzee.sh then change the permissions of the file using `chmod +x yahtzee.sh`. Then try again.*
> This will remove any .class files, compile all .java files, 
> move to the parent directory, create a `hwx.jar` file, 
> and run `hwx.jar` with the following command: `java -jar hwx.jar`

## Yahtzee Rules
> Roll up to 3 times each turn to rack up the best possible score.
> Decide which dice combo you're going for.
> Then select where you would like to enter the score on the scorecard.

## Understanding the Scorecard
### Aces, Twos, Threes, Fours, Fives, Sixes
> These scores count and add only the number they represent.

### Upper section 35 Bonus
> You get a +35 point bonus if the total score for your upper section is greater than or equal to 63.

### 3 of a kind
> Any roll with 3 dice having the same number. The total of the roll is simply added up.

### 4 of a kind
> Any roll with 3 dice having the same number. The total of the roll is simply added up.

### Full House
> A roll with a pair of matching dice and a triplet of matching dice.
> This does not total up the dice and instead gives you 25 points.

### Small Straight
> A roll with 4 dice in a strict increasing order (ex: 3, 4, 5, 6).
> This does not total up the dice and instead gives you 30 points.

### Large Straight
> A roll with 5 dice in a strict increasing order (ex: 2, 3, 4, 5, 6).
> This does not total up the dice and instead gives you 40 points.

### Chance
> Any type of roll can be added up and placed in this score box.

### Yahtzee
> A roll where all the dice are the same number.
> This does not total up the dice and instead gives you 50 points.

### Bonus Yahtzee
> If you roll a yahtzee but have already marked off your yahtzee score box,
> then select the bonus yahtzee box to get 100 points added to your total.

## How To Play The Game!
### The Menu
> Add players by typing a player name into the text box and clicking `Add Player` or by hitting the `enter` key.
> Remove players by clicking on their name or remove all players from the list with the `Clear Players` button.
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
> `Select` a score if you think the red preview number is to your liking!
> `Mark as 0` if you don't like any of your options or if you don't think you'll get lucky with that particular type of roll.

![Scorecard](https://github.com/WTsaur/Yahtzee/blob/main/images/scorecardImg.png)

> Lastly, click the pause button in the top right corner if you would like to restart the game or quit the game!

![Pause Button](https://github.com/WTsaur/Yahtzee/blob/main/images/pauseImg.png)

![Pause Menu](https://github.com/WTsaur/Yahtzee/blob/main/images/pauseMenu.png)

### Saving the game
> To save your game, simply hit pause and click `Quit game`. This will prompt you with a question asking you if
> you would like to save your game or not.
> Then the next time you open the game, it will ask you if you would like to resume your saved game.

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
- Scorecard.java
- Yahtzee.java
- EndgameScreen.java

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
