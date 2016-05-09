package com.rsa.bussiness;

import java.util.Map;

/**
 * 
 */
public class RSACoderTest {
	private String publicKey;
	private String privateKey;

	public void setUp() throws Exception {
		Map<String, Object> keyMap = RSACoder.initKey("as","asd");

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		System.err.println("��Կ: \n\r" + publicKey);
		System.err.println("˽Կ�� \n\r" + privateKey);
	}

	public static void main(String[] args) throws Exception {
		RSACoderTest rsaCoderTest=new RSACoderTest();
		rsaCoderTest.setUp();
	}
	
	
//	@Test
	public void test() throws Exception {
		System.err.println("��Կ���ܡ���˽Կ����");
		String inputStr = "abc";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
				privateKey);

		String outputStr = new String(decodedData);
		System.err.println("����ǰ: " + inputStr + "\n\r" + "���ܺ�: " + outputStr);
//		assertEquals(inputStr, outputStr);

	}

//	@Test
	public void testSign() throws Exception {
		System.err.println("˽Կ���ܡ�����Կ����");
		String inputStr = "sign";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

		byte[] decodedData = RSACoder
				.decryptByPublicKey(encodedData, publicKey);

		String outputStr = new String(decodedData);
		System.err.println("����ǰ: " + inputStr + "\n\r" + "���ܺ�: " + outputStr);
//		assertEquals(inputStr, outputStr);

		System.err.println("˽Կǩ��������Կ��֤ǩ��");
		// ����ǩ��
		String sign = RSACoder.sign(encodedData, privateKey);
		System.err.println("ǩ��:\r" + sign);

		// ��֤ǩ��
		boolean status = RSACoder.verify(encodedData, publicKey, sign);
		System.err.println("״̬:\r" + status);
//		assertTrue(status);

	}

}