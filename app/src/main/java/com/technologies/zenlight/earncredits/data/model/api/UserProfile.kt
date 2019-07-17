package com.technologies.zenlight.earncredits.data.model.api

class UserProfile {

    var id = ""
    var challengeRefs = ArrayList<String>()
    var powerUpRefs = ArrayList<String>()
    var credits = 0
    var userName = ""
    var imageUrl = ""
    var isPasswordReset = false
    var password = ""
    var difficulty = "normal"
    var email = ""
    var timestamp: Long = 0
}