package ca.finalfive.friendsup.models

/**
 * Game model for all the different games
 * @param id Id of the game
 * @param chatRoom array of messages sent by the users
 * @param gameMode A Game mode that the user can
 * this can be either `TRIVIA` | `PROMPT` | 'CAH' | 'WYR'
 * @param gameContent Content of the game being played
 * @param gameStarted Boolean which defines whether the game has started or not
 * @param maxMembers Maximum number of players for the game
 * @param members List of all the members in the game
 */
data class Game(
    /**
     * Id of the game
     */
    val id: String?,
    /**
     * Array of messages sent by the users
     */
    val chatRoom: List<Chat> = listOf(),
    /**
     * Game mode selected
     */
    val gameMode: String = "",
    /**
     * List of the game's content for each game
     * max length = 5
     */
    val gameContent: List<GameModeContent> = listOf(),
    /**
     * Boolean which defines whether the game has started or not
     */
    val gameStarted: Boolean = false,
    // Cannot use Number because firebase does not support deserialization
    /**
     * Maximum number of player for each game
     */
    val maxMembers: Int = 0,
    /**
     * List of all the usernames for the players inside the game
     */
    val members: List<String> = listOf()
) {
    // Constructor required by the firestore deserialization
    constructor(): this(null)
}

/**
 * Content of the Games
 * @param mainQuestion is going to be the main part of the Game
 * @param questionOptions array of any options needed by the game
 */
data class GameModeContent(
    /**
     * Main question is going to be the main topic
     * For Trivia it's the question asked
     * For Prompt it's going to be the prompt message
     * For Would You Rather it's going to equal to "Would you rather"
     * For Cards against humanity it's going to be the content of the black card
     */
    val mainQuestion: String = "",
    /**
     * Options needed by any game
     * For Trivia it's the multiple options in the trivia
     * For Prompt it's an empty list
     * For Would You Rather it's an array of 2 with the different options
     * For Cards against humanity it's going to be the content of the white card
     */
    val questionOptions: List<String> = listOf()
)
