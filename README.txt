This is an adventure game to find an amulet from ancient tomb. The game should work on both AdventureGUI and AdventureTextUI but if it doesn't please try the other one.

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

Game Walkthrough
Start at the Mystic Tomb Entrance (Outside):

Enter command: go south
This takes you to the Cryptic Foyer (Entrance).
In the Cryptic Foyer (Entrance):

Enter command: get torch
If the torch is an item here, you now have it in your inventory.
Enter command: go west
This takes you to the Forgotten Hall (Left Hallway).
In the Forgotten Hall (Left Hallway):

Enter command: use torch
Assuming using the torch illuminates the area or is required for visibility.
Enter command: get knife
If the knife is an item here, you now have it in your inventory.
Enter command: go east
This takes you back to the Cryptic Foyer (Entrance).
Back in the Cryptic Foyer (Entrance):

Enter command: go east
This takes you to the Root-Entwined Stairs (First Stairs).
In the Root-Entwined Stairs (First Stairs):

Enter command: use knife
Assuming using the knife clears the roots or is necessary to proceed.
Enter command: go east
This takes you to the Eternal Corridor (Middle of the Corridor).
In the Eternal Corridor (Middle of the Corridor):

Enter command: go east
This takes you to the Crossroads of Fate (End of Corridor).
In the Crossroads of Fate (End of Corridor):

You have two choices here:
Enter command: go south twice to reach the Amulet Chamber.
Alternatively, enter command: go north twice to reach the Hall of Shadows.
In the Amulet Chamber or Hall of Shadows:

Enter command: get amulet
Assuming the amulet is in either of these locations and is the main objective.
Retrace Your Steps to Exit:

Enter commands in sequence to return:
go south (twice if in the Amulet Chamber)
go west (three times to reach the Cryptic Foyer from the Crossroads of Fate)
Finally, go north to exit the tomb from the Cryptic Foyer, arriving at the Mystic Tomb Entrance (Outside).


