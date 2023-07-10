package com.lawencon.bootcamptest.view;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lawencon.bootcamptest.constant.QuestionTypeEnum;
import com.lawencon.bootcamptest.model.AcceptanceStatus;
import com.lawencon.bootcamptest.model.AnswerCandidate;
import com.lawencon.bootcamptest.model.CandidateAssign;
import com.lawencon.bootcamptest.model.Question;
import com.lawencon.bootcamptest.model.QuestionCandidate;
import com.lawencon.bootcamptest.model.QuestionOption;
import com.lawencon.bootcamptest.model.QuestionPackage;
import com.lawencon.bootcamptest.model.QuestionTopic;
import com.lawencon.bootcamptest.model.QuestionType;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;
import com.lawencon.bootcamptest.service.AnswerCandidateService;
import com.lawencon.bootcamptest.service.QuestionAssignService;
import com.lawencon.bootcamptest.service.QuestionPackageService;
import com.lawencon.bootcamptest.service.QuestionService;
import com.lawencon.bootcamptest.service.QuestionTopicService;
import com.lawencon.bootcamptest.service.QuestionTypeService;
import com.lawencon.bootcamptest.service.ReviewService;
import com.lawencon.bootcamptest.service.StatusService;
import com.lawencon.bootcamptest.util.ScannerUtil;

public class ReviewerView {
	private final ReviewService reviewService;
	private final QuestionAssignService questionAssignService;
	private final AnswerCandidateService answerCandidateService;
	private final StatusService statusService;
	private final QuestionTopicService questionTopicService;
	private final QuestionService questionService;
	private final QuestionPackageService questionPackageService;
	private final QuestionTypeService questionTypeService;
	private Long reviewerId;
	
	ReviewerView(ReviewService reviewService, QuestionAssignService questionAssignService, 
			AnswerCandidateService answerCandidateService, StatusService statusService, 
			QuestionTopicService questionTopicService, QuestionService questionService,
			QuestionPackageService questionPackageService, QuestionTypeService questionTypeService) throws SQLException{
		this.reviewService = reviewService;
		this.questionAssignService = questionAssignService;
		this.answerCandidateService = answerCandidateService;
		this.statusService = statusService;
		this.questionTopicService = questionTopicService;
		this.questionService = questionService;
		this.questionPackageService = questionPackageService;
		this.questionTypeService = questionTypeService;
	}
	
	void setReviewer(Long reviewerId){
		this.reviewerId = reviewerId;
	}
	
