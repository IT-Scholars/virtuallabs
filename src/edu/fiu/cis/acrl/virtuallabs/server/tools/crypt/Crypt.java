package edu.fiu.cis.acrl.virtuallabs.server.tools.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Crypt {

	private static String key = "1234567890123456";
	
	public static String encrypt(String input){
		if ((input == null) || (input == ""))
			return null;
		
		byte[] crypted = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			crypted = cipher.doFinal(input.getBytes());
		} catch(Exception e){
			System.out.println(e.toString());
		}
		
		if (crypted == null)
			return null;
		else
			return new String(Base64.encodeBase64(crypted));
	}

	public static String decrypt(String input){
		if ((input == null) || (input == ""))
			return null;
		
		byte[] output = null;
		try{
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			output = cipher.doFinal(Base64.decodeBase64(input));
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		if (output == null)
			return null;
		else
			return new String(output);
	}

}
