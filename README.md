# cc3002-breakout

Breakout is an arcade game that consists of using a horizontal bar and a ball that
bounces off the walls of the screen, to hit bricks positioned on top of the
screen.

## Code used

The code given by Juan Pablo was used as the base code for this homework. Although some tweaks were given so that the game graphic controller could observe the game logic controller. Also a new brick was introduced, whose logic is explained in the next section, and a new brick class was created as a consecuence.

## Features

Additionally to the core basic game mechanics, 2 big features and 2 small features were implemented. 

Firstly, a new cracked state was to given to both wooden and metal bricks, at 2 hits left and 5 hits left respectively. Since I couldn't find 2 cracked sprites to metalbricks which maked sense, only 1 was used. 

As another big feature a new brick called GoldenBrick was introduced. The golden is present if there are more than half the bricks of the level, in the other case it dissapears. When a GoldenBrick is hit the goes automatically to the next level, if there are no levels left the game ends. There is only one GoldenBrick per level.

Now moving to small features, sound was added when bricks are hit. The sound varies depending of the type of brick.

Lastly, in order to show the remaining balls on the GUI. Images of the ball remaining were implemented in the bottom right corner of the screen.

## Code location

The game GUI has a lot methods and clases but can be divided in 3 main sections.

* The BreakoutGameFacotry class, used to create most entites seen in the interface. Most entites use controller components.
* The Control, is a package in the GUI which has a class which extends components for every entity in the game app that uses a controller.
* The BreakoutApp, is the class that represents the graphical controler of the game. Some auxiliary methods were used to have a clearer code, suck as: displayCurrentLevel to show the current level on the screen and clearGame to destroy all brick entitites. To handle user inputs the initInput method is used. Global variables are stored in the initGameVars method. InitPhysics is used to handle collisions. InitUI is used to display most of the text seen on screen. And finally update is used to handle the notifications of the game logic controler.

