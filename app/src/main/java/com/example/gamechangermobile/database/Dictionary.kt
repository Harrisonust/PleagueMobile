package com.example.gamechangermobile.database

import com.example.gamechangermobile.R

class Dictionary {
    companion object {
        //        "OPP", "MSCR", "H/A", "MIN",
//        "PTS", "REB", "AST",
//        "FG", "FGM", "FGA", "FG%",
//        "2P", "2PM", "2PA", "2P%",
//        "3P", "3PM", "3PA", "3P%",
//        "FT", "FTM", "FTA", "FT%",
//        "OREB", "DREB",
//        "STL", "BLK", "TOV", "PF", "EFF"
        val statsName = mapOf(
            "PTS" to "points",
            "REB" to "rebounds",
            "AST" to "assists",

            "FGM" to "fieldGoalMade",
            "FGA" to "fieldGoalAttempt",

            "2PM" to "twoPointMade",
            "2PA" to "twoPointAttempt",

            "3PM" to "threePointMade",
            "3PA" to "threePointAttempt",

            "FTM" to "freeThrowMade",
            "FTA" to "freeThrowAttempt",

            "OREB" to "offensiveRebounds",
            "DREB" to "defensiveRebounds",
            "STL" to "steals",
            "BLK" to "blocks",
            "TOV" to "turnovers",
            "PF" to "personalFouls",

            "EFF" to "effFieldGoalPercentage",
        )

        val teams = mapOf(
            "台北富邦勇士" to "Braves",
            "新北國王" to "Kings",
            "桃園領航猿" to "Pilots",
            "新竹街口攻城獅" to "Lioneers",
            "福爾摩沙台新夢想家" to "Dreamers",
            "高雄鋼鐵人" to "Steelers"
        )

        val playerToImageResource = mapOf(
            // Braves
            "張宗憲" to R.drawable.braves_tsung_hsien_chang,
            "林志傑" to R.drawable.braves_chih_chieh_lin,
            "蔡文誠" to R.drawable.braves_wen_cheng_tsai,
            "賴廷恩" to R.drawable.braves_ting_en_lai,
            "簡偉儒" to R.drawable.braves_wei_ju_chien,
            "周桂羽" to R.drawable.braves_kuei_yu_chou,
            "林孟學" to R.drawable.braves_meng_hsueh,
            "曾祥鈞" to R.drawable.braves_hsiang_chun_tseng,
            "石博恩" to R.drawable.braves_brendon_smart,
            "林書緯" to R.drawable.braves_shu_wei_lin,
            "曾文鼎" to R.drawable.braves_ding_tseng,
            "張耕淯" to R.drawable.braves_keng_yu_chang,
            "辛特力" to R.drawable.braves_michael_singletary,
            "塞瑟夫" to R.drawable.braves_zaytsev,
//            "德古拉" to R.drawable.braves_samuel_deguara,
            "瓊斯" to R.drawable.braves_jones,

            // kings
            "楊敬敏" to R.drawable.kings_chin_min_yang,
            "戴維斯" to R.drawable.kings_q_davis,
            "林力仁" to R.drawable.kings_li_jen_lin,
            "李愷諺" to R.drawable.kings_kai_yen_li,
            "張文平" to R.drawable.kings_wen_ping_chang,
            "簡祐哲" to R.drawable.kings_you_chen_chien,
            "洪楷傑" to R.drawable.kings_kai_chieh_hung,
            "李盈鋒" to R.drawable.kings_ying_fong_li,
            "曾于豪" to R.drawable.kings_yu_hao_tseng,
            "楊興治" to R.drawable.kings_hsing_chih_yang,
            "林仕軒" to R.drawable.kings_shih_hsuan_lin,
            "陳俊男" to R.drawable.kings_chun_nan_chen,
            "聶歐瑪" to R.drawable.kings_niang_omar,
            "杰倫" to R.drawable.ic_baseline_sports_basketball_24, // TODO
            "湯瑪士" to R.drawable.kings_thomas_welsh,
            "麥卡洛" to R.drawable.kings_chris_mccullough,
            "里金斯" to R.drawable.kings_liggins_deandre,
            "洪志善" to R.drawable.kings_chih_shan_hung,
            "牧倫斯" to R.drawable.kings_byron_mullens, // TODO

            // pilots
            "吳家駿" to R.drawable.pilots_chia_chun_wu,
            "陳昱瑞" to R.drawable.pilots_yu_jui_chen,
            "施晉堯" to R.drawable.pilots_chin_yao_shih,
            "黃泓瀚" to R.drawable.pilots_hong_han_huang,
            "陳冠全" to R.drawable.pilots_kuan_chuan_chen,
            "關達祐" to R.drawable.pilots_ta_you_kuan,
            "李家慷" to R.drawable.pilots_chia_kang_li,
            "盧峻翔" to R.drawable.pilots_chun_hsiang_lu,
            "施顏宗" to R.drawable.pilots_yen_tsung_shih,
            "林耀宗" to R.drawable.pilots_yao_tsung_lin,
            "林正" to R.drawable.pilots_cheng_lin,
            "溫立煌" to R.drawable.pilots_li_huang_wen,
            "蘇志誠" to R.drawable.pilots_chih_cheng_su,
            "喬丹" to R.drawable.pilots_jordan_tolbert,
            "吉倫" to R.drawable.ic_baseline_sports_basketball_24, // TODO
            "羅賓森" to R.drawable.pilots_devin_robinson,
            "貝索維奇" to R.drawable.pilots_besovic, // TODO
            "林金榜" to R.drawable.pilots_chin_pang_lin,

            // lioneers
            "高國豪" to R.drawable.lioneers_kuo_hao_kao,
            "李家瑞" to R.drawable.lioneers_chia_jul_lee,
            "郭少傑" to R.drawable.lioneers_shao_chieh_kuo,
            "朱雲豪" to R.drawable.lioneers_yun_hao_chu,
            "蕭順議" to R.drawable.lioneers_shun_yi_hsiao,
            "陳堅恩" to R.drawable.lioneers_elliot_tan,
            "田浩" to R.drawable.lioneers_hao_tien,
            "容毅燊" to R.drawable.lioneers_ngai_san_iong,
            "吳岱豪" to R.drawable.lioneers_tai_hao_wu,
            "宋宇軒" to R.drawable.lioneers_yu_hsuan_sung,
            "林明毅" to R.drawable.lioneers_ming_yi_lin,
            "林宜輝" to R.drawable.lioneers_yi_huei_lin,
            "大勝" to R.drawable.lioneers_branden_dawson,
            "法獅" to R.drawable.lioneers_nicholas_faust,
            "辛巴" to R.drawable.lioneers_simbhullar,
            "布魯斯巫獅" to R.drawable.lioneers_mike_bruesewitz,

            // dreamers
            "吳永盛" to R.drawable.dreamers_yung_cheng_wu,
            "成力煥" to R.drawable.dreamers_li_huan_chieng,
            "楊盛硯" to R.drawable.dreamers_sheng_yen_yang,
            "李德威" to R.drawable.dreamers_te_wei_lee,
            "陳振傑" to R.drawable.dreamers_jen_jei_chen,
            "林俊吉" to R.drawable.dreamers_chun_chi_lin,
            "錢肯尼" to R.drawable.dreamers_kenneth_chen,
            "吳松蔚" to R.drawable.dreamers_sung_wei_wu,
            "簡浩" to R.drawable.dreamers_creighton_douglas,
            "忻沃克" to R.drawable.dreamers_randall_walko,
            "王振原" to R.drawable.dreamers_chen_yuan_wang,
            "吉爾貝克" to R.drawable.dreamers_brandon_gilbeck,
            "鄭子洋" to R.drawable.dreamers_tzu_yang_cheng,
            "布依德" to R.drawable.dreamers_julian_boyd,
            "利多" to R.drawable.ic_baseline_sports_basketball_24, // TODO
            "希克斯" to R.drawable.ic_baseline_sports_basketball_24, // TODO
            "揚科維奇" to R.drawable.dreamers_jankovic,

            // steelers
            "陳又瑋" to R.drawable.steelers_yu_wei_chen,
            "盧哲毅" to R.drawable.steelers_che_yi_lu,
            "呂政儒" to R.drawable.steelers_cheng_ju_lu,
            "王柏智" to R.drawable.steelers_po_chih_wang,
            "彭俊諺" to R.drawable.steelers_chun_yen_peng,
            "王律翔" to R.drawable.steelers_lu_hsiang_wang,
            "張伯維" to R.drawable.steelers_po_wei_chang,
            "林均濠" to R.drawable.steelers_jyun_hao_lin,
            "楊浩之" to R.drawable.steelers_matthew_yang,
            "鄭德維" to R.drawable.steelers_te_wei_cheng,
            "藍少甫" to R.drawable.steelers_shao_fu_lan,
            "戴瑞騰" to R.drawable.steelers_austin_derrick,
            "塔克" to R.drawable.steelers_tucker,
            "班森" to R.drawable.steelers_keith_benson,
            "布朗" to R.drawable.steelers_taylor_michael_braun,
            "班尼特" to R.drawable.steelers_anthony_bennett,
            "哈里斯" to R.drawable.ic_baseline_sports_basketball_24, // TODO
            "周儀翔" to R.drawable.steelers_chou_yi_hsiang,

            )

        val cn2en = mapOf<String, String>(
            "C" to "C",
            "Ｃ" to "C",
            "G" to "G",
            "Ｇ" to "G",
            "F" to "F",
            "Ｆ" to "F"
        )
    }
}