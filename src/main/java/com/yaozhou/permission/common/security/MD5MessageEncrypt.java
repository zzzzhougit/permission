package com.yaozhou.permission.common.security;


import java.security.MessageDigest;


public class MD5MessageEncrypt extends MessageEncrypt {

	@Override
	public byte[] decode(byte[] input) {
		throw new UnsupportedOperationException();
	}

	@Override
	public byte[] encode(byte[] input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input);
			return  bytes2Hex(md.digest()).getBytes();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
