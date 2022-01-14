package com.example.gamechangermobile.models

class Player(val FirstName:String, val LastName:String, var ProfilePic: Int) {
    var team: Team = Team("", 0)
    var age:Int = 0
    var pts:Int = 0
    var ast:Int = 0
    var reb:Int = 0

}