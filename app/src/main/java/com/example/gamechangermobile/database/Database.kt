package com.example.gamechangermobile.database

class Database {
    val headers = listOf("球員","出賽","時間","命中","出手","命中率","命中","出手","命中率","命中","出手","命中率","進攻","防守","籃板","助攻","失誤","抄截","阻攻","犯規","得分")

    val statsDictionary = mapOf(
            "points" to "PTS",
            "rebounds" to "REB",
            "assists" to "AST",
            "fieldGoal" to "FGM",
            "fieldGoalAttempt" to "FGA",
            "fieldGoalPercentage" to "FG%",
//            "fieldGoal2pt" to "",
//            "fieldGoalAttempt2pt" to 0F,
//            "fieldGoalPercentage2pt" to 0F,
            "fieldGoal3pt" to "3FGM",
            "fieldGoalAttempt3pt" to "3FGA",
            "fieldGoalPercentage3pt" to "3FG%",
            "freeThrow" to "FTM",
            "freeThrowAttempt" to "FTA",
            "freeThrowAttemptPercentage" to "FT%",
            "offensiveRebounds" to "OREB",
            "defensiveRebounds" to "DREB",
            "steals" to "STL",
            "blocks" to "BLK",
            "turnovers" to "TO",
            "personalFouls" to "PF",
//            "effFieldGoalPercentage" to ,
    )

    class Lioneers{
        val roster = listOf(
            listOf("lioneers_kuo_hao_kao","高國豪","5","27:37","2.8","9.6","29.2%","0.6","3.0","20.0%","1.4","1.8","77.8%","1.4","4.4","5.8","3.6","1.4","2.0","0.0","2.0","7.6"),
            listOf("lioneers_chia_jul_lee","李家瑞","8","32:06","3.1","7.8","40.3%","0.4","2.8","13.6%","1.1","2.0","56.2%","1.3","3.6","4.9","2.3","2.0","0.8","0.9","1.9","7.8"),
            listOf("lioneers_shao_chieh_kuo","郭少傑","7","9:05","0.9","2.3","37.5%","0.4","1.4","30.0%","0.9","1.1","75.0%","0.1","0.3","0.4","0.1","1.4","0.3","0.0","1.4","3.0"),
            listOf("lioneers_yun_hao_chu","朱雲豪","8","18:18","2.0","6.1","32.7%","0.6","4.3","14.7%","1.1","1.8","64.3%","0.9","2.6","3.5","1.4","1.6","0.8","0.3","1.8","5.8"),
            listOf("lioneers_shun_yi_hsiao","蕭順議","5","7:32","0.6","1.6","37.5%","0.0","0.2","0.0%","0.6","1.2","50.0%","0.4","1.2","1.6","0.2","0.6","0.2","0.0","1.6","1.8"),
            listOf("lioneers_elliot_tan","陳堅恩","8","18:07","2.3","6.0","37.5%","1.4","3.8","36.7%","0.5","0.6","80.0%","0.3","2.3","2.5","1.0","1.1","0.3","0.3","2.8","6.4"),
            listOf("lioneers_hao_tien","田浩","7","10:37","0.4","1.6","27.3%","0.1","0.6","25.0%","0.4","0.6","75.0%","0.1","0.6","0.7","2.0","1.0","0.0","0.0","1.6","1.4"),
            listOf("lioneers_ngai_san_iong","容毅燊","3","4:01","0.3","0.7","50.0%","0.3","0.3","100.0%","0.0","0.0","0.0%","0.0","1.3","1.3","0.0","0.3","0.0","0.3","1.7","1.0"),
            listOf("lioneers_tai_hao_wu","吳岱豪","8","13:16","0.5","1.6","30.8%","0.0","0.3","0.0%","0.4","1.3","30.0%","0.6","0.9","1.5","0.8","1.0","0.6","0.5","1.9","1.4"),
            listOf("lioneers_yu_hsuan_sung","宋宇軒","2","10:25","1.0","2.5","40.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.5","0.5","1.0","0.0","1.5","0.0","0.0","0.0","2.0"),
            listOf("lioneers_ming_yi_lin","林明毅","6","16:23","0.7","3.2","21.1%","0.3","1.8","18.2%","0.3","0.3","100.0%","0.3","1.5","1.8","0.8","1.7","1.0","0.0","1.0","2.0"),
            listOf("lioneers_yi_huei_lin","林宜輝","8","31:53","4.4","11.5","38.0%","1.5","5.6","26.7%","1.6","2.6","61.9%","0.9","3.6","4.5","2.6","3.5","0.6","0.1","2.9","11.9"),
            listOf("lioneers_branden_dawson","大勝","2","28:41","5.5","18.0","30.6%","0.0","1.0","0.0%","0.0","1.0","0.0%","6.0","7.0","13.0","0.5","2.5","1.0","1.5","1.0","11.0"),
            listOf("lioneers_nicholas_faust","法獅","6","36:30","10.3","22.0","47.0%","3.8","8.8","43.4%","2.7","3.0","88.9%","2.5","6.2","8.7","2.2","4.0","1.8","0.7","2.2","27.2"),
            listOf("lioneers_simbhullar","辛巴","8","38:58","10.8","15.4","69.9%","0.0","0.0","0.0%","6.0","10.5","57.1%","6.4","14.6","21.0","2.5","2.9","0.5","2.6","1.5","27.5")
        )
    }

