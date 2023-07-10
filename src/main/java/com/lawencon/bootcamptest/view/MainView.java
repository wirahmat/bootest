package com.lawencon.bootcamptest.view;

import com.lawencon.bootcamptest.util.ScannerUtil;

public class MainView {
	private final LoginView loginView;
	
	MainView(LoginView loginView) {
		this.loginView = loginView;
	}

	public void show() throws Exception{		
		System.out.println("== Bootcamp Test Management System ==");
		System.out.println("1. Login");
		System.out.println("2. Exit");
		final byte menuChoice = ScannerUtil.inputByte("Masukan Menu: ", 2);
		if (menuChoice == 1) {
			loginView.show();
		}
		else {
			return;
		}
		show();
	}
}
