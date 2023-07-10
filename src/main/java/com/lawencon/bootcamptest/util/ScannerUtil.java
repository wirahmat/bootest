package com.lawencon.bootcamptest.util;

import java.util.Scanner;

public class ScannerUtil {
	public static double inputDouble(String message, int range) {
		final Scanner scanner = new Scanner (System.in);
		double choiceDouble = 0;
		System.out.print(message);
		try {
			while(true) {
				final String choice = scanner.nextLine();
				choiceDouble = Double.parseDouble(choice);
				if (range == 0) {//Infinite Range
					if (choiceDouble <= range) {
						System.out.print("Data tidak boleh negatif atau nol!!!\nSilahkan Input Kembali: ");
					}
					else {
						break;
					}
				}
				else if (range != 0) {
					if (choiceDouble > range || choiceDouble <= 0) {
						System.out.print("Data tidak boleh lebih dari jangkauan atau tidak boleh negatif!!!\nSilahkan Input Kembali: ");
					}
					else {
						break;
					}
				}
			}
		}catch (Exception e) {
			System.out.println("Input Tidak Boleh Huruf atau Kosong!!!");
			e.printStackTrace();
			return inputDouble(message, range);
		}
		return choiceDouble;
	}
	public static int inputInt(String message, int range) {
		final Scanner scanner = new Scanner (System.in);
		int choiceInt = 0;
		System.out.print(message);
		try {
			while(true) {
				final String choice = scanner.nextLine();
				choiceInt = Integer.parseInt(choice);
				if (range == 0) {//Infinite Range
					if (choiceInt <= range) {
						System.out.print("Data tidak boleh negatif atau nol!!!\nSilahkan Input Kembali: ");
					}
					else {
						break;
					}
				}
				else if (range != 0) {
					if (choiceInt > range || choiceInt <= 0) {
						System.out.print("Data tidak boleh lebih dari jangkauan atau tidak boleh negatif!!!\nSilahkan Input Kembali: ");
					}
					else {
						break;
					}
				}
			}
		}catch (Exception e) {
			System.out.println("Input Tidak Boleh Huruf atau Kosong!!!");
			e.printStackTrace();
			return inputInt(message, range);
		}
		return choiceInt;
	}
	public static byte inputByte(String message, int range) {
		final Scanner scanner = new Scanner (System.in);
		byte choiceByte = 0;
		System.out.print(message);
		try {
			while(true) {
				final String choice = scanner.nextLine();
				choiceByte = Byte.parseByte(choice);
				if (range == 0) {//Infinite Range
					if (choiceByte <= range) {
						System.out.print("Data tidak boleh negatif atau nol!!!\nSilahkan Input Kembali: ");
					}
					else {
						break;
					}
				}
				else if (range != 0) {
					if (choiceByte > range || choiceByte <= 0) {
						System.out.print("Data tidak boleh lebih dari jangkauan atau tidak boleh negatif!!!\nSilahkan Input Kembali: ");
					}
					else {
						break;
					}
				}
			}
		}catch (Exception e) {
			System.out.println("Input Tidak Boleh Huruf atau Kosong!!!");
			e.printStackTrace();
			return inputByte(message, range);
		}
		return choiceByte;
	}
	public static String inputStr(String message) {
		final Scanner scanner = new Scanner (System.in);
		System.out.print(message);
		final String choice = scanner.nextLine();
		if(choice.trim().equalsIgnoreCase("")) {
			System.out.println("Input tidak boleh kosong!!!");
			inputStr(message);
		}
		return choice;
	}
	public static String inputOnlyStr(String message) {
		final Scanner scanner = new Scanner (System.in);
		int charCount = 0;
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.print(message);
		final String choice = scanner.nextLine();
		if(choice.trim().equalsIgnoreCase("")) {
			System.out.println("Input tidak boleh kosong!!!");
			return inputOnlyStr(message);
		}
		for (int i = 0; i < choice.length(); i++) {
			for (int j = 0; j < alphabet.length(); j++) {
				if (choice.substring(i, i + 1).equalsIgnoreCase(alphabet.substring(j, j + 1))) {
					charCount++;
				}
			}
			if(charCount != (i + 1)) {
				System.out.println("Input harus huruf!!!");
				return inputOnlyStr(message);
			}
		}
		return choice;
	}
}
