package edu.fiu.cis.acrl.virtuallabs.server.tools.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import edu.fiu.cis.acrl.vescheduler.server.tools.debug.DebugTools;

public class Crypt {

	// Debug level for this class
	private static int DEBUG_LEVEL = 4;
	
	private static String key = "1234567890123456";
	
	public static String encrypt(String input) {

		DebugTools.println(DEBUG_LEVEL, "[Crypt - encrypt] Inside!");
		
		DebugTools.println(DEBUG_LEVEL, "[Crypt - encrypt] "
				+ "input: " + input);
		
		String output = null;
		
		if (input == null)
			return input;
		if (input.isEmpty())
			return input;
		
		byte[] encrypted = null;
		try {
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			encrypted = cipher.doFinal(input.getBytes());
		} catch(Exception e){
			System.out.println(e.toString());
		}
		
		if (encrypted == null)
			output = null;
		else
			output = new String(Base64.encodeBase64(encrypted));

		DebugTools.println(DEBUG_LEVEL, "[Crypt - encrypt] "
				+ "output: " + output);
		
		DebugTools.println(DEBUG_LEVEL, "[Crypt - encrypt] Inside!");
		
		return output;
	}

	public static String decrypt(String input){

		DebugTools.println(DEBUG_LEVEL, "[Crypt - decrypt] Inside!");
		
		DebugTools.println(DEBUG_LEVEL, "[Crypt - decrypt] "
				+ "input: " + input);
		
		String output = null;
		
		if (input == null)
			return input;
		if (input.isEmpty())
			return input;
		
		byte[] decrypted = null;
		try{
			SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			decrypted = cipher.doFinal(Base64.decodeBase64(input));
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		if (decrypted == null)
			output = null;
		else
			output = new String(decrypted);

		DebugTools.println(DEBUG_LEVEL, "[Crypt - decrypt] "
				+ "output: " + output);
		
		DebugTools.println(DEBUG_LEVEL, "[Crypt - decrypt] Inside!");
		
		return output;
	}

}
