package com.mbl.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 该类是加密处理类.主要用来对数据进行加密,解密操作
 * @author zl
 * @create 2015年12月2日 下午2:08:11 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SymmetricCrypto {
    private final static String init_password = "Km-SSO";
    /**
     * 密钥文件路径
     */
    private final static String KEY_FILE_PATH = "Km_SSO_Key";
    /**
     * 对称加解密需要的密钥
     */
    private static Key key = null;
    /**
     * 对称密钥算法
     */
    private final static String KEY_ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
     */
    private final static String TRANS_FORMATION = "AES/ECB/PKCS5Padding";
    private final static String ENCODING_UTF_8 = "UTF-8";
    /**
     * 迭代次数
     */
    private final static int ITERATIONS = 1000;

    /**
     * 将指定的数据根据提供的密钥进行对称加密
     * @param data 需要加密的数据
     * @return byte[] 加密后的数据
     * @throws Exception
     */
    public static byte[] doEncrypt(byte[] data) throws Exception {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
            //初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, loadKey());
            //执行操作
            byte[] raw = cipher.doFinal(data);
            return raw;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("对密钥进行加密时异常" + e);
        }
    }

    /**
     * 将指定的数据根据提供的密钥进行对称加密<br></br>
     * 返回的数据为加密后数据并进行Base64转码
     * @param data 需要加密的数据
     * @return String 加密数据Base64
     * @throws Exception
     */
    public static String doEncryptAndBase64(byte[] data) throws Exception {
        byte[] encByte = doEncrypt(data);
        return new String(Base64.encodeBase64(encByte));
    }

    /**
     * 将指定的数据根据提供的密钥进行对称加密<br></br>
     * 返回的数据为加密后数据并进行Base64转码
     * @param data 需要加密的数据
     * @return String 加密数据Base64
     * @throws Exception
     */
    public static String doEncryptAndBase64(String data) throws Exception {
        byte[] encByte = doEncrypt(data.getBytes(ENCODING_UTF_8));
        return new String(Base64.encodeBase64(encByte));
    }

    /**
     * 将给定的数据通过指定的密钥进行对称解密
     * @param raw 待解密的数据
     * @return byte[] 解密后的数据
     * @throws Exception
     */
    public static byte[] doDecrypt(byte[] raw) throws Exception {
        try {
            // Get a cipher object
            Cipher cipher = Cipher.getInstance(TRANS_FORMATION);
            //初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, loadKey());
            //执行操作
            byte[] data = cipher.doFinal(raw);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("对数据进行解密时异常");
        }
    }

    /**
     * 将给定的数据新Base64解码，然后进行对称解密
     * @param data 待解密的数据
     * @return byte[] 解密后的数据
     * @throws Exception
     */
    public static byte[] doDecoderAndDecrypt(String data) throws Exception {
        byte[] dataTemp = doDecrypt(Base64.decodeBase64(data));
        return dataTemp;
    }

    /**
     * 将给定的数据新Base64解码，然后进行对称解密
     * @param data 待解密的数据
     * @return String 解密后的数据,转换为UTF-8
     * @throws Exception
     */
    public static String doDecoderAndDecrypt2(String data) throws Exception {
        byte[] dataTemp = doDecoderAndDecrypt(data);
        return new String(dataTemp, ENCODING_UTF_8);
    }

    /**
     * 在对文件进行加解密前，需要获得所需的密钥。
     * loadkey()将用来装入密钥，这个方法需用指定的口令作参数.
     * 从本质上来说loadkeyv()与createkeyv()相反：它首先需要读入盐和加密的密钥字节，然后用一个PBE cipher解密这个密钥。
     * 我们将使用javax.crypto.spec.SecretKeySpec从解密的密钥的字节中创建一个密钥。
     * @return
     * @throws Exception
     */
    private static Key loadKey() {
        if (key != null){
            return key;
        }
        //Load the bytes from the encrypted key file.
        try {
//            InputStream fis = SymmetricCrypto.class.getResourceAsStream(KEY_FILE_PATH);
            InputStream fis = SymmetricCrypto.class.getClassLoader().getResourceAsStream(KEY_FILE_PATH);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i = 0;
            while((i = fis.read()) != -1){
                baos.write(i);
            }
            fis.close();

            byte[] saltAndKeyBytes = baos.toByteArray();
            baos.close();

            //Get the salt, which is the first 8 bytes
            byte[] salt = new byte[8];
            System.arraycopy(saltAndKeyBytes, 0, salt, 0, 8);

            //Get the encrypted key bytes
            int length = saltAndKeyBytes.length - 8;
            byte[] encryptedKeyBytes = new byte[length];
            System.arraycopy(saltAndKeyBytes, 8, encryptedKeyBytes, 0, length);

            //Create the PBE clipher
            PBEKeySpec pbeKeySpec = new PBEKeySpec(init_password.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFactory.generateSecret(pbeKeySpec);
            PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, ITERATIONS);
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);

            //Decrypt the key bytes
            byte[] decryptedKeyBytes = cipher.doFinal(encryptedKeyBytes);

            //我们将使用javax.crypto.spec.SecretKeySpec根据给定的字节数组(密钥的密钥内容)和与给定的密钥内容相关联的密钥算法的名称构造一个密钥
            //从解密的密钥的字节中创建一个密钥
            //Create the key from the key bytes
            SecretKeySpec secretKeySpec = new SecretKeySpec(decryptedKeyBytes, KEY_ALGORITHM);
            key = secretKeySpec;
            return key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * createKey()方法，产生一个AES密钥，并存储在文件中
     * Create a 128-bit AES key and stores it to the filesystem as a keyStore.
     * @throws Exception
     */
	@SuppressWarnings("unused")
	private static void createKey() throws Exception{
        System.out.println("Generating a AES key...");
        String keyFileName = "E:/Km_SSO_Key";
        //实例化密钥生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥生成器 AES要求密钥长度为128,192,256位
        keyGenerator.init(128);
        //使用密钥生成器生成密钥
        Key key = keyGenerator.generateKey();

        System.out.println("Done generating the key.");

        //现在我们用一个口令加密这个密钥。首先用安全随机数创建一个8字节的盐并用这个口令创建一个PBE cipher

        byte[] salt = initSalt();
        //密钥转换
        PBEKeySpec pbeKeySpec = new PBEKeySpec(init_password.toCharArray());

        //密钥工厂用来将密钥（类型 Key 的不透明加密密钥）转换为密钥规范（底层密钥材料的透明表示形式），反之亦然。秘密密钥工厂只对秘密（对称）密钥进行操作
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey pbeKey = keyFactory.generateSecret(pbeKeySpec);
        //实例化PBE参数材料
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, ITERATIONS);
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
        cipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

        //现在我们可以使用这个PBE cipher对密钥进行加密：
        //Encrypt the key
        byte[] encryptedKeyBytes = cipher.doFinal(key.getEncoded());

        //要解密这个密钥，需要对应的盐。我们把制造的盐写在文件的头8个字节中，然后写已加密的密钥，并关闭文件。
        //Write out the salt, and then the encrypted key bytes
        FileOutputStream fos = new FileOutputStream(keyFileName);
        fos.write(salt);
        fos.write(encryptedKeyBytes);
        fos.close();
    }

    /**
     * 盐初始化<br>
     * 盐长度必须为8字节
     * @return byte[] 盐
     */
    private static byte[] initSalt(){
        //实例化安全随机数
        SecureRandom random = new SecureRandom();
        //生产盐
        return random.generateSeed(8);
    }
}
