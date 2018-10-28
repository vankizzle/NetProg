package org.elsys.netprog.rest;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BnCGame {
	private String GameId;
	private int turns;
	private int Bulls;
	private int Cows;
	private Integer Secret;
	private boolean success; 
	
	public BnCGame(String ID) {
		this.GameId = ID;
		this.turns = 0;
		this.Bulls = 0;
		this.Cows = 0;
		this.Secret = 0;
		this.success = false;
		GenerateNumber();
	}
	
	private void GenerateNumber() {
		Random gen= new Random();
		while(hasDupes(Secret = ThreadLocalRandom.current().nextInt(1000, 10000)));
	}
	
	private static boolean hasDupes(int num){
		boolean[] digs = new boolean[10];
		while(num > 0){
			if(digs[num%10]) return true;
			digs[num%10] = true;
			num/= 10;
		}
		return false;
	}

	public int Guess(String GuessNumber) {
			int GuessNumber1 = Integer.parseInt(GuessNumber);
			if(hasDupes(GuessNumber1) || GuessNumber1 < 1000) {
				return -1;
			}
			turns++;
			String guessStr = GuessNumber1 + "";
			String targetStr = Secret +"";
			for(int i= 0;i < 4;i++){
				if(guessStr.charAt(i) == targetStr.charAt(i)){
					Bulls++;
				}else if(targetStr.contains(guessStr.charAt(i)+"")){
					Cows++;
				}
			}
			if(Bulls == 4){
				success = true;
				return 0;
			}else{
				System.out.println(Cows+" Cows and "+Bulls+" Bulls.");
				Bulls = 0;
				Cows = 0;
				return 1;
			}
	}
	public String GetID() {
		return GameId;
	}
	public int GetCows() {
		return Cows;
	}	
	public int GetBulls() {
		return Bulls;
	}
	public int GetTurns() {
		return turns;
	}
	public boolean GetSuccess() {
		return success;
	}
	public Integer GetSecret() {
		return Secret;
	}
	
}