    class Dreamers {
        val roster = listOf(
            listOf("dreamers_yung_cheng_wu","吳永盛","7","25:49","4.6","10.4","43.8%","1.3","3.3","39.1%","0.3","0.6","50.0%","0.7","2.7","3.4","3.0","2.0","2.1","0.0","2.1","10.7"),
            listOf("dreamers_yung_cheng_wu","成力煥","9","26:31","2.6","7.1","35.9%","0.9","2.9","30.8%","1.2","1.4","84.6%","1.2","1.4","2.7","1.2","1.1","0.9","0.1","2.2","7.2"),
            listOf("dreamers_yung_cheng_wu","楊盛硯","7","9:12","0.4","2.1","20.0%","0.0","1.1","0.0%","0.9","1.0","85.7%","0.1","0.9","1.0","0.3","0.1","0.6","0.0","0.7","1.7"),
            listOf("dreamers_yung_cheng_wu","李德威","9","22:55","2.9","9.0","32.1%","1.0","4.4","22.5%","0.4","0.6","80.0%","0.9","3.8","4.7","1.7","2.9","0.4","1.2","2.9","7.2"),
            listOf("dreamers_yung_cheng_wu","陳振傑","6","13:56","2.0","5.2","38.7%","0.8","3.2","26.3%","0.5","0.8","60.0%","0.7","1.3","2.0","0.3","1.2","1.0","0.0","0.8","5.3"),
            listOf("dreamers_yung_cheng_wu","林俊吉","9","28:38","4.0","9.8","40.9%","1.6","4.9","31.8%","0.7","1.1","60.0%","0.6","2.1","2.7","4.2","2.3","1.7","0.0","2.6","10.2"),
            listOf("dreamers_yung_cheng_wu","錢肯尼","9","36:20","5.0","11.6","43.3%","2.0","4.0","50.0%","2.6","4.1","62.2%","1.3","4.0","5.3","2.1","1.3","0.9","0.3","2.0","14.6"),
            listOf("dreamers_yung_cheng_wu","吳松蔚","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("dreamers_yung_cheng_wu","簡浩","8","25:20","2.6","7.9","33.3%","2.0","5.9","34.0%","0.4","0.6","60.0%","1.3","3.0","4.3","2.0","1.4","1.6","0.0","1.9","7.6"),
            listOf("dreamers_yung_cheng_wu","沃克","9","21:33","2.4","7.6","32.4%","1.3","3.8","35.3%","0.8","1.1","70.0%","1.6","4.8","6.3","1.3","0.9","0.2","0.6","1.6","7.0"),
            listOf("dreamers_yung_cheng_wu","王振原","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("dreamers_yung_cheng_wu","吉爾貝克","9","25:17","3.9","7.6","51.5%","0.0","0.2","0.0%","1.8","2.9","61.5%","3.3","6.1","9.4","0.7","1.4","0.3","2.8","3.3","9.6"),
            listOf("dreamers_yung_cheng_wu","鄭子洋","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("dreamers_yung_cheng_wu","布依德","6","32:32","5.7","17.7","32.1%","1.8","7.7","23.9%","3.8","5.5","69.7%","3.0","7.8","10.8","1.3","2.7","1.0","0.8","2.7","17.0"),
            listOf("dreamers_yung_cheng_wu","利多","1","29:54","6.0","24.0","25.0%","2.0","9.0","22.2%","6.0","8.0","75.0%","3.0","7.0","10.0","1.0","3.0","4.0","1.0","4.0","20.0")
        )
    }

    class Kings {
        val roster = listOf(
            listOf("kings_chin_min_yang","楊敬敏","9","36:25","6.6","18.2","36.0%","3.7","10.7","34.4%","4.4","5.4","81.6%","0.6","5.8","6.3","3.7","4.0","1.7","0.0","1.6","21.2"),
            listOf("kings_chin_min_yang","戴維斯","2","19:17","2.0","6.5","30.8%","0.0","2.0","0.0%","1.0","2.0","50.0%","4.0","7.5","11.5","1.0","1.5","0.0","1.0","3.5","5.0"),
            listOf("kings_chin_min_yang","林力仁","4","5:32","0.3","2.5","10.0%","0.3","1.8","14.3%","1.0","1.5","66.7%","0.3","1.3","1.5","0.0","0.3","0.0","0.0","0.0","1.8"),
            listOf("kings_chin_min_yang","李愷諺","9","34:23","5.3","11.3","47.1%","1.4","4.4","32.5%","3.0","3.9","77.1%","1.7","5.4","7.1","6.2","3.0","2.1","0.2","0.8","15.1"),
            listOf("kings_chin_min_yang","張文平","9","16:36","1.1","5.8","19.2%","0.9","4.4","20.0%","0.2","0.2","100.0%","0.8","2.2","3.0","1.1","0.6","0.4","0.1","0.9","3.3"),
            listOf("kings_chin_min_yang","簡祐哲","9","22:58","4.3","9.2","47.0%","2.4","5.9","41.5%","0.6","0.6","100.0%","0.3","1.0","1.3","0.3","0.8","0.7","0.1","2.2","11.7"),
            listOf("kings_chin_min_yang","洪楷傑","8","26:42","4.0","8.8","45.7%","2.1","5.5","38.6%","1.4","1.6","84.6%","0.5","1.0","1.5","2.0","2.1","1.3","0.1","1.9","11.5"),
            listOf("kings_chin_min_yang","李盈鋒","5","17:10","2.0","5.6","35.7%","1.2","3.0","40.0%","1.4","1.6","87.5%","0.6","1.8","2.4","0.8","0.6","0.6","0.2","0.8","6.6"),
            listOf("kings_chin_min_yang","曾于豪","2","4:58","0.0","1.0","0.0%","0.0","0.0","0.0%","1.0","1.0","100.0%","0.0","0.5","0.5","0.0","0.5","0.5","0.5","0.0","1.0"),
            listOf("kings_chin_min_yang","楊興治","9","17:18","1.8","3.7","48.5%","0.0","0.0","0.0%","0.6","1.2","45.5%","1.4","1.9","3.3","0.4","0.8","0.3","0.0","1.8","4.1"),
            listOf("kings_chin_min_yang","林仕軒","6","13:11","0.3","3.0","11.1%","0.0","1.0","0.0%","0.5","1.3","37.5%","0.3","1.0","1.3","2.3","1.2","0.5","0.0","1.0","1.2"),
            listOf("kings_chin_min_yang","陳俊男","5","14:03","1.2","4.2","28.6%","0.2","1.4","14.3%","0.6","1.6","37.5%","0.6","1.2","1.8","1.0","0.8","0.4","0.2","1.2","3.2"),
            listOf("kings_chin_min_yang","聶歐瑪","6","8:37","0.2","1.0","16.7%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.7","0.5","1.2","0.5","0.3","0.5","0.0","2.2","0.3"),
            listOf("kings_chin_min_yang","杰倫","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("kings_chin_min_yang","湯瑪士","7","40:47","7.7","14.1","54.5%","1.1","4.6","25.0%","2.0","3.0","66.7%","6.6","12.6","19.1","2.0","1.6","2.0","2.9","1.6","18.6"),
            listOf("kings_chin_min_yang","麥卡洛","3","35:13","7.7","17.3","44.2%","3.0","8.7","34.6%","1.7","3.0","55.6%","0.3","7.3","7.7","1.0","2.0","1.0","0.7","3.3","20.0"),
            listOf("kings_chin_min_yang","里金斯","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("kings_chin_min_yang","洪志善","4","16:16","1.3","3.3","38.5%","0.5","2.0","25.0%","0.0","0.0","0.0%","0.0","1.8","1.8","2.0","1.3","0.0","0.0","1.0","3.0")
        )
    }

    class Braves {
        val roster = listOf(
            listOf("braves_tsung_hsien_chang","張宗憲","9","31:47","4.6","14.7","31.1%","1.0","4.1","24.3%","1.3","2.2","60.0%","1.1","1.7","2.8","2.3","2.1","1.6","0.2","1.7","11.4"),
            listOf("braves_tsung_hsien_chang","林志傑","7","21:05","3.3","8.4","39.0%","1.6","5.1","30.6%","1.6","2.3","68.8%","0.9","2.7","3.6","1.3","1.4","1.0","0.0","1.0","9.7"),
            listOf("braves_tsung_hsien_chang","蔡文誠","11","21:29","2.5","6.3","39.1%","0.4","1.6","22.2%","0.3","0.4","75.0%","1.0","3.7","4.7","2.1","0.8","1.1","0.1","1.0","5.5"),
            listOf("braves_tsung_hsien_chang","賴廷恩","7","8:26","1.0","2.1","46.7%","0.3","0.9","33.3%","0.6","0.6","100.0%","0.1","0.6","0.7","0.4","0.6","0.9","0.0","0.1","2.9"),
            listOf("braves_tsung_hsien_chang","簡偉儒","9","14:11","2.0","4.9","40.9%","1.0","2.6","39.1%","0.4","1.3","33.3%","0.7","1.3","2.0","0.7","0.8","0.7","0.0","0.3","5.4"),
            listOf("braves_tsung_hsien_chang","周桂羽","9","13:50","1.3","3.3","40.0%","0.6","1.6","35.7%","0.4","0.7","66.7%","0.3","0.9","1.2","1.2","1.1","1.0","0.2","1.6","3.7"),
            listOf("braves_tsung_hsien_chang","林孟學","8","17:41","1.3","3.5","35.7%","0.1","0.9","14.3%","0.4","0.5","75.0%","1.3","2.4","3.6","0.5","0.4","0.9","0.1","1.3","3.0"),
            listOf("braves_tsung_hsien_chang","曾祥鈞","9","13:39","1.0","3.2","31.0%","0.2","1.0","22.2%","0.8","1.4","53.8%","0.4","2.6","3.0","0.3","0.4","0.7","0.4","2.2","3.0"),
            listOf("braves_tsung_hsien_chang","石博恩","8","22:19","3.3","7.4","44.1%","0.8","2.8","27.3%","1.5","2.1","70.6%","2.9","6.0","8.9","0.4","0.6","1.0","0.8","2.0","8.8"),
            listOf("braves_tsung_hsien_chang","林書緯","9","29:49","4.8","11.2","42.6%","0.9","4.1","21.6%","1.3","1.9","70.6%","0.7","2.2","2.9","4.0","1.8","1.0","0.2","1.4","11.8"),
            listOf("braves_tsung_hsien_chang","曾文鼎","4","23:23","3.0","7.5","40.0%","0.3","3.0","8.3%","1.0","1.0","100.0%","1.8","2.0","3.8","3.8","1.5","0.0","0.0","2.0","7.3"),
            listOf("braves_tsung_hsien_chang","張耕淯","8","10:09","1.3","3.4","37.0%","0.3","1.1","22.2%","0.8","0.8","100.0%","0.3","0.6","0.9","0.1","0.4","0.3","0.1","0.8","3.5"),
            listOf("braves_tsung_hsien_chang","辛特力","10","36:25","9.2","20.9","44.0%","3.0","8.6","34.9%","2.9","3.3","87.9%","2.7","9.8","12.5","2.6","3.3","1.3","0.7","2.7","24.3"),
            listOf("braves_tsung_hsien_chang","塞瑟夫","8","39:07","7.8","15.5","50.0%","2.0","5.0","40.0%","2.5","3.9","64.5%","3.0","6.9","9.9","1.4","2.3","0.9","1.5","2.3","20.0"),
            listOf("braves_tsung_hsien_chang","德古拉","4","29:11","7.0","11.3","62.2%","0.0","0.3","0.0%","2.3","3.0","75.0%","5.3","5.5","10.8","1.3","2.5","0.8","0.8","3.5","16.3")
        )
    }

    class Pilots {
        val roster = listOf(
            listOf("pilots_chia_chun_wu","吳家駿","8","34:36","3.1","8.1","38.5%","1.0","3.8","26.7%","0.8","0.9","85.7%","0.4","2.5","2.9","3.9","1.4","1.0","0.0","1.1","8.0"),
            listOf("pilots_chia_chun_wu","陳昱瑞","8","25:28","2.1","5.3","40.5%","0.8","2.4","31.6%","0.9","1.4","63.6%","1.3","4.5","5.8","2.4","1.4","1.4","0.0","1.6","5.9"),
            listOf("pilots_chia_chun_wu","施晉堯","8","34:02","4.1","12.4","33.3%","1.8","6.8","25.9%","0.6","1.1","55.6%","0.6","2.3","2.9","1.9","1.6","1.1","0.1","2.4","10.6"),
            listOf("pilots_chia_chun_wu","黃泓瀚","2","19:29","0.0","3.5","0.0%","0.0","2.0","0.0%","0.0","2.0","0.0%","3.0","3.0","6.0","0.5","1.0","0.5","0.5","2.5","0.0"),
            listOf("pilots_chia_chun_wu","陳冠全","8","24:55","2.9","8.6","33.3%","0.6","2.5","25.0%","0.5","1.8","28.6%","1.9","4.0","5.9","1.4","1.9","1.3","0.4","1.5","6.9"),
            listOf("pilots_chia_chun_wu","關達祐","5","19:34","1.0","4.6","21.7%","0.4","2.4","16.7%","0.6","0.8","75.0%","0.4","0.8","1.2","2.4","1.4","1.4","0.4","1.8","3.0"),
            listOf("pilots_chia_chun_wu","李家慷","7","17:51","1.7","5.1","33.3%","0.4","2.1","20.0%","0.9","1.9","46.2%","0.4","0.7","1.1","1.4","0.9","0.4","0.0","1.7","4.7"),
            listOf("pilots_chia_chun_wu","盧峻翔","3","28:29","5.3","12.7","42.1%","2.7","6.7","40.0%","2.0","2.0","100.0%","1.3","4.0","5.3","0.3","2.0","0.7","0.0","2.0","15.3"),
            listOf("pilots_chia_chun_wu","施顏宗","1","5:08","0.0","1.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","1.0","0.0","1.0","0.0","3.0","1.0","0.0","1.0","0.0"),
            listOf("pilots_chia_chun_wu","林耀宗","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("pilots_chia_chun_wu","林正","4","7:45","0.5","1.3","40.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.3","1.8","2.0","0.8","0.5","0.0","0.0","0.8","1.0"),
            listOf("pilots_chia_chun_wu","溫立煌","5","5:51","0.2","1.2","16.7%","0.0","0.2","0.0%","0.2","0.6","33.3%","0.2","0.6","0.8","0.0","0.2","0.0","0.2","1.0","0.6"),
            listOf("pilots_chia_chun_wu","蘇志誠","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("pilots_chia_chun_wu","喬丹","7","37:17","7.6","13.6","55.8%","1.3","3.7","34.6%","1.7","3.0","57.1%","3.7","9.1","12.9","4.1","2.1","1.9","1.1","2.0","18.1"),
            listOf("pilots_chia_chun_wu","吉倫","3","35:26","7.0","19.3","36.2%","2.0","6.7","30.0%","6.7","7.7","87.0%","0.7","3.7","4.3","3.7","1.7","2.3","0.0","1.7","22.7"),
            listOf("pilots_chia_chun_wu","羅賓森","6","33:46","11.3","22.8","49.6%","1.5","3.7","40.9%","3.2","3.8","82.6%","3.7","10.7","14.3","2.2","3.5","1.0","1.0","3.2","27.3")
        )
    }

    class Steelers {
        val roster = listOf(
            listOf("steelers_yu_wei_chen","陳又瑋","8","35:33","4.1","10.4","39.8%","0.5","2.6","19.0%","2.0","2.4","84.2%","0.5","2.8","3.3","2.1","0.9","2.1","0.5","1.8","10.8"),
            listOf("steelers_yu_wei_chen","盧哲毅","5","7:03","0.8","1.8","44.4%","0.6","1.6","37.5%","0.2","0.4","50.0%","0.4","0.2","0.6","0.2","1.2","0.6","0.0","0.6","2.4"),
            listOf("steelers_yu_wei_chen","呂政儒","8","37:43","5.8","15.6","36.8%","2.6","10.0","26.3%","1.1","1.5","75.0%","0.8","2.3","3.0","2.3","1.9","2.0","0.4","2.5","15.3"),
            listOf("steelers_yu_wei_chen","王柏智","5","23:01","4.0","8.8","45.5%","1.4","3.8","36.8%","0.6","1.4","42.9%","0.6","4.4","5.0","2.2","2.0","0.6","0.6","2.4","10.0"),
            listOf("steelers_yu_wei_chen","彭俊諺","8","17:01","1.0","3.5","28.6%","0.5","2.0","25.0%","0.3","0.4","66.7%","0.4","2.4","2.8","2.6","1.1","1.6","0.0","1.6","2.8"),
            listOf("steelers_yu_wei_chen","王律翔","8","18:49","1.8","6.0","29.2%","0.6","3.3","19.2%","0.0","0.0","0.0%","0.5","0.9","1.4","0.4","1.1","0.4","0.0","1.8","4.1"),
            listOf("steelers_yu_wei_chen","張伯維","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("steelers_yu_wei_chen","林均濠","5","9:13","0.6","2.2","27.3%","0.2","1.0","20.0%","0.0","0.0","0.0%","0.0","1.2","1.2","0.4","0.6","0.0","0.0","1.8","1.4"),
            listOf("steelers_yu_wei_chen","楊浩之","0","0:00","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"),
            listOf("steelers_yu_wei_chen","鄭德維","8","14:35","1.4","4.4","31.4%","0.3","1.4","18.2%","0.3","0.4","66.7%","0.8","1.3","2.0","0.8","1.3","1.0","0.0","1.9","3.3"),
            listOf("steelers_yu_wei_chen","藍少甫","7","15:47","2.6","6.0","42.9%","0.4","1.6","27.3%","0.6","1.0","57.1%","1.6","1.7","3.3","0.0","1.7","0.4","0.3","1.7","6.1"),
            listOf("steelers_yu_wei_chen","戴瑞騰","1","7:12","2.0","3.0","66.7%","0.0","0.0","0.0%","0.0","0.0","0.0%","0.0","2.0","2.0","0.0","0.0","0.0","0.0","1.0","4.0"),
            listOf("steelers_yu_wei_chen","塔克","6","41:34","7.7","19.8","38.7%","3.0","10.8","27.7%","1.8","3.3","55.0%","1.7","6.7","8.3","6.3","1.7","1.5","0.0","1.5","20.2"),
            listOf("steelers_yu_wei_chen","班森","6","33:20","9.0","17.0","52.9%","0.3","1.3","25.0%","2.3","4.5","51.9%","5.2","8.2","13.3","0.3","0.3","1.0","0.7","2.7","20.7"),
            listOf("steelers_yu_wei_chen","伯朗","4","40:46","5.3","13.3","39.6%","1.8","7.0","25.0%","2.5","3.3","76.9%","1.0","8.3","9.3","7.0","2.8","2.0","0.3","2.5","14.8")
        )
    }
}