package com.studywithme.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncodeUtil {
	public static String toSHA1(String toEncrypt) {
	    return toSHA1(toEncrypt, "UTF-8");
	}

	public static String toSHA1(String toEncrypt, String encoding) {
	    String salt = "dsjfhsuidhfernbtjk@$*&#(*&";
	    String res = null;
	    toEncrypt = toEncrypt + salt;
	    try {
	        byte[] dataBytes = toEncrypt.getBytes(encoding);
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        res = Base64.encodeBase64String(md.digest(dataBytes));
	    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    return res;
	}
}
