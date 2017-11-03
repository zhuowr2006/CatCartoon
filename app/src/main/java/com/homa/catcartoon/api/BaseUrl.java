package com.homa.catcartoon.api;

/**
 * Created by Homa on 2017/9/11.
 */

public class BaseUrl {

    public static final String APP_BASE_URL = "http://app.bilibili.com/";
    public static final String BASE_URL = "http://www.bilibili.com/";


    public static final String BANGUMI_BASE_URL = "http://bangumi.bilibili.com/";

    public static final String LIVE_BASE_URL = "http://live.bilibili.com/";
    public static final String VIDEO_BASE_URL = "http://interface.bilibili.com/";

    /**
     * Key
     */
    public static final String SECRETKEY_MINILOADER = "1c15888dc316e05a15fdd0a02ed6584f";


    //分区url
    //动画
    public static String URL_DONG_HUA = "1";
    public static String URL_MAD_MAV = "24";
    public static String URL_MAD_3D = "25";
    public static String URL_DONG_HUA_DUAN_PIAN = "47";
    public static String URL_DONG_HUA_ZONG_HE = "27";
    //番剧
    public static String URL_FAN_JU = "13";
    public static String URL_LIAN_ZAI_DONG_HUA = "33";
    public static String URL_WAN_JIE_DONG_HUA = "32";
    public static String URL_ZHI_XUN = "51";
    public static String URL_GUAN_FANG_YAN_SHEN = "152";
    public static String URL_GUO_CHAN_DONG_HUA = "153";
    //音乐
    public static String URL_YIN_YUE = "3";
    public static String URL_FAN_CHANG = "31";
    public static String URL_VOCALOID_UTAU = "30";
    public static String URL_YAN_ZOU= "59";
    public static String URL_YIN_YUE_XUAN_JI = "130";
    //舞蹈
    public static String URL_WU_DAO = "129";
    public static String URL_ZHAI_WU = "20";
    public static String URL_SAN_CHIYUAN_WU_DAO = "154";
    public static String URL_WU_DAO_JIAOCHENG= "156";
    //科技
    public static String URL_KE_JI = "36";
    public static String URL_JI_LU_PIAN = "37";
    public static String URL_KE_PU_REN_WEN = "124";
    public static String URL_YE_SHENG_JI_SHU= "122";
    public static String URL_YAN_JIANG = "39";
    public static String URL_XIN_HAI = "96";
    public static String URL_SHU_MA = "95";
    public static String URL_JI_XIE = "98";
    //娱乐
    public static String URL_YU_LE = "5";
    public static String URL_GAO_XIAO = "138";
    public static String URL_SHENG_HUO = "21";
    public static String URL_DONG_WU = "75";
    public static String URL_MEI_SHI = "76";
    public static String URL_ZONG_YI = "71";
    public static String URL_YU_LE_QUAN = "137";
    public static String URL_KOREA = "131";
    //电影
    public static String URL_DIAN_YIN = "23";
    public static String URL_DIAN_YIN_XIANG_GUANG = "82";
    public static String URL_DUAN_PIAN = "85";
    public static String URL_OU_MEI_DIAN_YIN = "145";
    public static String URL_RI_BEN_DIAN_YIN = "146";
    public static String URL_GUO_CHAN_DIAN_YIN = "147";
    public static String URL_QI_TA_GUOJIA = "83";
    //游戏
    public static String URL_YOU_XI = "4";
    public static String URL_DIAN_JI = "17";
    public static String URL_WANG_LUO_DIAN_YIN = "65";
    public static String URL_YIN_YOU = "136";
    public static String URL_MUGEN = "19";
    public static String URL_GMV = "121";
    //鬼畜
    public static String URL_GUI_CU = "119";
    public static String URL_GUI_CU_TIAOJIAO = "22";
    public static String URL_YING_MAD = "26";
    public static String URL_REN_LI= "126";
    public static String URL_JIAOCHENG= "127";
    //电视剧
    public static String URL_DIAN_SHI_JU = "11";
    public static String URL_LIAN_ZAI_JU= "15";
    public static String URL_WANG_JIE_JU = "34";
    public static String URL_TE_SHE= "86";
    public static String URL_DIAN_SHI_JU_XIANGUANG= "128";
    //时尚
    public static String URL_SHI_SHANG = "155";
    public static String URL_MEI_ZHUANG= "157";
    public static String URL_FU_SHI = "158";
    public static String URL_SHI_SHANG_ZHI_XUN= "159";
    //排行
    public static String URL_RANK_QUAN_QU = "0";//全区
    public static String URL_RANK_XIN_FAN = "33";//新番
    public static String URL_RANK_DONG_HUA = "1";//动画
    public static String URL_RANK_YIN_YUE = "3";//音乐
    public static String URL_RANK_YOU_XI = "4";//游戏
    public static String URL_RANK_KE_JI = "36";//科技
    public static String URL_RANK_YU_LE = "5";//娱乐
    public static String URL_RANK_DIAN_YING = "23";//电影


