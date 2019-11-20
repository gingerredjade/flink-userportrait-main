package com.opslab.util.encrypt;

import junit.framework.TestCase;


public class BlowfishTest extends TestCase {

    public void testEncrypt() {
        System.out.println(Blowfish.encrypt("12345", "0opslab.com"));
    }

    public void testDecrypt() {
        byte[] tt = Blowfish.encrypt("12345", "0opslab.com");
        System.out.println(Blowfish.decrypt("12345", tt));
    }
}
