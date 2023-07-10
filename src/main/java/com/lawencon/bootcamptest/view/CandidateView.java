package com.lawencon.bootcamptest.view;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lawencon.bootcamptest.constant.AcceptanceStatusEnum;
import com.lawencon.bootcamptest.constant.ProgressStatusEnum;
import com.lawencon.bootcamptest.model.AcceptanceStatus;
import com.lawencon.bootcamptest.model.AnswerCandidate;
import com.lawencon.bootcamptest.model.CandidateAssign;
import com.lawencon.bootcamptest.model.CandidateFile;
import com.lawencon.bootcamptest.model.File;
import com.lawencon.bootcamptest.model.FileType;
import com.lawencon.bootcamptest.model.ProgressStatus;
import com.lawencon.bootcamptest.model.Question;
import com.lawencon.bootcamptest.model.QuestionOption;
import com.lawencon.bootcamptest.model.QuestionType;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;
import com.lawencon.bootcamptest.model.User;
import com.lawencon.bootcamptest.service.AnswerCandidateService;
import com.lawencon.bootcamptest.service.CandidateAssignService;
import com.lawencon.bootcamptest.service.QuestionService;
import com.lawencon.bootcamptest.service.ReviewService;
import com.lawencon.bootcamptest.service.StatusService;
import com.lawencon.bootcamptest.service.UserService;
import com.lawencon.bootcamptest.util.ScannerUtil;

public class CandidateView {
	
	private final AnswerCandidateService answerCandidateService;
	private final QuestionService questionService;
	private final CandidateAssignService candidateAssignService;
	private final ReviewService reviewService;
	private final StatusService statusService;
	private final UserService userService;
	private Long candidateId;
	
	CandidateView(AnswerCandidateService answerCandidateService, 
			QuestionService questionService, 
			CandidateAssignService candidateAssignService, 
			ReviewService reviewService, StatusService statusService, 
			UserService userService) throws SQLException{
		this.answerCandidateService = answerCandidateService;
		this.questionService = questionService;
		this.candidateAssignService = candidateAssignService;
		this.reviewService = reviewService;
		this.statusService = statusService;
		this.userService = userService;
	}
	
