package com.aurionpro.entities;

import java.util.Random;

public class RandomNumberGenerator {
	
	public static int generateNumber() {
		Random random = new Random();
		return (random.nextInt(1000)+10000);
	}
}