    public static String getVideoListURL(int videoType) {
        String url = "";
        switch (videoType) {
            //<------------动画区------------->
            case DEF_VIDEO_TYPE.DONG_HUA:
                url = URL_DONG_HUA;
                break;
            case DEF_VIDEO_TYPE.MAD_AMV:
                url = URL_MAD_MAV;
                break;
            case DEF_VIDEO_TYPE.MMD_3D:
                url = URL_MAD_3D;
                break;
            case DEF_VIDEO_TYPE.DONG_HUA_DUAN_PIAN:
                url = URL_DONG_HUA_DUAN_PIAN;
                break;
            case DEF_VIDEO_TYPE.DONG_HUA_ZONG_HE:
                url = URL_DONG_HUA_ZONG_HE;
                break;
            //<------------番剧区------------->
            case DEF_VIDEO_TYPE.FAN_JU:
                url = URL_FAN_JU;
                break;
            case DEF_VIDEO_TYPE.LIAN_ZAI_DONG_HUA:
                url = URL_LIAN_ZAI_DONG_HUA;
                break;
            case DEF_VIDEO_TYPE.WAN_JIE_DONG_HUA:
                url = URL_WAN_JIE_DONG_HUA;
                break;
            case DEF_VIDEO_TYPE.ZHI_XUN:
                url = URL_ZHI_XUN;
                break;
            case DEF_VIDEO_TYPE.GUAN_FANG_YAN_SHEN:
                url = URL_GUAN_FANG_YAN_SHEN;
                break;
            case DEF_VIDEO_TYPE.GUO_CHAN_DONG_HUA:
                url = URL_GUO_CHAN_DONG_HUA;
                break;
            //<------------音乐区------------->
            case DEF_VIDEO_TYPE.YIN_YUE:
                url = URL_YIN_YUE;
                break;
            case DEF_VIDEO_TYPE.FAN_CHANG:
                url = URL_FAN_CHANG;
                break;
            case DEF_VIDEO_TYPE.VOCALOID_UTAU:
                url = URL_VOCALOID_UTAU;
                break;
            case DEF_VIDEO_TYPE.YAN_ZOU:
                url = URL_YAN_ZOU;
                break;
            case DEF_VIDEO_TYPE.YIN_YUE_XUAN_JI:
                url = URL_YIN_YUE_XUAN_JI;
                break;
            //<------------舞蹈区------------->
            case DEF_VIDEO_TYPE.WU_DAO:
                url = URL_WU_DAO;
                break;
            case DEF_VIDEO_TYPE.ZHAI_WU:
                url = URL_ZHAI_WU;
                break;
            case DEF_VIDEO_TYPE.SAN_CHIYUAN_WU_DAO:
                url = URL_SAN_CHIYUAN_WU_DAO;
                break;
            case DEF_VIDEO_TYPE.WU_DAO_JIAOCHENG:
                url = URL_WU_DAO_JIAOCHENG;
                break;
            //<------------科技区------------->
            case DEF_VIDEO_TYPE.KE_JI:
                url = URL_KE_JI;
                break;
            case DEF_VIDEO_TYPE.JI_LU_PIAN:
                url = URL_JI_LU_PIAN;
                break;
            case DEF_VIDEO_TYPE.KE_PU_REN_WEN:
                url = URL_KE_PU_REN_WEN;
                break;
            case DEF_VIDEO_TYPE.YE_SHENG_JI_SHU:
                url = URL_YE_SHENG_JI_SHU;
                break;
            case DEF_VIDEO_TYPE.YAN_JIANG:
                url = URL_YAN_JIANG;
                break;
            case DEF_VIDEO_TYPE.JUN_SHI:
                url = URL_XIN_HAI;
                break;
            case DEF_VIDEO_TYPE.SHU_MA:
                url = URL_SHU_MA;
                break;
            case DEF_VIDEO_TYPE.JI_XIE:
                url = URL_JI_XIE;
                break;
            //<------------娱乐区------------->
            case DEF_VIDEO_TYPE.YU_LE:
                url = URL_YU_LE;
                break;
            case DEF_VIDEO_TYPE.GAO_XIAO:
                url = URL_GAO_XIAO;
                break;
            case DEF_VIDEO_TYPE.SHENG_HUO:
                url = URL_SHENG_HUO;
                break;
            case DEF_VIDEO_TYPE.DONG_WU:
                url = URL_DONG_WU;
                break;
            case DEF_VIDEO_TYPE.MEI_SHI:
                url = URL_MEI_SHI;
                break;
            case DEF_VIDEO_TYPE.ZONG_YI:
                url = URL_ZONG_YI;
                break;
            case DEF_VIDEO_TYPE.YU_LE_QUAN:
                url = URL_YU_LE_QUAN;
                break;
            case DEF_VIDEO_TYPE.KOREA:
                url = URL_KOREA;
                break;
            //<------------电影区------------->
            case DEF_VIDEO_TYPE.DIAN_YIN:
                url = URL_DIAN_YIN;
                break;
            case DEF_VIDEO_TYPE.DIAN_YIN_XIANG_GUANG:
                url = URL_DIAN_YIN_XIANG_GUANG;
                break;
            case DEF_VIDEO_TYPE.DUAN_PIAN:
                url = URL_DUAN_PIAN;
                break;
            case DEF_VIDEO_TYPE.OU_MEI_DIAN_YIN:
                url = URL_OU_MEI_DIAN_YIN;
                break;
            case DEF_VIDEO_TYPE.RI_BEN_DIAN_YIN:
                url = URL_RI_BEN_DIAN_YIN;
                break;
            case DEF_VIDEO_TYPE.GUO_CHAN_DIAN_YIN:
                url = URL_GUO_CHAN_DIAN_YIN;
                break;
            case DEF_VIDEO_TYPE.QI_TA_GUOJIA:
                url = URL_QI_TA_GUOJIA;
                break;
            //<------------游戏区------------->
            case DEF_VIDEO_TYPE.YOU_XI:
                url = URL_YOU_XI;
                break;
            case DEF_VIDEO_TYPE.DIAN_JI:
                url = URL_DIAN_JI;
                break;
            case DEF_VIDEO_TYPE.WANG_LUO_DIAN_YIN:
                url = URL_WANG_LUO_DIAN_YIN;
                break;
            case DEF_VIDEO_TYPE.YIN_YOU:
                url = URL_YIN_YOU;
                break;
            case DEF_VIDEO_TYPE.MUGEN:
                url = URL_MUGEN;
                break;
            case DEF_VIDEO_TYPE.GMV:
                url = URL_GMV;
                break;
            //<------------鬼畜区------------->
            case DEF_VIDEO_TYPE.GUI_CU:
                url = URL_GUI_CU;
                break;
            case DEF_VIDEO_TYPE.GUI_CU_TIAOJIAO:
                url = URL_GUI_CU_TIAOJIAO;
                break;
            case DEF_VIDEO_TYPE.YING_MAD:
                url = URL_YING_MAD;
                break;
            case DEF_VIDEO_TYPE.REN_LI:
                url = URL_REN_LI;
                break;
            case DEF_VIDEO_TYPE.JIAOCHENG:
                url = URL_JIAOCHENG;
                break;
            //<------------电视剧区------------->
            case DEF_VIDEO_TYPE.DIAN_SHI_JU:
                url = URL_DIAN_SHI_JU;
                break;
            case DEF_VIDEO_TYPE.LIAN_ZAI_JU:
                url = URL_LIAN_ZAI_JU;
                break;
            case DEF_VIDEO_TYPE.WANG_JIE_JU:
                url = URL_WANG_JIE_JU;
                break;
            case DEF_VIDEO_TYPE.TE_SHE:
                url = URL_TE_SHE;
                break;
            case DEF_VIDEO_TYPE.DIAN_SHI_JU_XIANGUANG:
                url = URL_DIAN_SHI_JU_XIANGUANG;
                break;
            //<------------时尚区------------->
            case DEF_VIDEO_TYPE.SHI_SHANG:
                url = URL_SHI_SHANG;
                break;
            case DEF_VIDEO_TYPE.MEI_ZHUANG:
                url = URL_MEI_ZHUANG;
                break;
            case DEF_VIDEO_TYPE.FU_SHI:
                url = URL_FU_SHI;
                break;
            case DEF_VIDEO_TYPE.SHI_SHANG_ZHI_XUN:
                url = URL_SHI_SHANG_ZHI_XUN;
                break;
            //<------------排行榜------------->
            case 10070:
                url = URL_RANK_QUAN_QU;
                break;
            case 100733:
                url = URL_RANK_XIN_FAN;
                break;
            case 10071:
                url = URL_RANK_DONG_HUA;
                break;
            case 10073:
                url = URL_RANK_YIN_YUE;
                break;
            case 10074:
                url = URL_RANK_YOU_XI;
                break;
            case 100736:
                url = URL_RANK_KE_JI;
                break;
            case 10075:
                url = URL_RANK_YU_LE;
                break;
            case 100723:
                url = URL_RANK_DIAN_YING;
                break;
            default:
                break;
        }
        return url;
    }
    /**
     * 视频类型
     */
    public class DEF_VIDEO_TYPE {
        // 动画区
        public static final int DONG_HUA = 1;
        public static final int MAD_AMV = 24;	// MAD.AMV
        public static final int MMD_3D = 25;	// MMD.3D
        public static final int DONG_HUA_DUAN_PIAN = 47;	// 动画短片
        public static final int DONG_HUA_ZONG_HE = 27;		// 动画综合
        // 番剧区
        public static final int FAN_JU = 13;
        public static final int LIAN_ZAI_DONG_HUA = 33; // 连载动画
        public static final int WAN_JIE_DONG_HUA = 32;	// 完结动画
        public static final int ZHI_XUN = 51;	// 资讯
        public static final int GUAN_FANG_YAN_SHEN = 152;	// 官方延伸
        public static final int GUO_CHAN_DONG_HUA = 153;	// 国产动画
        // 音乐区
        public static final int YIN_YUE = 3;
        public static final int FAN_CHANG = 31;	// 翻唱
        public static final int VOCALOID_UTAU = 30;	// VOCALOID-UTAU
        public static final int YAN_ZOU = 59;	// 演奏
        public static final int YIN_YUE_XUAN_JI = 130;	//音乐选集
        // 舞蹈区
        public static final int WU_DAO = 129;
        public static final int ZHAI_WU = 20;	// 宅舞
        public static final int SAN_CHIYUAN_WU_DAO = 154;	//三次元舞蹈
        public static final int WU_DAO_JIAOCHENG = 156;	// 舞蹈教程
        //科技区
        public static final int KE_JI = 36;
        public static final int JI_LU_PIAN = 37;	//纪录片
        public static final int KE_PU_REN_WEN = 124;	//科普人文
        public static final int YE_SHENG_JI_SHU = 122;	//野生技术
        public static final int YAN_JIANG = 39;	//演讲·公开课
        public static final int JUN_SHI = 96;	//军事
        public static final int SHU_MA = 95;	//数码
        public static final int JI_XIE = 98;	//机械
        //娱乐
        public static final int YU_LE = 5;
        public static final int GAO_XIAO = 138;	//搞笑
        public static final int SHENG_HUO = 21;	//生活
        public static final int DONG_WU = 75;	//动物
        public static final int MEI_SHI = 76;	//美食
        public static final int ZONG_YI = 71;	//综艺
        public static final int YU_LE_QUAN = 137;	//娱乐圈
        public static final int KOREA = 131;	//KOREA相关
        //电影
        public static final int DIAN_YIN = 23;
        public static final int DIAN_YIN_XIANG_GUANG = 82;	//电影相关
        public static final int DUAN_PIAN = 85;	//短片
        public static final int OU_MEI_DIAN_YIN = 145;	//欧美电影
        public static final int RI_BEN_DIAN_YIN = 146;	//日本电影
        public static final int GUO_CHAN_DIAN_YIN = 147;	//国产电影
        public static final int QI_TA_GUOJIA = 83;	//其他国家
        //游戏
        public static final int YOU_XI = 4;
        public static final int DIAN_JI = 17;	//单机联机
        public static final int WANG_LUO_DIAN_YIN = 65;	//网络电竞
        public static final int YIN_YOU = 136;	//音游
        public static final int MUGEN = 19;	//MUGEN
        public static final int GMV = 121;	//GMV
        //鬼畜
        public static final int GUI_CU = 119;
        public static final int GUI_CU_TIAOJIAO = 22;	//鬼畜调教
        public static final int YING_MAD = 26;	//音MAD
        public static final int REN_LI = 126;	//人力vocaloid
        public static final int JIAOCHENG = 127;	//教程演示
        //电视剧
        public static final int DIAN_SHI_JU = 11;
        public static final int LIAN_ZAI_JU = 15;	//连载剧集
        public static final int WANG_JIE_JU = 34;	//完结剧集
        public static final int TE_SHE = 86;	//特摄·布袋
        public static final int DIAN_SHI_JU_XIANGUANG = 128;	//电视剧相关
        //时尚
        public static final int SHI_SHANG = 155;
        public static final int MEI_ZHUANG = 157;	//美妆健身
        public static final int FU_SHI = 158;	//服饰
        public static final int SHI_SHANG_ZHI_XUN = 159;	//时尚资讯
    }

}
