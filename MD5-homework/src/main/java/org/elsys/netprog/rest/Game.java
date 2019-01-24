package org.elsys.netprog.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.bind.DatatypeConverter;

public class Game {
	private byte[] mybytearray_;
	private int arraylen_;
	private String Hash_;
	
	public Game(int len) {
		this.arraylen_ = len;
		this.mybytearray_ = new byte[arraylen_];
		GenerateRandomArray(mybytearray_);
	}
	
	public void GenerateRandomArray(byte[] b) {
		new Random().nextBytes(b);
		GenerateHash();
	}
	
	public void GenerateHash() {
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(mybytearray_);
			
			byte[] digest = md.digest();
			
			this.Hash_ = Base64.getUrlEncoder().encodeToString(digest);	
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int Compare(String arrayInput,String HashString) {
		if (Base64.getUrlEncoder().encodeToString(mybytearray_).equals(arrayInput) && Hash_.equals(HashString)){
			return 1;	
		}else return 0;
	}
	
	public String GetHash() {
		return Hash_;
	}
	
	public int GetLen() {
		return arraylen_;
	}
	
}
