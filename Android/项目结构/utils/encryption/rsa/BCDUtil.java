package com.zht.newgirls.encryption.rsa;

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/4/7 10:24
 * <p/>
 * Description: BCD加密工具类
 */
public class BCDUtil {
    /**
     * BCD转字符串
     */
    public static String bcd2Str(byte[] bytes) {
        char temp[] = new char[bytes.length * 2], val;

        for (int i = 0; i < bytes.length; i++) {
            val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
            temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

            val = (char) (bytes[i] & 0x0f);
            temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }

    /**
     * ASCII码转BCD码
     */
    public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
        byte[] bcd = new byte[asc_len / 2];
        int    j   = 0;
        for (int i = 0; i < (asc_len + 1) / 2; i++) {
            bcd[i] = asc_to_bcd(ascii[j++]);
            bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
        }
        return bcd;
    }

    private static byte asc_to_bcd(byte asc) {
        byte bcd;

        if ((asc >= '0') && (asc <= '9')) {
            bcd = (byte) (asc - '0');
        } else if ((asc >= 'A') && (asc <= 'F')) {
            bcd = (byte) (asc - 'A' + 10);
        } else if ((asc >= 'a') && (asc <= 'f')) {
            bcd = (byte) (asc - 'a' + 10);
        } else {
            bcd = (byte) (asc - 48);
        }
        return bcd;
    }
}
