package com.hs.mydatabinding.retrofit;

/**
 * Description: 网络请求参数名字典类
 * 具体参数可以根据项目进行修改
 * 建议不要和BaseParams类混在一起
 */
public class RequestParams {
    ///////////////////////////////////////////////////////////////////////////
    // common参数
    ///////////////////////////////////////////////////////////////////////////
    public static final String APP_KEY            = "appkey";
    public static final String SIGNA              = "signa";
    public static final String TS                 = "ts";
    public static final String MOBILE_TYPE        = "mobileType";
    public static final String VERSION_NUMBER     = "versionNumber";
    // 登录参数
    public static final String TOKEN              = "oauthToken";
    public static final String USER_ID            = "userId";
    public static final String REFRESH_TOKEN      = "refresh_token";
    ///////////////////////////////////////////////////////////////////////////
    // 登录请求参数
    ///////////////////////////////////////////////////////////////////////////
    public static final String ID                 = "id";
    public static final String PWD                = "pwd";
    public static final String PHONE              = "phone";
    public static final String CODE               = "code";
    public static final String SCORE               = "score";
    /**
     * IMEI相关
     */
    public static final String ACKTOKEN           = "ackToken";
    public static final String ACKAPPKEY          = "ackAppkey";
    public static final String ACKAPPKEY_NUM      = "291DE5F14A880DBC78A401856EE5771C";
    //分页参数
    public static final String PAGE               = "page";
    public static final String PAGE_SIZE          = "pagesize";
    //产品相关
    public static final String UUID               = "uuid";
    public static final String HUI_JXQ_MONEY      = "hui_jxq_money";
    public static final String MONEY              = "money";
    public static final String BANKCODE           = "bankCode";
    public static final String DIRPWD             = "pwd";
    public static final String PAYPASSWORD        = "paypwd";
    public static final String REDIDS             = "red_ids";
    public static final String EXPIDS             = "experience_ids";
    public static final String UPIDS              = "up_rate_id";
    public static final String SESSION_ID         = "session_id";
    public static final String NEW_PWD            = "new_pwd";
    public static final String OLD_PWD            = "old_pwd";
    public static final String NEW_PAYPWD         = "new_paypwd";
    public static final String OLD_PAYPWD         = "old_paypwd";
    //实名认证
    public static final String REALNAME           = "realname";
    public static final String CARDID             = "card_id";
    //消息
    public static final String TYPE               = "type";
    public static final String TIMESEARCH         = "timeSearch";
    public static final String ISDXB              = "isDXB";
    //记录相关
    public static final String STATUS             = "status";
    public static final String TENDER_ID          = "tender_id";
    //双乾授权
    public static final String AUTHORIZE_TYPE     = "type";
    public static final String AUTHORIZE_ONOFF    = "on_off";
    //重置密码
    public static final String RESETPAYPWD_NEWPWD = "new_paypwd";
    public static final String RESETPAYPWD_IDCARD = "id_card";
    public static final String SETPAYPWD_PWD      = "payPwd";
    public static final String PHONE_OR_EMAIL     = "phone_or_email";
    //上传头像
    public static final String IMGURL             = "imgUrl";
    //自动投标相关
    public static final String enable             = "enable";
    //红包拆分
    public static final String redEnvelopeSeparateMoney = "redEnvelopeSeparateMoney";
}
