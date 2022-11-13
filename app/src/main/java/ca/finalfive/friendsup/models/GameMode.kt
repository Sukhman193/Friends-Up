package ca.finalfive.friendsup.models

/**
 * Game modes available for the games
 */
class GameMode {
    companion object {
        /**
         * Trivia game mode
         */
        const val TRIVIA = "TRIVIA"

        /**
         * Would you rather game mode
         */
        const val WOULD_YOU_RATHER = "WYR"

        /**
         * Prompt game mode
         */
        const val PROMPT = "PROMPT"

        /**
         * Cards against humanity game mode
         */
        const val CARDS_AGAINST_HUMANITY = "CAH"

        /**
         * Get the firebase collection name for the game
         * @param gameMode Mode of the game being played
         * @throws Error if gameMode is invalid
         */
        fun getGameCollection(gameMode: String): String {
            if (gameMode == TRIVIA ||
                gameMode == PROMPT ||
                gameMode == WOULD_YOU_RATHER ||
                gameMode == CARDS_AGAINST_HUMANITY) {
                // Return the game which looks like `Trivia_Game`
                return "${gameMode.lowercase().replaceFirstChar { char -> char.uppercase() }}_Game"
            // if game is neither of them than throw an error
            } else {
                throw IllegalArgumentException("Game mode is invalid")
            }
        }
    }
}