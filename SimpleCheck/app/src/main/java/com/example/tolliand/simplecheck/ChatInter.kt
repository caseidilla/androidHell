package com.example.tolliand.simplecheck

class ChatInter {
    var nameUser: String = ""
    var lastMess: String = ""

    constructor(){}

    constructor(name: String, mess: String){
        this.nameUser = name
        this.lastMess = mess
    }

}