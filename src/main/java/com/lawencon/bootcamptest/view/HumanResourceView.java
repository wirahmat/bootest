package com.lawencon.bootcamptest.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lawencon.bootcamptest.constant.RoleEnum;
import com.lawencon.bootcamptest.model.CandidateAssign;
import com.lawencon.bootcamptest.model.Profile;
import com.lawencon.bootcamptest.model.Question;
import com.lawencon.bootcamptest.model.QuestionCandidate;
import com.lawencon.bootcamptest.model.QuestionPackage;
import com.lawencon.bootcamptest.model.QuestionType;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;
import com.lawencon.bootcamptest.model.Role;
import com.lawencon.bootcamptest.model.User;
import com.lawencon.bootcamptest.service.CandidateAssignService;
import com.lawencon.bootcamptest.service.QuestionPackageService;
import com.lawencon.bootcamptest.service.QuestionService;
import com.lawencon.bootcamptest.service.RoleService;
import com.lawencon.bootcamptest.service.UserService;
import com.lawencon.bootcamptest.util.DateConvert;
import com.lawencon.bootcamptest.util.ScannerUtil;

public class HumanResourceView {
	
	private final UserService userService;
	private final CandidateAssignService candidateAssignService;
	private final QuestionService questionService;
	private final QuestionPackageService questionPackageService;
	private final RoleService roleService;
	private Long hrId;
	
	HumanResourceView(UserService userService, CandidateAssignService candidateAssignService, 
			QuestionService questionService, QuestionPackageService questionPackageService,
			RoleService roleService) throws Exception{
		this.userService = userService;
		this.candidateAssignService = candidateAssignService;
		this.questionService = questionService;
		this.questionPackageService = questionPackageService;
		this.roleService = roleService;
	}
	
	void setHrId(Long hrId) {
		this.hrId = hrId;
	}

