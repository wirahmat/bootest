package com.lawencon.bootcamptest.view;

import com.lawencon.bootcamptest.constant.RoleEnum;
import com.lawencon.bootcamptest.model.User;
import com.lawencon.bootcamptest.service.UserService;
import com.lawencon.bootcamptest.util.ScannerUtil;

public class LoginView {
	private final UserService userService;
	private final SuperAdminView superAdminView;
	private final HumanResourceView humanResourceView;
	private final ReviewerView reviewerView;
	private final CandidateView candidateView;
	
	LoginView(UserService userService, SuperAdminView superAdminView, HumanResourceView humanResourceView, ReviewerView reviewerView, CandidateView candidateView) {
		this.userService = userService;
		this.superAdminView = superAdminView;
		this.humanResourceView = humanResourceView;
		this.reviewerView = reviewerView;
		this.candidateView = candidateView;
	}

	void show() throws Exception{
		final String userEmail = ScannerUtil.inputStr("Masukan emailmu: ");	
		final String userPass = ScannerUtil.inputStr("Masukan passwordmu: ");
		
		final User user = userService.login(userEmail, userPass);
		
		if(user != null && user.getRole() != null && user.getIsActive()) {
			System.out.println("\nSelamat Datang " + user.getProfile().getUserName());
			if (RoleEnum.SUPERADMIN.roleCode.equalsIgnoreCase(user.getRole().getRoleCode())) {
				superAdminView.setSuperAdminId(user.getId());
				superAdminView.show();
			}
			else if(RoleEnum.HR.roleCode.equalsIgnoreCase(user.getRole().getRoleCode())) {
				humanResourceView.setHrId(user.getId());
				humanResourceView.show();
			}
			else if(RoleEnum.REVIEWER.roleCode.equalsIgnoreCase(user.getRole().getRoleCode())) {
				reviewerView.setReviewer(user.getId());
				reviewerView.show();
			}
			else if(RoleEnum.CANDIDATE.roleCode.equalsIgnoreCase(user.getRole().getRoleCode())) {
				candidateView.setCandidateId(user.getId());
				candidateView.show();
			}
		}
		else if(user != null && user.getRole() != null && (user.getIsActive() == null || !user.getIsActive())) {
			System.out.println("Kamu sudah menjawab!!\n");
		}
		else {
			System.out.println("\n ===== User tidak ditemukan!!! =====\n");
		}
		
	}
}
