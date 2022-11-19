package ca.finalfive.friendsup.models

/**
 * Game model for all the different games
 * @param id Id of the game
 * @param chatRoom array of messages sent by the users
 * @param gameMode A Game mode that the user can
 * this can be either `TRIVIA` | `PROMPT` | 'CAH' | 'WYR'
 * @param gameContent Content of the game being played
 * @param isGameStarted Boolean which defines whether the game has started or not
 * @param isGameEnded Boolean which defines whether the game has ended or not
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
    var isGameStarted: Boolean = false,
    // Cannot use Number because firebase does not support deserialization
    /**
     * Maximum number of player for each game
     */
    val maxMembers: Int = 0,
    /**
     * List of all the users inside a game
     */
    val members: List<GameUserMember> = listOf(),
    /**
     * States whether the game has ended or not
     */
    var isGameEnded: Boolean = false,
    /**
     * Friends added to the friendList queue
     */
    val addFriendList: List<String> = listOf(),
    /**
     * Number which keeps track of which gameContent question the
     * players are at
     */
    val gameProgress: Int = 0
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
    val questionOptions: List<GameQuestionOption> = listOf()
)

/**
 * Data class for the game question option
 * @param optionText content of the option which will be displayed to the user
 * @param selectedBy List of users who have selected the option
 */
data class GameQuestionOption(
    /**
     * Content of the question's option which will be displayed to the user
     */
    val optionText: String = "",

    /**
     * List of users who have selected the option
     */
    var selectedBy: List<String> = listOf()
)

/**
 * Details of all the users inside a game
 * @param username Username of the user
 * @param email Email of the user
 * @param icon Icon of the user
 */
data class GameUserMember(
    /**
     * Username of the user
     */
    val username: String = "",
    /**
     * Email of the user
     */
    val email: String = "",
    /**
     * Icon of the user
     */
    val icon: String = ""
)