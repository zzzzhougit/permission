package com.yaozhou.permission.common.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESMessageEncrypt extends MessageEncrypt {

	private String innerKey;
	
	public DESMessageEncrypt(String innerKey){
		this.innerKey = innerKey;
	}	
	
	@Override
	public byte[] encode(byte[] input) {
		try {
			Cipher c1 = Cipher.getInstance("DES");
			c1.init(Cipher.ENCRYPT_MODE, getSecretKey());
			byte[] codes =  c1.doFinal(input);
			return  bytes2Hex(codes).getBytes();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	public byte[] decode(byte[] input) {
		try {			
			Cipher c1 = Cipher.getInstance("DES");
			c1.init(Cipher.DECRYPT_MODE,getSecretKey());
			return c1.doFinal(hex2Bytes(new String(input)));
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	protected SecretKey secretKey;
	
	protected SecretKey getSecretKey() {
		if(secretKey==null){
			SecretKeyFactory keygen=null;
			try {
				keygen = SecretKeyFactory.getInstance("DES");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			DESKeySpec keySpec=null;
			try {
				keySpec = new DESKeySpec(innerKey.getBytes());				
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}			 
			try {
				secretKey= keygen.generateSecret(keySpec);
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
		}

		return secretKey;
	}
}
