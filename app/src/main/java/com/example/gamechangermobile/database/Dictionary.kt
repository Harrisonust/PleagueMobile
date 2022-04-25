package com.example.gamechangermobile.database

import com.example.gamechangermobile.R

class Dictionary {
    companion object {
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
            "德古拉" to R.drawable.braves_samuel_deguara,
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
            "杰倫" to R.drawable.ic_baseline_sports_basketball_24,
            "湯瑪士" to R.drawable.kings_thomas_welsh,
            "麥卡洛" to R.drawable.kings_chris_mccullough,
            "里金斯" to R.drawable.kings_liggins_deandre,
            "洪志善" to R.drawable.kings_chih_shan_hung,
            "牧倫斯" to R.drawable.ic_baseline_sports_basketball_24,

        )
    }
}