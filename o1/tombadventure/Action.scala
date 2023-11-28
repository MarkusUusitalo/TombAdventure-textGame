package o1.tombadventure

/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as “go east” or “rest” */
class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as “You go west.”). The description is returned in an `Option`
    * wrapper; if the command was not recognized, `None` is returned. */
  def execute(actor: Player) = this.verb match
    case "go"        => Some(actor.go(this.modifiers))
    case "rest"      => Some(actor.rest())
    case "xyzzy"     => Some("The grue tastes yummy.")
    case "quit"      => Some(actor.quit())
    case "get" => Some(actor.get(this.modifiers))
    case "drop" => Some(actor.drop(this.modifiers))
    case "examine" => Some(actor.examine(this.modifiers))
    case "inventory" => Some(actor.inventory)
    case "use" => Some(actor.use(this.modifiers))
    case "compass" => Some("You are facing " + actor.facingWhere)
    case "help"      => Some(
      "Commands to play the game:\n" +
      " 'go [direction]' - Move in the specified direction (e.g., 'go west')\n" +
      " 'rest' - Take a rest (no significant effect in game terms)\n" +
      " 'get [item]' - Pick up an item (e.g., 'get torch')\n" +
      " 'drop [item]' - Drop an item from your inventory (e.g., 'drop knife')\n" +
      " 'examine [item]' - Examine an item in your inventory (e.g., 'examine amulet')\n" +
      " 'inventory' - Check the items you are carrying\n" +
      " 'quit' - Quit the game\n" +
      " 'xyzzy' - A magical command with a mysterious effect\n" +
      " 'use [item]' - You can use the item to enter new areas.\n" +
      " 'compass' - See the direction that you are currently facing towards.\n" +
      " 'help' - Show this help message")  // I used chatGBT to come up with the help description text part only



    case other       => None

  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString = s"$verb (modifiers: $modifiers)"

end Action