	void show() throws Exception{
		System.out.println("== Human Resource ==");
		System.out.println("1. Assign Candidate");
		System.out.println("2. Tambah Paket");
		System.out.println("3. Daftarkan Kandidat");
		System.out.println("4. Keluar");
		final byte menuChoice = ScannerUtil.inputByte("Pilih Menu", 4);
		if (menuChoice == 1) {
			//Input Candidate
			System.out.println("==== Candidate ====");
			final List<User> candidates = userService.getByRoleCode(RoleEnum.CANDIDATE.roleCode);
			for(int i = 0; i < candidates.size(); i++) {
				final int number = i + 1;
				System.out.println(number + ". " + candidates.get(i).getProfile().getUserName());
			}
			final byte userChoice = ScannerUtil.inputByte("Pilih kandidat: ", candidates.size());
			final Long candidateId = candidates.get(userChoice - 1).getId();
			final List<Long> types = new ArrayList<>();
			
			while(true) {
				System.out.println("==== Question Type ====");
				final List<QuestionType> questionTypes = candidateAssignService.getAllType();
				for(int i = 0; i < questionTypes.size(); i++) {
					final int number = i + 1;
					System.out.println(number + ". " + questionTypes.get(i).getTypeName());
				}
				final byte typeChoice = ScannerUtil.inputByte("Pilih tipe pertanyaan: ", questionTypes.size());
				if (types.size() != 0) {
					boolean isThere = false;
					for (int i = 0; i < types.size(); i++) {
						if(types.get(i) == typeChoice) {
							isThere = true;
							System.out.println("Tipe Soal Sudah Dipilih");
							break;
						}
					}
					if(isThere) {
						continue;
					}
				}
				
				final Long typeId = questionTypes.get(typeChoice - 1).getId();
				types.add(typeId);
				final byte chooseAgain = ScannerUtil.inputByte("Apakah mau memasukan tipe lain? (1. Ya/ 2. Tidak)", 2);
				if(chooseAgain == 1) {
					continue;
				}
				else {
					break;
				}
			}

			final String startDate = ScannerUtil.inputStr("Masukan tanggal mulai (contoh: 2023-06-24 07:00:00) : ");
			final String endDate = ScannerUtil.inputStr("Masukan tanggal selesai (contoh: 2023-06-24 07:00:00) : ");
			
			final LocalDateTime timeNow = LocalDateTime.now();
			final User candidate = new User();
			
			final List<CandidateAssign> candidateAssigns = new ArrayList<>();
			for (int i = 0; i < types.size(); i++) {
				final CandidateAssign candidateAssign = new CandidateAssign();
				
				candidate.setId(candidateId);
				candidateAssign.setCandidate(candidate);
				
				final QuestionType questionType = new QuestionType();
				questionType.setId(types.get(i));
				candidateAssign.setQuestionType(questionType);
				
				final LocalDateTime startDateConvert = DateConvert.convertDate(startDate);
				candidateAssign.setStartDate(startDateConvert);
				
				final LocalDateTime endDateConvert = DateConvert.convertDate(endDate);
				candidateAssign.setEndDate(endDateConvert);
				
				candidateAssign.setCreatedBy(hrId);
				candidateAssign.setCreatedAt(timeNow);
				candidateAssign.setIsActive(true);
				candidateAssign.setVersionNum(0);
				candidateAssigns.add(candidateAssign);
			}
			
			//Input Review
			final List<User> reviewers = userService.getByRoleCode(RoleEnum.REVIEWER.roleCode);
			System.out.println("==== Reviewer ====");
			for(int i = 0; i < reviewers.size(); i++) {
				final int number = i + 1;
				System.out.println(number + ". " + reviewers.get(i).getProfile().getUserName());
			}
			final byte reviewerChoice = ScannerUtil.inputByte("Pilih reviewer: ", reviewers.size());
			final Long reviewerId = reviewers.get(reviewerChoice - 1).getId();
			
			final Review review = new Review();

			review.setCandidate(candidate);
			
			final User reviewer = new User();
			reviewer.setId(reviewerId);
			review.setReviewer(reviewer);
			
			review.setCreatedBy(hrId);
			review.setCreatedAt(timeNow);
			review.setIsActive(true);
			review.setVersionNum(0);

			//Set Review Detail
			final List<ReviewDetail> reviewDetails = new ArrayList<>();
			for (int i = 0; i < candidateAssigns.size(); i++) {
				final ReviewDetail reviewDetail = new ReviewDetail();
				reviewDetail.setReview(review);
				reviewDetail.setCandidateAssign(candidateAssigns.get(i));			
				reviewDetail.setCreatedBy(hrId);
				reviewDetail.setCreatedAt(timeNow);
				reviewDetail.setIsActive(true);
				reviewDetail.setVersionNum(1);
				reviewDetails.add(reviewDetail);
			}
			
			//Input Question
			final List<Question> questions;
			if (types.size() == 1) {
				questions = questionService.getAllByType(types.get(0));
			}
			else {
				questions = questionService.getAll();
			}
			final List<Question> questionChoose = new ArrayList<>();
			
			while(true) {
				System.out.println("==== Question ====");
				for(int i = 0; i < questions.size(); i++) {
					final int number = i + 1;
					System.out.println(number + ". " + questions.get(i).getQuestion() + "(" + questions.get(i).getQuestionType().getTypeName() + ")");
				}
				final byte questionChoice = ScannerUtil.inputByte("Pilih pertanyaan: ", questions.size());
				if (questionChoose.size() != 0) {
					boolean isThere = false;
					for (int i = 0; i < questionChoose.size(); i++) {
						if(questionChoose.get(i).getId() == (questionChoice)) {
							isThere = true;
							System.out.println("Soal Sudah Dipilih");
							break;
						}
					}
					if(isThere) {
						continue;
					}
				}
				final Question question = questions.get(questionChoice - 1);
				questionChoose.add(question);
				final byte chooseAgain = ScannerUtil.inputByte("Apakah mau memasukan soal lain? (1. Ya/ 2. Tidak)", 2);
				if(chooseAgain == 1) {
					continue;
				}
				else {
					break;
				}
			}
			
			//Set Candidate Question
			final List<QuestionCandidate> questionCandidates = new ArrayList<>();
			for(int i = 0; i < candidateAssigns.size(); i++) {
				for(int j = 0; j < questionChoose.size(); j++) {
					if (candidateAssigns.get(i).getQuestionType().getId() == questionChoose.get(j).getQuestionType().getId()) {
						final QuestionCandidate questionCandidate = new QuestionCandidate();
						
						final Question question = new Question();
						question.setId(questionChoose.get(j).getId());
						questionCandidate.setQuestion(question);
						
						final User hr = new User();
						hr.setId(hrId);
						questionCandidate.setHr(hr);
						
						questionCandidate.setCandidate(candidate);
						questionCandidate.setCandidateAssign(candidateAssigns.get(i));
						questionCandidate.setCreatedBy(hrId);
						questionCandidate.setCreatedAt(timeNow);
						questionCandidate.setIsActive(true);
						questionCandidate.setVersionNum(1);
						questionCandidates.add(questionCandidate);
					}
				}
			}
			
			final boolean setCandidate = candidateAssignService.setCandidate(candidateAssigns, review, reviewDetails, questionCandidates);
			if (setCandidate) {
				System.out.println("Proses Berhasil!!\n");
			}
			else {
				System.out.println("Proses Gagal!!\n");
			}
			show();
		}
		else if (menuChoice == 2) {
			final List<QuestionPackage> questionPackages = questionPackageService.getAll();
			for (int i = 0; i <questionPackages.size(); i++) {
				System.out.println("- " + questionPackages.get(i).getPackageName());
			}
			System.out.println("1. Tambah");
			System.out.println("2. Kembali");
			final byte menuChoose = ScannerUtil.inputByte("Pilih Menu: ", 2);
			if(menuChoose == 1) {
				final String packageName = ScannerUtil.inputStr("Masukan paket: ");
				String packageCode = "";
				while(true) {
					packageCode = ScannerUtil.inputStr("Masukan kode paket: ");
					if(packageCode.length() > 5) {
						System.out.println("Kode lebih dari 5 karakter");
						continue;
					}
					else {
						break;
					}
				}
				final QuestionPackage questionPackage = questionPackageService.insert(packageName, packageCode, hrId);
				if(questionPackage != null) {
					System.out.println("Proses Berhasil");
				}
				else {
					System.out.println("Proses Gagal");
				}
			}
			show();
		}
		else if(menuChoice == 3) {
			System.out.println("===== Pendaftaran Kandidat =====");
			final String email = ScannerUtil.inputStr("Masukan email kandidat: ");
			final String password = ScannerUtil.inputStr("Masukan password: ");
			final Role role = roleService.getByCode(RoleEnum.CANDIDATE.roleCode);
			
			final String fullName = ScannerUtil.inputStr("Masukan nama: ");
			final String phone = ScannerUtil.inputStr("Masukan nomor telepon: ");
			final String address = ScannerUtil.inputStr("Masukan alamat: ");
			
			final LocalDateTime timeNow = LocalDateTime.now();
			
			final Profile profile = new Profile();
			profile.setUserName(fullName);
			profile.setUserPhone(phone);
			profile.setUserAddress(address);
			profile.setCreatedBy(hrId);
			profile.setCreatedAt(timeNow);
			profile.setIsActive(true);
			profile.setVersionNum(1);
			
			final User user = new User();
			user.setUserEmail(email);
			user.setUserPassword(password);
			user.setRole(role);
			user.setCreatedBy(hrId);
			user.setCreatedAt(timeNow);
			user.setIsActive(true);
			user.setVersionNum(1);
			
			final User userResult = userService.insert(user, profile, null);
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
