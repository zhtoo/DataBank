package com.hs.mydatabinding.retrofit.exception;

/**
 * App返回code常量类
 * code常量根据实际需求更改
 *
 * @author Kunkka
 */
public class AppResultCode {
    /**
     * 代码级别的错误
     */
    public static final int    EX_SYSTEM_ERROR              = 0x0000;
    /**
     * 业务级别的错误
     */
    public static final int    EX_BUSINESS_WARN             = 0x0000;
    /**
     * 成功
     */
    public static final int    EX_SUCCESS                   = 0x0001;
    /**
     * token过期
     */
    public static final int    EX_TOKEN_TIMEOUT             = 0x0000;
    /**
     * token不存在，无效的token
     */
    public static final int    EX_TOKEN_INVALID             = 0x0000;
    /**
     * 请求数据为空
     */
    public static final int    EX_EMPTY_RESULTSET           = 0x0001;
    /**
     * 成功
     */
    public static final int    SUCCESS                      = 0x0001;
    public static final String SUCCESS_9999                 = "9999";
    /**
     * 代码级别的错误
     */
    public static final int    ERROR                        = 0x0500;
    /**
     * 流程级别的错误
     */
    public static final int    ACCESS_BARRED                = 0x0403;
    /**
     * 业务级别的错误
     */
    public static final int    ERR_BUSINESS                 = 0x0000;
    /**
     * 请求数据为空
     */
    public static final int    EMPTY_RESULTSET              = 0x0001;
    /**
     * appkey不正确
     */
    public static final int    ERR_APPKEY_INVALID           = 0x0101;
    /**
     * 签名不正确
     */
    public static final int    ERR_SINGA                    = 0x0102;
    /**
     * token过期
     */
    public static final int    TOKEN_TIMEOUT                = 0x0103;
    /**
     * token不存在，无效的token
     */
    public static final int    TOKEN_INVALID                = 0x0104;
    /**
     * 丢失（缺失）的token
     */
    public static final int    TOKEN_MISS                   = 0x0105;
    /**
     * 长期不在线,token失效
     */
    public static final int    TOKEN_LOSE_EFFICACY          = 0x0106;
    /**
     * 用户不存在，无效的用户id
     */
    public static final int    ERR_USER_INVALID             = 0x0201;
    /**
     * 用户名格式不正确
     */
    public static final int    ERR_USERNAME_INVALID         = 0x0202;
    /**
     * 用户名被占用
     */
    public static final int    ERR_USERNAME_USED            = 0x0203;
    /**
     * 手机号格式错误
     */
    public static final int    ERR_PHONE_INVALID            = 0x0204;
    /**
     * 手机号被占用
     */
    public static final int    ERR_PHONE_USED               = 0x0205;
    /**
     * 手机未认证
     */
    public static final int    ERR_PHONE_NOT_AUTH           = 0x0206;
    /**
     * 邮箱地址不正确
     */
    public static final int    ERR_EMAIL_INVALID            = 0x0207;
    /**
     * 邮箱被占用
     */
    public static final int    ERR_EMAIL_USED               = 0x0208;
    /**
     * 邮箱未认证
     */
    public static final int    ERR_EMAIL_NOT_AUTH           = 0x0209;
    /**
     * 未实名认证
     */
    public static final int    ERR_REALNAME_NOT_AUTH        = 0x0210;
    /**
     * 验证码不正确
     */
    public static final int    ERR_CODE_INVALID             = 0x0211;
    /**
     * 验证码过期
     */
    public static final int    ERR_CODE_TIMEOUT             = 0x0212;
    /**
     * 密码格式不正确
     */
    public static final int    ERR_PWD_INVALID              = 0x0213;
    /**
     * 用户被锁定，多次键入登录密码错误
     */
    public static final int    ERR_USER_LOCKED_CAUSE_PWD    = 0x0214;
    /**
     * 用户被锁定，多次键入支付密码错误
     */
    public static final int    ERR_USER_LOCKED_CAUSE_PAYPWD = 0x0215;
    /**
     * 错误的用户类型（类型不为投资人的用户尝试登录app）
     */
    public static final int    ERR_USER_TYPE_NOT_ALLOWED    = 0x0216;
    /**
     * 键入密码不正确
     */
    public static final int    ERR_PWD_NOT_CORRECT          = 0x0217;
    /**
     * 键入支付密码不正确
     */
    public static final int    ERR_PAYPWD_NOT_CORRECT       = 0x0218;
    /**
     * 短信接口功能性问题
     */
    public static final int    ERR_SENT_MSG_FAIL            = 0x0219;
    /**
     * 查询標不存在
     */
    public static final int    ERR_BORROW_INVALID           = 0x0220;
    /**
     * 用户权限被冻结
     */
    public static final int    ERR_USER_FREEZE              = 0x0221;
    /**
     * 用户不是新手，不能投资新手标
     */
    public static final int    ERR_USER_NOT_NOVICE          = 0x0222;
    /**
     * 用户未设置支付密码
     */
    public static final int    ERR_PAYPWD_INVALID           = 0x0223;
    /**
     * 密码不能与支付密码一致
     */
    public static final int    ERR_PWD_REPEAT               = 0x0224;
    /**
     * 错误的银行id
     */
    public static final int    ERR_BANK_ID                  = 0x0225;
    /**
     * 身份证被占用
     */
    public static final int    ERR_CARD_ID_USED             = 0x0226;
    /**
     * 验证码已发送，60秒后重新获取
     */
    public static final int    ERR_SEND_CODE                = 0x0227;
    /**
     * 请求的接口版本号过低
     */
    public static final int    ERR_INTERFACE_VERSION        = 0x0228;
    /**
     * 找不到文件
     */
    public static final int    ERR_NOT_FOUND                = 0x0404;
    /**
     * token不匹配
     */
    public static final int    TOKEN_UNMATE                 = 0x403;
}
