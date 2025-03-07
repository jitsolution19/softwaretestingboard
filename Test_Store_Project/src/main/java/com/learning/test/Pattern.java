package com.learning.test;

public class Pattern {

	public static void main(String[] args) {
		int input = 5;
		int exp = 65;
		for (int i = 0; i < input; i++) {
			exp = exp + i;
			for (int j = 0; j < input; j++) {
				char output = (char) exp;
				System.out.print(output);
			}
			System.out.println();
		}
	}

}
