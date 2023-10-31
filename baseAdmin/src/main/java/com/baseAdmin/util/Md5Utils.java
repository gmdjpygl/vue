package com.baseAdmin.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 提供Md5相关工具
 *
 * @author wangyajun
 */
public class Md5Utils {

    /**
     * 计算sig是否合法
     *
     * @param sig
     * @param params
     * @return
     */
    public static boolean isSigInvalid(String sig, Object... params) {
        String md5Server = encrypt(params);
        return md5Server.equals(sig);
    }

    /**
     * 获取Md5值
     *
     * @param params
     * @return
     */
    public static String encrypt(Object... params) {
        StringBuffer sb = new StringBuffer();
        for (Object object : params) {
            sb.append(object.toString());
        }
        return encrypt(sb.toString());
    }

    public static String encrypt(byte[] inputBytes) {
        // 得到MessageDigest对象
        MessageDigest md = null;
        // 加密后的字符串
        String strDes = null;
        // 要加密的字符串字节型数组
        try {

            md = MessageDigest.getInstance("MD5");
            md.update(inputBytes);
            // 通过执行诸如填充之类的最终操作完成哈希计算
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    /**
     * 获取Md5值
     *
     * @param strSrc
     * @return
     */
    public static String encrypt(String strSrc) {
        // parameter strSrc is a string will be encrypted,
        // parameter encName is the algorithm name will be used.
        // encName dafault to "MD5"
        // 得到MessageDigest对象
        MessageDigest md = null;
        // 加密后的字符串
        String strDes = null;
        // 要加密的字符串字节型数组

        try {
            byte[] bt = strSrc.getBytes("UTF-8");
            md = MessageDigest.getInstance("MD5");
            md.update(bt);
            // 通过执行诸如填充之类的最终操作完成哈希计算
            strDes = bytes2Hex(md.digest());
        } catch (Exception e) {
            return null;
        }
        return strDes;
    }

    /**
     * 将字节数组转换成16进制的字符串
      */
    private static String bytes2Hex(byte[] bts) {
        StringBuffer desBuffer = new StringBuffer();
        String tmp = null;

        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                desBuffer.append("0");
            }
            desBuffer.append(tmp);
        }
        return desBuffer.toString();
    }
    public static String stringToMD5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有这个md5算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
    public static void main(String[] args) {
       String test = "dddddddddddddddddddddddddddddddddddddddddd";
        System.out.println(stringToMD5(test));
    }

}
