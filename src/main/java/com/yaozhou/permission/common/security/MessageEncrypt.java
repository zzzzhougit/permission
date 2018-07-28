package com.yaozhou.permission.common.security;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MessageEncrypt {

	public static final List<Character> HEX_CHAR_LIST;
	static {
		HEX_CHAR_LIST = new ArrayList<Character>();
		HEX_CHAR_LIST.add(new Character('0'));
		HEX_CHAR_LIST.add(new Character('1'));
		HEX_CHAR_LIST.add(new Character('2'));
		HEX_CHAR_LIST.add(new Character('3'));
		HEX_CHAR_LIST.add(new Character('4'));
		HEX_CHAR_LIST.add(new Character('5'));
		HEX_CHAR_LIST.add(new Character('6'));
		HEX_CHAR_LIST.add(new Character('7'));
		HEX_CHAR_LIST.add(new Character('8'));
		HEX_CHAR_LIST.add(new Character('9'));
		HEX_CHAR_LIST.add(new Character('a'));
		HEX_CHAR_LIST.add(new Character('b'));
		HEX_CHAR_LIST.add(new Character('c'));
		HEX_CHAR_LIST.add(new Character('d'));
		HEX_CHAR_LIST.add(new Character('e'));
		HEX_CHAR_LIST.add(new Character('f'));
	}

	private static Map<String, MessageEncrypt> algorithm = new HashMap<>();
	static {
		MessageEncrypt.put("MD5", new MD5MessageEncrypt());
	}

	private static byte hex2Byte(String s) {
		int high = HEX_CHAR_LIST.indexOf(new Character(s.charAt(0))) << 4;
		int low = HEX_CHAR_LIST.indexOf(new Character(s.charAt(1)));

		return (byte) (high + low);
	}

	/**
	 * hex string to bytes
	 * @param input
	 * @return
	 */
	public static byte[] hex2Bytes(String input){
		int len = input.length() / 2;
		byte[] rtn = new byte[len];

		for (int i = 0; i < len; i++) {
			rtn[i] = hex2Byte(input.substring(i * 2, i * 2 + 2));
		}
		return rtn;
	}

	/**
	 * bytes to hex string
	 * @param bytes
	 * @return
	 */
	public static String bytes2Hex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();

		for(byte b:bytes){
			int i = b & 0xFF;
			if(i<=0xF){
				sb.append("0");
			}

			sb.append(Integer.toHexString(i));
		}

		return sb.toString();
	}
	
	public String encode(String ... input){
		StringBuilder sb = new StringBuilder();
		for(String s :input){
			sb.append(s);
		}
		
		return encode(sb.toString());
	}
	
	public String encode(String input){
		if(input!=null){
			byte[] b = null;
			try {
				b = encode(input.getBytes("GBK"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			if(b!=null)
				return new String(b);
		}
		return null;
	}
	
	public String decode(String input){
		if(input!=null){
			byte[] b = null;
			try {
				b = decode(input.getBytes("GBK"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			if(b!=null)
				try {
					return new String(b, "GBK");
				} catch (UnsupportedEncodingException e) {
					return new String(b);
				}			
		}
		return null;
	}
	
	public abstract byte[] encode(byte[]input);	
	
	public abstract byte[] decode(byte[]input);	
	
	public static void put(String name , MessageEncrypt alg){
		algorithm.put(name.toUpperCase(), alg);
	}	
	
	public static MessageEncrypt getInstance(String name){
		return MessageEncrypt.getInstance(name,"");
	}
	
	public static MessageEncrypt getInstance(String name,String innerKey){
		
		if("DES".equals(name)){
			return new DESMessageEncrypt(innerKey);
		}
		
		return algorithm.get(name.toUpperCase());
	}
}
