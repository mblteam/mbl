package com.mbl.common.util; 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mbl.common.constant.UtilConstant;

/**
 * LTPA token工具
 * @author zl
 * @create 2015年12月2日 下午2:08:45 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TokenUtil {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TokenUtil.class);

	// 私有密钥
	private static final String secretKey = "2dH5a81YepbVe+gF1XVUzx4C1Ca5g6A4Q63OFobJV9g=";

	// 过期时间(1个月)
	private static int durationMinutes = 21600;

	private static final String CHARSET = "Cp850";

	/**
	 * 
	 * 〈一句话功能简述〉 功能详细描述
	 * 
	 * @param username
	 * @param createTime
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static String createToken(String userid, GregorianCalendar createTime) {

		String tokenVersion = "0123";
		String userInfo = userid;
		byte[] version = tokenVersion.getBytes();

		String creationStr = Integer.toHexString((int) (createTime
				.getTimeInMillis() / UtilConstant.THOUSAND));

		byte[] creation = creationStr.getBytes();

		// 获得过期时间，过期时间=当前时间+到期时间(分钟)
		GregorianCalendar expirationDate = (GregorianCalendar) createTime
				.clone();
		expirationDate.add(Calendar.MINUTE, durationMinutes);

		String expirationStr = Integer.toHexString((int) (expirationDate
				.getTimeInMillis() / UtilConstant.THOUSAND));

		byte[] expiration = expirationStr.getBytes();

		byte[] userInfoByte = userInfo.getBytes();

		byte[] secretByte = secretKey.getBytes();

		byte[] workingBuffer = new byte[UtilConstant.PRE_USER_LENGTH
				+ userInfoByte.length + secretByte.length];

		System.arraycopy(version, 0, workingBuffer, 0, version.length);
		System.arraycopy(creation, 0, workingBuffer, version.length,
				creation.length);
		System.arraycopy(expiration, 0, workingBuffer, version.length
				+ creation.length, expiration.length);
		System.arraycopy(userInfoByte, 0, workingBuffer,
				UtilConstant.PRE_USER_LENGTH, userInfoByte.length);
		System.arraycopy(secretByte, 0, workingBuffer,
				UtilConstant.PRE_USER_LENGTH + userInfoByte.length,
				secretByte.length);

		// 进行 SHA-1 校验和

		MessageDigest md = null;

		try {

			md = MessageDigest.getInstance("SHA-1");

			md.reset();

		} catch (NoSuchAlgorithmException e) {

			LOGGER.info("Message digest exception", e);
		}

		byte[] hash = null;
		if (md != null) {
			hash = md.digest(workingBuffer);
		}
		byte[] outputBuffer = new byte[UtilConstant.PRE_USER_LENGTH
				+ userInfoByte.length + hash.length];

		System.arraycopy(workingBuffer, 0, outputBuffer, 0,
				UtilConstant.PRE_USER_LENGTH + userInfoByte.length);
		System.arraycopy(hash, 0, outputBuffer, UtilConstant.PRE_USER_LENGTH
				+ userInfoByte.length, hash.length);

		return new String(Base64.encodeBase64(outputBuffer));

	}

	/**
	 * 
	 * token校验
	 * 
	 * @param token
	 *            token String
	 * @return userid
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static String validateToken(String token) {

		boolean result = true;
		byte[] ltpa = Base64.decodeBase64(token.getBytes());
		byte[] sha = new byte[UtilConstant.PRE_USER_LENGTH];

		int userInfoLength = ltpa.length - UtilConstant.PRE_LENGTH;
		byte[] header = new byte[UtilConstant.FOUR];
		byte[] creation = new byte[UtilConstant.EIGHT];
		byte[] expires = new byte[UtilConstant.EIGHT];
		byte[] userInfo = new byte[userInfoLength];

		ByteArrayInputStream stream = new ByteArrayInputStream(ltpa);

		// 读取版本号

		stream.read(header, 0, UtilConstant.FOUR);

		if (header[0] != '0' || header[1] != '1' || header[2] != '2'
				|| header[UtilConstant.INDEX_THR] != '3') {
			result = false;
		}
		// 读取开始时间
		stream.read(creation, 0, UtilConstant.EIGHT);

		// 读取到期时间
		stream.read(expires, 0, UtilConstant.EIGHT);
		// 校验到期时间,过期时间小于当前时间,则过期
		String expiresStr = new String(expires);
		int ex = Integer.valueOf(expiresStr, UtilConstant.HEX);
		int now = (int) ((new GregorianCalendar()).getTimeInMillis())
				/ UtilConstant.THOUSAND;
		if (ex < now) {
			// 过期
			result = false;
		}
		// 读取用户信息
		stream.read(userInfo, 0, userInfoLength);
		char[] characters = new char[userInfoLength];

		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new ByteArrayInputStream(userInfo),
					CHARSET);
			isr.read(characters);
		} catch (UnsupportedEncodingException e1) {
			LOGGER.info("validite token unsupportedcodingException", e1);
		} catch (IOException e) {
			LOGGER.info("validite token ioexception", e);
		}

		String userid = new String(characters);

		if (!result) {
			return null;
		}
		// 读取SHA校验和
		stream.read(sha, 0, UtilConstant.PRE_USER_LENGTH);

		// 创建LTPA Token
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();

		try {
			// Token版本号
			ostream.write(header);
			// 创建时间
			ostream.write(creation);
			// 过期时间
			ostream.write(expires);
			// user
			ostream.write(userInfo);
			//
			ostream.write(secretKey.getBytes());
			ostream.close();
		} catch (IOException e) {
			LOGGER.info("validite token ostream ioexception", e);
		}

		// 进行 SHA-1 校验和
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			md.reset();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.info("validite token occur nosuch algorithm exception", e);
		}

		byte[] digest = null;
		if (md != null) {
			digest = md.digest(ostream.toByteArray());
		}
		boolean valid = MessageDigest.isEqual(digest, sha);
		if (valid) {
			return userid;
		} else {
			return null;
		}

	}
	
	public static void main(String[] args) {
		System.out.println(createToken("e2fbc730-d158-4f8a-a73e-ef8f1bd249dc", new GregorianCalendar()));
	}
}
