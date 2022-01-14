package com.example.gamechangermobile.models

class Player(val FirstName:String, val LastName:String, var ProfilePic: Int) {
    var team: Team = Team("", 0)
    var age:Int = 0
    var gp:Int = 0
    var min:Float = 0.0F
    var pts:Float = 0.0F
    var fgm:Float = 0.0F
    var fga:Float = 0.0F
    var fg_precentage:Float = 0.0F
    var three_pm:Float = 0.0F
    var three_pa:Float = 0.0F
    var ftm:Float = 0.0F
    var fta:Float = 0.0F
    var ft_precentage:Float = 0.0F
    var oreb:Float = 0.0F
    var dreb:Float = 0.0F
    var reb:Float = 0.0F
    var ast:Float = 0.0F
    var tov:Float = 0.0F
    var stl:Float = 0.0F
    var blk:Float = 0.0F
    var pf:Float = 0.0F
    var fp:Float = 0.0F
    var dd2:Float = 0.0F
    var td3:Float = 0.0F
    var pm:Float = 0.0F
}

//TEAM	GP	MIN	PTS	FGM	FGA	FG%	3PM	3PA	3P%	FTM	FTA	FT%	OREB	DREB	REB	AST	TOV	STL	BLK	PF	FP	DD2	TD3	+/-