	void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	void show() throws SQLException{
		final List<CandidateAssign> candidateAssign = candidateAssignService.getId(candidateId);
		final User user = userService.getById(candidateId);
		if (candidateAssign.size() != 0) {
			for (int i = 0; i < candidateAssign.size(); i++) {
				if(candidateAssign.get(i).getCandidate().getId() != candidateId) {
					System.out.println("\nKamu belum di assign!!!\n");
				}
			}
		}
		else {
			System.out.println("\nKamu belum di assign!!!\n");
		}
		
		System.out.println("== Candidate ==");
		System.out.println("1. Upload File");
		System.out.println("2. Logout");
		System.out.println("3. Keluar");
		final byte menuChoice = ScannerUtil.inputByte("Pilih Menu?", 3);
		final List<FileType> fileTypes = answerCandidateService.getAllFileType();
		final List<CandidateFile> candidateFiles = new ArrayList<>();
		final List<AnswerCandidate> answers = new ArrayList<>();
		final User candidate = new User();
		candidate.setId(candidateId);
		if (menuChoice == 1) {
			final LocalDateTime timeNow = LocalDateTime.now();
			for(int i = 0; i < fileTypes.size(); i++) {
				System.out.println("Masukan " + fileTypes.get(i).getTypeName());
				final String fileName = ScannerUtil.inputStr("Masukan file name: ");
				final String fileExt = ScannerUtil.inputStr("Masukan file extension: ");
				
				final File file = new File();
				
				file.setExt(fileExt);
				file.setFile(fileName);			
				file.setCreatedBy(candidateId);
				file.setCreatedAt(timeNow);
				file.setIsActive(true);
				file.setVersionNum(0);
				
				final CandidateFile candidateFile = new CandidateFile();
				candidateFile.setFile(file);
				candidateFile.setFileType(fileTypes.get(i));
				candidateFile.setCandidate(candidate);
				candidateFile.setCreatedBy(candidateId);
				candidateFile.setCreatedAt(timeNow);
				candidateFile.setIsActive(true);
				candidateFile.setVersionNum(0);
				candidateFiles.add(candidateFile);
			}
			final List<QuestionType> questionTypes = candidateAssignService.getAllType();
			final List<CandidateAssign> candidateAssigns = candidateAssignService.getId(candidateId);
			final List<Question> questions = questionService.getByCandidate(candidateId);
			long candidateAssignId = 0;
			for (int i = 0; i < candidateAssign.size(); i++) {
				if(candidateAssigns.get(i).getQuestionType().getId() == questionTypes.get(0).getId()) {
					candidateAssignId = candidateAssigns.get(i).getId();
				}
			}
			
			final ProgressStatus progressStatus = statusService.getProgressByCode(ProgressStatusEnum.ONPROGRESS.progressCode);
			Review updateStatus = reviewService.getReviewByCandidate(candidateId);
		
			
			updateStatus.setProgressStatus(progressStatus);
			updateStatus.setAcceptanceStatus(null);
			updateStatus.setUpdatedBy(candidateId);
			updateStatus.setUpdatedAt(timeNow);
			updateStatus.setIsActive(true);
			
			updateStatus = reviewService.update(updateStatus);
			
			if(updateStatus != null) {
				System.out.println("Status Pengerjaan : " + progressStatus.getStatus());
			}
			else {
				System.out.println("Update Gagal!!");
			}
			
			int isAnswer = 0;
			int totalMultiChoice = 0;
			for (int i = 0; i < questions.size(); i++) {
				final AnswerCandidate answerCandidate = new AnswerCandidate();
				final int number = i + 1;
				System.out.println(number + ". " + questions.get(i).getQuestion());
				final List<QuestionOption> questionOptions = answerCandidateService.getOption(questions.get(i).getId());
				if(questions.get(i).getQuestionType().getId() == questionTypes.get(0).getId()) {
					totalMultiChoice++;
					for(int j = 0; j < questionOptions.size(); j++) {
						final int numberOption = j + 1;
						System.out.println(numberOption + ". " + questionOptions.get(j).getOptionData());		
					}
					final byte optionChoose = ScannerUtil.inputByte("Pilih jawaban: ", questionOptions.size());
					answerCandidate.setQuestion(questions.get(i));
					answerCandidate.setQuestionOption(questionOptions.get(optionChoose - 1));
					if (questionOptions.get(optionChoose - 1).getIsAnswer()) {
						isAnswer++;
					}
					for(int k = 0; k < candidateAssigns.size(); k++) {
						if (candidateAssigns.get(k).getQuestionType().getId() == questionTypes.get(0).getId()) {
							answerCandidate.setCandidateAssign(candidateAssigns.get(k));
						}
					}
				}
				else {
					final String essayAnswer = ScannerUtil.inputStr("Ketik jawaban: ");
					answerCandidate.setQuestion(questions.get(i));
					answerCandidate.setAnswerEssay(essayAnswer);
					for(int k = 0; k < candidateAssigns.size(); k++) {
						if (candidateAssigns.get(k).getQuestionType().getId() == questionTypes.get(1).getId()) {
							answerCandidate.setCandidateAssign(candidateAssigns.get(k));
						}
					}
				}
				answerCandidate.setCandidate(candidate);
				answerCandidate.setCreatedBy(candidateId);
				answerCandidate.setCreatedAt(timeNow);
				answerCandidate.setIsActive(true);
				answerCandidate.setVersionNum(0);
				answers.add(answerCandidate);
			}
			System.out.println("\nJawaban kamu: ");
			for (int i = 0; i < answers.size(); i++) {
				if(answers.get(i).getAnswerEssay() != null) {
					System.out.println(answers.get(i).getAnswerEssay());
				}
				else {
					System.out.println(answers.get(i).getQuestionOption().getOptionData());
				}
			}
			final ReviewDetail reviewDetail = reviewService.getIdByCandidateAssign(candidateAssignId);			
			
			final ProgressStatus newProgressStatus = statusService.getProgressByCode(ProgressStatusEnum.SUBMITTED.progressCode);
			final AcceptanceStatus acceptanceStatus = statusService.getAcceptanceByCode(AcceptanceStatusEnum.NEEDREVIEW.acceptanceCode);
			updateStatus.setProgressStatus(newProgressStatus);
			updateStatus.setAcceptanceStatus(acceptanceStatus);
			
			
			user.setUpdatedAt(timeNow);
			user.setUpdatedBy(candidateId);
			user.setIsActive(false);
			
			final boolean setAnswer = answerCandidateService.setAnswer(candidateFiles, answers, reviewDetail, updateStatus, user, isAnswer, totalMultiChoice, candidateId);
			if (setAnswer) {
				System.out.println("\nProses Berhasil!!\n");
			}
			else {
				System.out.println("Proses Gagal!!\n");
			}
		}
	}
}