	void show() throws SQLException{
		System.out.println("== Reviewer ==");
		System.out.println("1. Beri Penilaian");
		System.out.println("2. Lihat Nilai");
		System.out.println("3. Buat Topik");
		System.out.println("4. Buat Pertanyaan");
		System.out.println("5. Logout");
		final byte menuChoice = ScannerUtil.inputByte("Pilih Menu: ", 5);
		if (menuChoice == 1) {
			final List<Review> reviews = reviewService.getAllById(reviewerId);
			System.out.println("==== List Candidate ====");
			for(int i = 0; i < reviews.size(); i++) {
				final int number = i + 1;
				System.out.println(number + ". " + reviews.get(i).getCandidate().getProfile().getUserName());				
			}
			final byte candidateChoose = ScannerUtil.inputByte("Pilih kandidat: ", reviews.size());
			final List<QuestionCandidate> questionCandidates = questionAssignService.getByType(QuestionTypeEnum.ESSAY.typeCode);
			final List<QuestionCandidate> questionCandidatesChoice = questionAssignService.getByType(QuestionTypeEnum.MULTIOPTION.typeCode);
			final List<AnswerCandidate> answerCandidates = answerCandidateService.getAnswerByType(QuestionTypeEnum.ESSAY.typeCode);
			final LocalDateTime timeNow = LocalDateTime.now();
			final List<AcceptanceStatus> acceptanceStatus = statusService.getAll();
			
			final List<CandidateAssign> candidateAssign = new ArrayList<>();
			final Set<Long> assignId = new HashSet<>();
			final List<Double> grade = new ArrayList<>();
			final List<String> notes = new ArrayList<>();
			
			int number = 0;
			if(questionCandidates.size() != 0) {
				for(int i = 0; i < questionCandidates.size(); i++) {
					if(reviews.get(candidateChoose - 1).getCandidate().getId() == questionCandidates.get(i).getCandidate().getId()) {
						number++;
						System.out.println("\n" + number + ". " + questionCandidates.get(i).getQuestion().getQuestion());
						System.out.println("Jawaban: " + answerCandidates.get(i).getAnswerEssay());
						candidateAssign.add(questionCandidates.get(i).getCandidateAssign());
					}
				}
				
				final double gradeEssay = ScannerUtil.inputDouble("Masukan Nilai: ", 100);
				final String notesEssay = ScannerUtil.inputStr("Masukan Catatan: ");
				grade.add(gradeEssay);
				notes.add(notesEssay);
			}
			
			if(questionCandidatesChoice.size() != 0) {
				System.out.println("Soal Pilihan Ganda Sudah dinilai lewat sistem");
				for(int i = 0; i < questionCandidatesChoice.size(); i++) {
					if(reviews.get(candidateChoose - 1).getCandidate().getId() == questionCandidatesChoice.get(i).getCandidate().getId()) {
						candidateAssign.add(questionCandidatesChoice.get(i).getCandidateAssign());
					}
				}
				final ReviewDetail reviewDetail = reviewService.getIdByCandidateAssign(candidateAssign.get(candidateAssign.size() - 1).getId());
				final double gradeMp = reviewDetail.getGrade();
				final String notesMp =  reviewDetail.getNotes();
				grade.add(gradeMp);
				notes.add(notesMp);
				System.out.println("Score: " + grade);
				System.out.println("Notes: " + notes);
			}
			
			for (int i = 0; i < acceptanceStatus.size(); i++) {
				final int numberAccept = i + 1;
				System.out.println(numberAccept + ". " + acceptanceStatus.get(i).getStatus());
			}
			final byte acceptChoice = ScannerUtil.inputByte("Pilih Status: ", acceptanceStatus.size());
			
			final Review updateStatus = reviewService.getReviewByCandidate(reviews.get(candidateChoose - 1).getCandidate().getId());
			updateStatus.setAcceptanceStatus(acceptanceStatus.get(acceptChoice - 1));
			updateStatus.setUpdatedBy(reviewerId);
			updateStatus.setUpdatedAt(timeNow);
			updateStatus.setIsActive(true);
			
			ReviewDetail reviewDetail = null;
			boolean result = false;
			for (int i = 0; i < candidateAssign.size(); i++) {
				assignId.add(candidateAssign.get(i).getId());
			}		
			int index = 0;
			for (Long id: assignId) {
				final ReviewDetail reviewDetailId = reviewService.getIdByCandidateAssign(id);
				reviewDetail = new ReviewDetail();
				reviewDetail.setId(reviewDetailId.getId());
				reviewDetail.setGrade(grade.get(index));
				reviewDetail.setNotes(notes.get(index));
				reviewDetail.setUpdatedBy(reviewerId);
				reviewDetail.setUpdatedAt(timeNow);
				reviewDetail.setIsActive(true);
				result = reviewService.update(reviewDetail, updateStatus);
				index++;
			}
			
			if(result) {
				System.out.println("Proses Berhasil!!");
			}
			else {
				System.out.println("Proses Gagal!!");
			}
			show();
		}
		else if (menuChoice == 2) {
			final List<ReviewDetail> reviewDetail = reviewService.getDetailById(reviewerId);
			for(int i = 0; i < reviewDetail.size(); i++) {
				final int number = i + 1;
				System.out.println(number + ". " + reviewDetail.get(i).getReview().getCandidate().getProfile().getUserName());
				System.out.println("Nilai : " + reviewDetail.get(i).getGrade());
				System.out.println("Notes : " + reviewDetail.get(i).getNotes());
			}
			show();
		}
		else if (menuChoice == 3) {
			final List<QuestionTopic> questionTopics = questionTopicService.getAllTopic();
			for(int i = 0; i < questionTopics.size(); i++) {
				System.out.println("- " + questionTopics.get(i).getTopicName());
			}
			System.out.println("\n1. Tambah");
			System.out.println("2. Kembali");
			final byte menuChoose = ScannerUtil.inputByte("Pilih Menu: ", 2);
			if(menuChoose == 1) {
				final String topicName = ScannerUtil.inputStr("Masukan topik: ");
				String topicCode = "";
				while(true) {
					topicCode = ScannerUtil.inputStr("Masukan kode topik: ");
					if(topicCode.length() > 5) {
						System.out.println("Kode lebih dari 5 karakter");
						continue;
					}
					else {
						break;
					}
				}
				final QuestionTopic questionTopic = questionTopicService.insert(topicName, topicCode, reviewerId);
				if(questionTopic != null) {
					System.out.println("Proses Berhasil");
				}
				else {
					System.out.println("Proses Gagal");
				}
			}
			show();
		}
		else if(menuChoice == 4) {
			final List<Question> questionList = new ArrayList<>();
			while(true) {
				final String question = ScannerUtil.inputStr("Masukan Soal: ");
				
				final List<QuestionTopic> questionTopics = questionTopicService.getAllTopic();
				for (int i = 0; i < questionTopics.size(); i++) {
					final int number = i + 1;
					System.out.println(number + ". " + questionTopics.get(i).getTopicName());
				}
				final byte topicChoose = ScannerUtil.inputByte("Pilih topic: ", questionTopics.size());
				
				final List<QuestionPackage> questionPackages = questionPackageService.getAll();
				for (int i = 0; i < questionPackages.size(); i++) {
					final int number = i + 1;
					System.out.println(number + ". " + questionPackages.get(i).getPackageName());
				}
				final byte packageChoose = ScannerUtil.inputByte("Pilih paket: ", questionPackages.size());
				
				final List<QuestionType> questionTypes = questionTypeService.getAllTypes();
				for (int i = 0; i < questionTypes.size(); i++) {
					final int number = i + 1; 
					System.out.println(number + ". " + questionTypes.get(i).getTypeName());
					}
				final byte typeChoose = ScannerUtil.inputByte("Pilih tipe: ", questionTypes.size());
				
				final List<QuestionOption> questionOptions = new ArrayList<>();
				
				if(QuestionTypeEnum.MULTIOPTION.typeCode.equalsIgnoreCase(questionTypes.get(typeChoose - 1).getTypeCode())) {
					final int optionSize = ScannerUtil.inputInt("Masukan jumlah pilihan: ", 0);
					for (int i = 0; i < optionSize; i++) {
						final LocalDateTime timeNow = LocalDateTime.now();
						final String optionName = ScannerUtil.inputStr("Masukan pilihan ke- " + (i + 1) + " : ");
						final byte isAnswer = ScannerUtil.inputByte("\nApakah ini kunci jawaban(1. Ya, 2.Tidak)?", 2);
						final QuestionOption questionOption = new QuestionOption();
						questionOption.setOptionData(optionName);
						questionOption.setCreatedBy(reviewerId);
						questionOption.setCreatedAt(timeNow);
						questionOption.setIsActive(true);
						questionOption.setVersionNum(1);
						if(isAnswer == 1) {
							questionOption.setIsAnswer(true);
						}
						else {
							questionOption.setIsAnswer(false);
						}
						questionOptions.add(questionOption);
					}
				}
				final LocalDateTime timeNow = LocalDateTime.now();
				final Question questionResult = new Question();
				questionResult.setQuestion(question);
				questionResult.setQuestionTopic(questionTopics.get(topicChoose - 1));
				questionResult.setQuestionType(questionTypes.get(typeChoose - 1));
				questionResult.setQuestionPackage(questionPackages.get(packageChoose - 1));
				questionResult.setCreatedBy(reviewerId);
				questionResult.setCreatedAt(timeNow);
				questionResult.setIsActive(true);
				questionResult.setVersionNum(1);
				questionResult.setQuestionOptions(questionOptions);
				
				questionList.add(questionResult);
				
				final byte inputAgain = ScannerUtil.inputByte("Apakah mau memasukan pertanyaan lain (1. Ya, 2. Tidak)? ", 2);
				if(inputAgain == 1) {
					continue;
				}
				else {
					final boolean questionInsert = questionService.insert(questionList);
					if(questionInsert) {
						System.out.println("Proses Berhasil");
					}
					else {
						System.out.println("Proses Gagal");
					}
					break;
				}
			}	
			show();
		}
	}
}
