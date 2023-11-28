package o1.tombadventure

import scala.collection.mutable.Map

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an “area” can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */

// Description for facing the other way added with a possible required Item to enter the area

class Area(var name: String, var requiredItemName: Option[String], var description: String, var descriptionBack: String):

  private var available = if requiredItemName.isEmpty then true else false // Tells if the area is available for the player.
  private val neighbors = Map[String, Area]()
  private var items = Vector[Item]()

  def canEnter = this.available
  // canUnlock tells if a player can unlock a specific area or not.
  def canUnlock(inventory: Map[String, Item], itemName: String): Boolean = inventory.contains(itemName) && requiredItemName.contains(itemName) 

  def unlock(itemName: String, inventory: Map[String, Item]): Boolean = // Unlocks the area and returns true if succesful
    if this.canUnlock(inventory, itemName) then
      this.available = true
      true
    else
      false

  def addItem(item: Item): Unit = this.items = this.items :+ item

  def contains(itemName:String): Boolean = this.items.exists(_.name == itemName)

  def removeItem(itemName: String): Option[Item] =
    val(remove, theRest) = this.items.partition(_.name == itemName)
    this.items = theRest
    remove.headOption



  /** Returns the area that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def neighbor(direction: String) = this.neighbors.get(direction)

  /** Adds an exit from this area to the given area. The neighboring area is reached by moving in
    * the specified direction from this area. */
  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor

  /** Adds exits from this area to the given areas. Calling this method is equivalent to calling
    * the `setNeighbor` method on each of the given direction–area pairs.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction
    * @see [[setNeighbor]] */
  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits


  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about exits and items. If there are no
    * items present, the return value has the form "DESCRIPTION\n\nExits available:
    * DIRECTIONS SEPARATED BY SPACES". If there are one or more items present, the return
    * value has the form "DESCRIPTION\nYou see here: ITEMS SEPARATED BY SPACES\n\nExits available:
    * DIRECTIONS SEPARATED BY SPACES". The items and directions are listed in an arbitrary order. */
  def fullDescription(headingIn: Boolean) = // Added a boolean to state if the player is heading in or not
    val items =
      if this.items.isEmpty then
        ""
      else
        "\nYou see here: " + this.items.map(_.name).mkString("\n")
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    if headingIn then  // Checks which descriptions to print.
      this.description + items + exitList
    else
      this.descriptionBack + items + exitList


  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name + ": " + this.description.replaceAll("\n", " ") + "You see here: " +  this.items.mkString(" ").take(150)

end Area

