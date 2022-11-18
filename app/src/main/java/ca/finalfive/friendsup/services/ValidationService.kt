package ca.finalfive.friendsup.services

class Constants {
    class Regex {
        companion object {
            val PHONE_NUMBER = kotlin.text.Regex("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}\$")
        }
    }
}

class ValidationService {
    fun isPhoneNumber(data: String): Boolean{
        try{
            Constants.Regex.PHONE_NUMBER.matches(data)
//            val phoneNumber =
            return true
        }catch(e: Exception){
            return false
        }
    }
}