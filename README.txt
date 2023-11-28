This game is a part of Ohjelmointi1 course (Aalto university). This is an adventure game to find an amulet from ancient tomb. The game should work on both AdventureGUI and AdventureTextUI but if it doesn't please try the other one.

There are some areas that you cannot enter without a specific object. You have to use objects to make some areas available.
When using the item, you have to face the direction of the area that you wish to use the item for. You can turn with command go 'direction'.

There are traps in some rooms of the tomb.

ChatGPT was used to spice up the descriptions of the areas.

New methods and attributes:
- Player: use, facingWhere, isDead, changeFacing, changeDirection and private vals facing and headingIn
- Area: unlock, canUnlock, canEnter and a private var available
- Descriptions change when the player gets the amulet and turns back
- In some areas a pice of equipment can be lost


And of course new actions and the methods have been embedded to the code.




