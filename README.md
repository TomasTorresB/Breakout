# cc3002-breakout

Breakout is an arcade game that consists of using a horizontal bar and a ball that
bounces off the walls of the screen, to hit bricks positioned on top of the
screen.

## Implementation

Breakout has 3 main classes:
- Game: In charge of implementing the game logic
- Level: Which simulates the behaviour of a ingame level
- Brick: Which simulates the behaviour of a ingame brick

In general, the game keeps track of the player balls,current level and the number of points acquiered. It is worth noting that the current level has a reference to the next playable level, similar to a linked list, so in order to add a new level a recursion was neede.

In order for the game class to react according to the ingame changes such as brick destructions or end of a level, an observer pattern was used where the game has the role of observer and the levels with the bricks are the observable objects. Since level also has to react according to bricks it was given both an observable and observer design. 

## How to use

This proyect uses a facade type design where the class "HomewordTwoFacade" has a simple implementation of all relevant methods used in this game. Although the game is not playable yet due to lack of GUI tests were used to test the game logic.
When testing this proyect almost all code can be tested using the "HomewordTwoFacade" method as shown in "BigTestT2".
The testing class "NotBigTestT2" uses JUNIT tests in order to cover most of the code.

## Additional Notes

While testing initial settings in "BigTestT2" in line 48, my implementation for the starting level of a game uses a "null" object as  the next level reference since when starting, the next level is not yet added; but the test suggests that the next level should be the same level in order to pass the tests. Therefore, the constructor of "Level_0" was modified in order to only pass this test.

Initially another proyect was created and then merged with this maven proyect which lead to a lot of problems with the linked git repository. Which is why I opted to create a new branch for the proyect, sorry for the confusion.


