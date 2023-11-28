package o1.tombadventure

/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of “hard-coded” information that pertains to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class TombAdventure:

  val title = "An adventure inside a tomb to find a legendary hidden amulet."


  private val outside = Area("Mystic Tomb Entrance", None,
    "Before you lies an ancient tomb, its entrance a blend of foreboding shadows and intricate carvings. The air is thick with the scent of history and mystery. Do you dare to step into the unknown?",
    "Emerging from the tomb, the outside world feels unusually bright and alive. The experience inside lingers like a distant memory.")
  private val entrance = Area("Cryptic Foyer", None,
    "Stepping inside, you are greeted by a damp, chilling breeze. The corridor ahead forks into darkness. To the right, gnarled tree roots obstruct the path. An unlit torch hangs on the wall, a silent invitation to deeper exploration.",
    "Your hands brush against the cold stone walls as you fumble in the darkness. A faint outline of the exit looms ahead, a sliver of light guiding your way.")
  private val leftHallway = Area("Forgotten Hall", Some("torch"),
    "You find yourself in a vast, doorless hallway, silent and unyielding. The air is thick with dust and secrets. Something glints on the floor in the dim light.",
    "In the oppressive darkness, the hallway seems endless, each step echoing into nothingness.")
  private val firstStairs = Area("Root-Entwined Stairs", Some("knife"),
    "Using the knife, you slice through the thick roots blocking your path. The stairs descend into colder, damper depths. A shiver runs down your spine as you venture downward to a seemingly endless corridor.",
    "Ascending the stairs feels heavier this time, as if the tomb itself is reluctant to let you go.")
  private val middleOfTheCorridor = Area("Eternal Corridor", None,
    "The corridor stretches on, its end lost in shadows. To your left, an opening beckons with unknown promises, while the corridor itself continues into the abyss.",
    "Trudging back through the corridor, you feel a sense of déjà vu, each step a repeat of another.\nYou feel a doorway on your right and the corridor continues straight. You try to remember the direction you came from.")
  private val leftOfCorridor = Area("Ambush Passage", None,
    "As you step forward, the illusion of safety shatters. The floor gives way beneath you, plunging you into darkness. You land amidst a tangle of what feels horribly like... snakes! A fatal mistake.",
    "")
  private val endOfCorridor = Area("Crossroads of Fate", None,
    "The corridor concludes with a decision: stairs descend both left and right, each path veiled in secrecy. Which way will your destiny unfold?",
    "In the shadowy return, each stair feels like a step back in time, the echoes of your choices still lingering in the air.\nYou find yourself to be in a familiar feeling crossroad.")
  private val toTheLeft = Area("Constricting Passage", Some("torch"),
    "The corridor narrows, the walls closing in. Claustrophobia grips you as you squeeze through, unsure if the path will grant passage or become a stone tomb.",
    "Returning through the narrowing path, a sense of urgency hastens your steps, the walls seemingly inching closer.")
  private val keptGoingLeft = Area("Hall of Shadows", Some("torch"),
    "The passage opens into a dark hall. The torchlight fades to a mere glimmer. Suddenly, the ground vanishes beneath you, sending you sliding into darkness, you lose the torch and crash against something solid and ancient. It's a coffin, and atop it, the amulet!",
    "Blindly groping in darkness, your memory is your only guide, each step a silent prayer for escape.")
  private val toTheRight = Area("Stairway of Whispers", None,
    "The stairs spiral downward, each step echoing with hushed whispers of the past. At the bottom lies a door, old and forbidding. Does salvation or peril lie beyond?",
    "Your ascent is accompanied by faint whispers, as if the tomb itself is recounting tales of those who walked these steps before.")
  private val amuletChamber = Area("Amulet Chamber", None,
    "You step into a grand chamber, its walls echoing with the whispers of time. In the center, on a pedestal, lies the coveted amulet, gleaming in the dim light.\nAs you approach, the torch in your hand sputters its final breath, the flame dwindling into obscurity. Realizing its light will no longer guide your path, you gently lay it to rest on the cold stone floor, its purpose fulfilled.",
    "In the darkness, you feel the familiar contours of the chamber, the pedestal where the amulet once rested now a mere shadow.")
  private val doorInTheEnd = Area("Archway of Decisions", None,
    "With a deep breath, you open the door. An arrow whizzes past, a deadly warning. The path forward is fraught with danger. You cautiously retreat, closing the door on its silent threat.",
    "You open the door. An arrow whizzes past, a deadly warning. The path forward is fraught with danger. You cautiously retreat, closing the door on its silent threat.")

  outside.setNeighbors(Vector("south" -> entrance))
  entrance.setNeighbors(Vector("east" -> leftHallway, "west" -> firstStairs, "north" -> outside))
  leftHallway.setNeighbors(Vector("west" -> entrance))
  firstStairs.setNeighbors(Vector("west" -> middleOfTheCorridor, "east" -> entrance))
  middleOfTheCorridor.setNeighbors(Vector("east" -> firstStairs, "south" -> leftOfCorridor, "west" -> endOfCorridor))
  endOfCorridor.setNeighbors(Vector("south" -> toTheLeft, "north" -> toTheRight, "east" -> middleOfTheCorridor))
  toTheLeft.setNeighbors(Vector("north" -> endOfCorridor, "south" -> keptGoingLeft))
  toTheRight.setNeighbors(Vector("south" -> endOfCorridor, "north" -> amuletChamber))
  keptGoingLeft.setNeighbors(Vector("north" -> doorInTheEnd, "south" -> toTheRight))
  amuletChamber.setNeighbors(Vector("north" -> doorInTheEnd, "south" -> toTheRight))
  doorInTheEnd.setNeighbors(Vector("south" -> toTheRight))

  this.entrance.addItem(Item("torch", "This should help. Luckily you brought matches with you. You light it."))
  this.leftHallway.addItem(Item("knife", "You pick up the knife. It feels sharp regardless of the rust on it."))
  this.keptGoingLeft.addItem(Item("amulet", "A legendary amulet. You can feel the beauty of it but is it worth all this stress?"))
  this.amuletChamber.addItem(Item("amulet", "A legendary amulet. You can feel the beauty of it but is it worth all this stress?"))
   /** The character that the player controls in the game. */
  val player = Player(outside)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 40

  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = this.player.location == this.outside && this.player.has("amulet") // Game is complete when the player is outside with amulet.

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit || this.player.isDead // Added isDead option

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "You are a treasure hunter. A legendary one.\nYou have come to find a mysterious amulet before your rival, notorious Ms. Oganessian seeks it before you and kills everyone in the way.\nYou have your dear neon-compass in your pocket to tell you where you are facing when wandering inside the dark tomb.\nWith command 'help' you can see all the possible commands.\nThere are some areas that you cannot enter without a specific object. You have to use objects to make some areas available.\nWhen using the item, you have to face the direction of the area that you wish to use the item for. You can turn with command go 'direction'."

  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage =
    if this.isComplete then
      "You made it! Better leave the area before Ms. Oganessian gets here..."
    else if this.turnCount == this.timeLimit then
      "Oh no! Time's up. Starved of entertainment, you collapse and weep like a child.\nGame over!"
    else if this.player.isDead then // game over due to player dying
      "No exits since you died. Unlucky, game over. Try again!"
    else  // game over due to player quitting
      "Quitter!"

  /** Plays a turn by executing the given in-game command, such as “go west”. Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  // If a player goes to the "Hall of Shadows" loses she her torch
  def playTurn(command: String) =
     // to make sure not to go trough the last loops for nothing.
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    if this.player.isDead then
      s"${this.player.location.fullDescription}"
    else
      var outcome = outcomeReport.getOrElse(s"Unknown command: \"$command\".")
      if (this.player.location == this.amuletChamber) && (this.player.has("amulet")) && this.player.isHeadingIn then

        this.player.drop("torch")  // Player loses torch
        this.keptGoingLeft.removeItem("torch")
        this.player.changeDirection  // Player is now heading out of the tomb and different descriptions come up.
        this.keptGoingLeft.removeItem("amulet") // Making sure that there are not 2 amulets available for the player.
      else if (this.player.location == this.keptGoingLeft && this.player.has("amulet")) && this.player.isHeadingIn then

        this.player.drop("torch") // player loses torch
        this.keptGoingLeft.removeItem("torch")
        this.player.changeDirection  // Player is now heading out of the tomb and different descriptions come up.
        this.amuletChamber.removeItem("amulet")  // Making sure that there are not 2 amulets available for the player.
      if this.player.location == this.toTheLeft && !this.player.isHeadingIn then
        this.player.go("north")
        outcome = "That may not be the direction you came from.. You take out the compass and face towards north. But that is where the Amulet Chamber is. Try to remember how you got here.."
      outcome





end TombAdventure
/*class Adventure:

  /** the name of the game */
  val title = "A Forest Adventure"

  private val middle      = Area("Forest", "You are somewhere in the forest. There are a lot of trees here.\nBirds are singing.")
  private val northForest = Area("Forest", "You are somewhere in the forest. A tangle of bushes blocks further passage north.\nBirds are singing.")
  private val southForest = Area("Forest", "The forest just goes on and on.")
  private val clearing    = Area("Forest Clearing", "You are at a small clearing in the middle of forest.\nNearly invisible, twisted paths lead in many directions.")
  private val tangle      = Area("Tangle of Bushes", "You are in a dense tangle of bushes. It's hard to see exactly where you're going.")
  private val home        = Area("Home", "Home sweet home! Now the only thing you need is a working remote control.")
  private val destination = home

  middle     .setNeighbors(Vector("north" -> northForest, "east" -> tangle, "south" -> southForest, "west" -> clearing   ))
  northForest.setNeighbors(Vector(                        "east" -> tangle, "south" -> middle,      "west" -> clearing   ))
  southForest.setNeighbors(Vector("north" -> middle,      "east" -> tangle, "south" -> southForest, "west" -> clearing   ))
  clearing   .setNeighbors(Vector("north" -> northForest, "east" -> middle, "south" -> southForest, "west" -> northForest))
  tangle     .setNeighbors(Vector("north" -> northForest, "east" -> home,   "south" -> southForest, "west" -> northForest))
  home       .setNeighbors(Vector(                                                                  "west" -> tangle     ))

  // TODO: Uncomment the two lines below. Improve the code so that it places the items in clearing and southForest, respectively.
  this.clearing.addItem(Item("battery", "It's a small battery cell. Looks new."))
  this.southForest.addItem(Item("remote", "It's the remote control for your TV.\nWhat it was doing in the forest, you have no idea.\nProblem is, there's no battery."))

  /** The character that the player controls in the game. */
  val player = Player(middle)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 40


  /** Determines if the adventure is complete, that is, if the player has won. */
  def isComplete = this.player.location == this.destination && this.player.has("battery") && this.player.has("remote")

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "You are lost in the woods. Find your way back home.\n\nBetter hurry, 'cause Scalatut elämät is on real soon now. And you can't miss Scalkkarit, right?"


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage =
    if this.isComplete then
      "Home at last... and phew, just in time! Well done!"
    else if this.turnCount == this.timeLimit then
      "Oh no! Time's up. Starved of entertainment, you collapse and weep like a child.\nGame over!"
    else  // game over due to player quitting
      "Quitter!"


  /** Plays a turn by executing the given in-game command, such as “go west”. Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

end Adventure
*/


