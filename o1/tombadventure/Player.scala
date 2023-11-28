package o1.tombadventure

import scala.collection.mutable.Map

/** A `Player` object represents a player character controlled by the real-life user
  * of the program.
  *
  * A player object’s state is mutable: the player’s location and possessions can change,
  * for instance.
  *
  * @param startingArea  the player’s initial location */
class Player(startingArea: Area):

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var inventoryItems = Map[String, Item]()
  private var headingIn = true // Gives the direction that the player is heading. Helping to print different descriptions on the way out.
  private var facing = "south" // Gives the direction that the player is facing.

  def changeFacing(direction: String) = this.facing = direction // player is heading always to the direction she moves toward

  def get(itemName: String): String =
    this.currentLocation.removeItem(itemName) match
      case None => s"There is no ${itemName} here to pick up."
      case Some(item) =>
        this.inventoryItems += (item.name -> item)
        s"You pick up the ${itemName}."

  def drop(itemName: String): String =
     if this.inventoryItems.contains(itemName) then
      val item = this.inventoryItems(itemName)
      this.inventoryItems -= itemName
      this.currentLocation.addItem(item)
      s"You drop the ${itemName}."
     else
      "You don't have that!"

  def changeDirection = this.headingIn = !this.headingIn // Changes the direction of the player.

  def isHeadingIn = this.headingIn // returns if the player is heading in or not. For the desctiptions of areas.

  def use(itemName: String) =  // Uses the unlock method of Area class and returns true if success, false otherwise
    if (this.inventoryItems.contains(itemName)) then
      this.currentLocation.neighbor(this.facing) match
        case Some(destination) =>
          if destination.unlock(itemName, this.inventoryItems) then
            this.go(this.facing)
            s"You use the $itemName."
          else
            "This item cannot be used to this direction."
        case None =>
          "There is nothing to use this item for in this direction."
    else
      "You don't have that item."

  def examine(itemName: String):String =
    this.inventoryItems.get(itemName) match
      case None =>
        "If you want to examine something, you need to pick it up first."
      case Some(item) =>
        s"You look closely at the ${itemName}.\n${item.description}"

  def inventory: String =
     if this.inventoryItems.isEmpty then
      "You are empty-handed."
     else
      val items = this.inventoryItems.keys.mkString("\n")
      s"You are carrying:\n$items"

  
  def isDead = this.location.name == "Ambush Passage" // isDead method added in case the player dies

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  /** Returns the player’s current location. */
  def location = this.currentLocation

  def has(itemName: String): Boolean = this.inventoryItems.contains(itemName)

  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player’s current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String) =
    this.facing = direction
    this.location.neighbor(direction) match
      case Some(area) =>
        if area.canEnter then
          this.currentLocation = area
          s"You go $direction."
        else
          area.requiredItemName match
            case Some(name) => s"You need to use a ${name} to go ${direction} if you have one."
            case None => ""

      case None =>
        "You can't go " + direction + "."


  def facingWhere = this.facing // Returns the direction that the player is facing. For the compass command.
  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() =
    "You rest for a while. Better get a move on, though."


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() =
    this.quitCommandGiven = true
    ""

  /** Returns a brief description of the player’s state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

end Player

