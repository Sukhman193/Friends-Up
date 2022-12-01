# Friends Up
> Friends Up is an Android application which allows you to connect with others through games such as Trivia, Would You Rather, and more! Match with another person and discuss why you think your answer is right. Friends Up also allows you to keep yourself private or if you both want to continue chatting, you can share your user details with your opponent!

## Features
- Connect two players in a game room
- Chat Room available while playing games
- Four game types
	- Trivia
	- Would you Rather
	- Prompted Chat
	- Cards Against Humanity
- Players choose whether they want to become friends after playing a game together
- User Profile to display information
- Report feature after chat has ended


## Tech Stack
Technology used in this project
- Kotlin
- Jetpack Compose
- Firebase
- GraphQL
- Apollo 
- AWS 
- TypeScript
- Pipedream
- Lottie

Many icons used from: 
- [Icons8](https://icons8.com/icons/)

## Developers
- Lorenz Castillo
- Sahand Hajiseyedi
- Sukhmandeep Singh
- Kelsey Zirk

## How to install and run
### Prerequisites
> Git command line tool installed

> Android Studio installed

> Access to the backend repository for this application.
Which is [Friends-up-backend](https://github.com/Sukhman193/friends-up-backend)

### Installation steps
1. First we need to get the android project on the local computer, so open the terminal and navigate to the directory where you would like to make the installation.
    ```
    git clone https://github.com/Sukhman193/Friends-Up.git
    ```
2. After we clone the repository we need to open it inside android studio
3. Now we need to get the SHA keys for the computer
	1. Open the terminal from android studio(should be somewhere at the bottom left)
	2. Run the following command
	    ```
        ./gradlew signingReport
        ```
	3. Take note of the SHA1
4. Now we need to go to the browser and open [firebase console](https://console.firebase.google.com/)
	1. Create a new project and name it `Friends up`
	2. You may decide to turn on `Analytics` or not and click on `Create Project` (If analytics was enabled you will have one more step to take before being able to create the project)
	3. Now click on the android Icon to add the android application
	4. Enter the following information  
	   Package name
	    ```
        ca.finalfive.friendsup
        ```
	   App Nickname
	    ```
        Friends Up
        ```
	   Debug signing certificate SHA-1
	    ```
        [Paste the SHA key you got in stpe 3.3]
        ```
	5. Now download the `google-services.json` file
5. Move the `google-services.json` file generated in the previous step and place it inside `app/` (`app/google-services.json`)
6. Now set up the [Friends up backend](https://github.com/Sukhman193/friends-up-backend) and get the endpoint url
7. Go into the file `app/src/main/java/repositories/GameApolloRepository.kt` and on line `259` change the server url to the one you got in the previous step
8. Congratulations, your app is now ready to run
    