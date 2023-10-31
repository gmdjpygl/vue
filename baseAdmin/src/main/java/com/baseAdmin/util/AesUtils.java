package com.baseAdmin.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
//import sodotek.triton.data.exception.RbException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @program: triton
 * @description: 加密工具类
 * @author: menglingfei
 * @date: 2021-10-29 08:52
 **/
public class AesUtils {

    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 数据加密
     *
     * @param encryptKey    秘钥
     * @param plainTextData 待加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static String encryptAES(String encryptKey, String plainTextData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = encryptKey.getBytes("utf-8");
        IvParameterSpec iv = new IvParameterSpec(raw);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(raw, "AES"), iv);
        byte[] encryptedData = cipher.doFinal(plainTextData.getBytes("UTF-8"));
        return Base64.encodeBase64String(encryptedData);
    }

    /**
     * 数据解密
     *
     * @param encryptKey    秘钥
     * @param encryptedData 加密的数据
     * @return 解密过后的数据
     * @throws Exception
     */
    public static String decryptAES(String encryptKey, String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = encryptKey.getBytes("utf-8");
        IvParameterSpec iv = new IvParameterSpec(raw);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(raw, "AES"), iv);
        byte[] decryptedData = cipher.doFinal(Base64.decodeBase64(encryptedData));
        return new String(decryptedData, "UTF-8");
    }

    public static String encryptAESByte(String encryptKey, byte[] plainData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = encryptKey.getBytes("utf-8");
        IvParameterSpec iv = new IvParameterSpec(raw);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(raw, "AES"), iv);
        byte[] encryptedData = cipher.doFinal(plainData);
        return Base64.encodeBase64String(encryptedData);
    }

    public static byte[] decryptAESByte(String encryptKey, String fileData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = encryptKey.getBytes("utf-8");
        IvParameterSpec iv = new IvParameterSpec(raw);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(raw, "AES"), iv);
        return cipher.doFinal(Base64.decodeBase64(fileData));
    }

    /**
     * 签名
     *
     * @param data 加密后的数据
     * @param salt 盐
     * @return 签名
     * @throws Exception
     */
    public static String sign(String data, String salt) {
        data = data + "|" + salt;
        byte[] bytes;
        try {
            bytes = MessageDigest.getInstance("MD5").digest(data.getBytes("UTF-8"));
        } catch (Exception e) {
            return null;
        }
        return byteToString(bytes);
    }


    public static String decryptData(String data, String sign, String encryptKey, String salt) throws Exception {
        String rightSign = "";
        try {
            rightSign = AesUtils.sign(data, salt);
        } catch (Exception e) {
            throw new Exception("验签失败", e);
        }
        if (sign.equals(rightSign)) {
        }
        String requestDataJson;
        try {
            requestDataJson = AesUtils.decryptAES(encryptKey, data);
        } catch (Exception e) {
            throw new Exception("数据解密失败，请检查key", e);
        }
        return requestDataJson;
    }

    private static String byteToString(byte[] bByte) {
        StringBuilder sb = new StringBuilder();
        for (byte aBByte : bByte) {
            sb.append(byteToArrayString(aBByte));
        }
        return sb.toString();
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return HEX_DIGITS[iD1] + HEX_DIGITS[iD2];
    }

    /**
     * 随机生成秘钥
     */
    public static String getKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        String s = byteToString(b);
        return s.substring(0, 16);
    }


    public static void main(String[] args) throws Exception {
        e();

        //String key = getKey();
        String key="JuYhvCRuhiv6Ccyo";
        System.out.println("key=" + key);
        /**
        Map<String,String> data = new HashMap<>();
        data.put("assetId","4301040205201007011002");
        data.put("assetType","SBZ1");
**/

        /**
        List<Object> assetType = new ArrayList<>();
        assetType.add("YWJ1");
        List<Object> semaphoreType = new ArrayList<>();
        semaphoreType.add("ywj1:level");**/
        Map<String,Object> data = new HashMap<>();
        data.put("assetType","YWJ1");
        data.put("semaphoreType","ywj1:level");
        data.put("startTime","1648742400");
        data.put("endTime","1648828800");

       // String data = "csps-hd-Cjh-CjhYwj3Val";
        String jsonString =  JSON.toJSONString(data);
        System.out.println("data:"+jsonString);
        String encryptText = encryptAES(key, jsonString);
        System.out.println("encry:" + encryptText);
        String text = decryptAES(key, encryptText);
        System.out.println("decrypt:" + text);
        String s = sign(encryptText,"2021$Triton");
        System.out.println("sign:"+s);
        System.out.println((new Date().getTime()));
    }
    public static void e() throws Exception {
        String key="JuYhvCRuhiv6Ccyo";
        String s = "WKB3kq3CohCU2pxQyVG3v+Oi+LJRaysYjXFAJxQlmDjorGfYqKhpruZjZiVlHSbHfFVXQfjZ7H9S1QsAvZTK9QZhOiWT0Zbp+jkqspqP/Jt8ZG5d8ozu7PWMLcAqP9pELyRlTYv6rEGwx9S7pU02mg==";
        String a = decryptAES(key,s);
        System.out.println(a);
    }
}

