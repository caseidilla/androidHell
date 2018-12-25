package com.example.tolliand.simplecheck

import java.util.Calendar


class ChMessage {

    var messageText = String()
    var messageUser: String? = null
    var messageTime: Calendar? = null

    constructor(){}

    constructor(messageText: String, messageUser: String) {
        this.messageText = messageText
        this.messageUser = messageUser
        messageTime = Calendar.getInstance()
    }

}