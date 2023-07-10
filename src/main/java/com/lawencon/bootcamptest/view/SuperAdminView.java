package com.lawencon.bootcamptest.view;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.lawencon.bootcamptest.model.File;
import com.lawencon.bootcamptest.model.Profile;
import com.lawencon.bootcamptest.model.Role;
import com.lawencon.bootcamptest.model.User;
import com.lawencon.bootcamptest.service.RoleService;
import com.lawencon.bootcamptest.service.UserService;
import com.lawencon.bootcamptest.util.ScannerUtil;

public class SuperAdminView {
	private final RoleService roleService;
	private final UserService userService;
	private Long superAdminId;
	
	public SuperAdminView(RoleService roleService, UserService userService) {
		this.roleService = roleService;
		this.userService = userService;
	}

	public void setSuperAdminId(Long superAdminId) {
		this.superAdminId = superAdminId;
	}

	void show() throws SQLException{
		System.out.println("== Super Admin ==");
		System.out.println("1. Tambah User");
		System.out.println("2. Keluar");
		final byte menuChoice = ScannerUtil.inputByte("Pilih Menu: ", 2);
		if (menuChoice == 1) {
			final String email = ScannerUtil.inputStr("Masukan email: ");
			final String password = ScannerUtil.inputStr("Masukan password: ");
			
			final List<Role> roles = roleService.getAll();
			for (int i = 0; i < roles.size(); i++) {
				final int number = i + 1;
				System.out.println(number + ". " + roles.get(i).getRoleName());
			}
			final byte roleChoice = ScannerUtil.inputByte("Pilih Role", roles.size());
			
			final Role role = roles.get(roleChoice - 1);
			
			final String fullName = ScannerUtil.inputStr("Masukan nama: ");
			final String phone = ScannerUtil.inputStr("Masukan nomor telepon: ");
			final String address = ScannerUtil.inputStr("Masukan alamat: ");
			
			final String fileName = ScannerUtil.inputStr("Masukan nama file: ");
			final String ext = ScannerUtil.inputStr("Masukan extension: ");
			
			final LocalDateTime timeNow = LocalDateTime.now();
			
			final File file = new File();
			file.setFile(fileName);
			file.setExt(ext);
			file.setCreatedBy(superAdminId);
			file.setCreatedAt(timeNow);
			file.setIsActive(true);
			file.setVersionNum(1);
			
			
			final Profile profile = new Profile();
			profile.setUserName(fullName);
			profile.setUserPhone(phone);
			profile.setUserAddress(address);
			profile.setCreatedBy(superAdminId);
			profile.setCreatedAt(timeNow);
			profile.setIsActive(true);
			profile.setVersionNum(1);
			
			final User user = new User();
			user.setUserEmail(email);
			user.setUserPassword(password);
			user.setRole(role);
			user.setCreatedBy(superAdminId);
			user.setCreatedAt(timeNow);
			user.setIsActive(true);
			user.setVersionNum(1);
			
			final User userResult = userService.insert(user, profile, file);
			if(userResult != null) {
				System.out.println("Proses Berhasil");
			}
			else {
				System.out.println("Proses Gagal");
			}
			show();
		}
	}
	
}
