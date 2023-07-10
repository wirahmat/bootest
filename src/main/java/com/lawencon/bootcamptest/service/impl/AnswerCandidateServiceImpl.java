package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.AnswerCandidateDao;
import com.lawencon.bootcamptest.dao.CandidateFileDao;
import com.lawencon.bootcamptest.dao.FileDao;
import com.lawencon.bootcamptest.dao.FileTypeDao;
import com.lawencon.bootcamptest.dao.QuestionOptionDao;
import com.lawencon.bootcamptest.dao.ReviewDao;
import com.lawencon.bootcamptest.dao.ReviewDetailDao;
import com.lawencon.bootcamptest.dao.UserDao;
import com.lawencon.bootcamptest.model.AnswerCandidate;
import com.lawencon.bootcamptest.model.CandidateFile;
import com.lawencon.bootcamptest.model.File;
import com.lawencon.bootcamptest.model.FileType;
import com.lawencon.bootcamptest.model.QuestionOption;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;
import com.lawencon.bootcamptest.model.User;
import com.lawencon.bootcamptest.service.AnswerCandidateService;

public class AnswerCandidateServiceImpl implements AnswerCandidateService{
	private final FileTypeDao fileTypeDao;
	private final FileDao fileDao;
	private final AnswerCandidateDao answerCandidateDao;
	private final QuestionOptionDao questionOptionDao;
	private final CandidateFileDao candidateFileDao;
	private final ReviewDetailDao reviewDetailDao;
	private final ReviewDao reviewDao;
	private final UserDao userDao;
	private final EntityManager em;
	
	public AnswerCandidateServiceImpl(FileTypeDao fileTypeDao, FileDao fileDao,
			 AnswerCandidateDao answerCandidateDao, QuestionOptionDao questionOptionDao,
			 CandidateFileDao candidateFileDao, ReviewDetailDao reviewDetailDao,
			 ReviewDao reviewDao, UserDao userDao,
			 SessionFactory sessionFactory) throws SQLException{
		this.fileTypeDao = fileTypeDao;
		this.answerCandidateDao = answerCandidateDao;
		this.fileDao = fileDao;
		this.questionOptionDao = questionOptionDao;
		this.candidateFileDao = candidateFileDao;
		this.reviewDetailDao = reviewDetailDao;
		this.reviewDao = reviewDao;
		this.userDao = userDao;
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<FileType> getAllFileType() throws SQLException {
		final List<FileType> fileTypes = fileTypeDao.getAll();
		return fileTypes;
	}
	
	@Override
	public boolean setAnswer(List<CandidateFile> candidateFiles, 
			List<AnswerCandidate> answerCandidates, 
			ReviewDetail reviewDetail, Review updateStatus, 
			User user, double isAnswer, double totalMultiChoice, 
			Long candidateId) {
		this.em.getTransaction().begin();
		try {
			final LocalDateTime timeNow = LocalDateTime.now();
			if(reviewDetail != null) {
				final double grade = ((double)isAnswer/(double)totalMultiChoice) * 100.0;
				reviewDetail.setGrade(grade);
				reviewDetail.setNotes("Kamu benar " + isAnswer + " dari total " + totalMultiChoice + " pertanyaan pilihan ganda");
				reviewDetail.setUpdatedBy(candidateId);
				reviewDetail.setUpdatedAt(timeNow);
			}
			
			for(int i = 0; i < candidateFiles.size(); i++) {			
				final File file = candidateFiles.get(i).getFile();
				file.setCreatedBy(candidateFiles.get(i).getCreatedBy());
				file.setCreatedAt(candidateFiles.get(i).getCreatedAt());
				file.setIsActive(true);
				file.setVersionNum(0);
				fileDao.insert(file);
				
				final CandidateFile candidateFile = candidateFiles.get(i);
				candidateFileDao.insert(candidateFile);
			}
			for(int i = 0; i < answerCandidates.size(); i++) {
				final AnswerCandidate answerCandidate = answerCandidates.get(i);
				answerCandidateDao.insert(answerCandidate);
			}
			
			final Review review = reviewDao.getById(updateStatus.getId());
			review.setAcceptanceStatus(updateStatus.getAcceptanceStatus());
			review.setProgressStatus(updateStatus.getProgressStatus());
			
			final ReviewDetail reviewDetailResult = reviewDetailDao.getById(reviewDetail.getId());
			reviewDetailResult.setGrade(reviewDetail.getGrade());
			reviewDetailResult.setNotes(reviewDetail.getNotes());
			reviewDetailResult.setUpdatedBy(reviewDetail.getUpdatedBy());
			reviewDetailResult.setUpdatedAt(reviewDetail.getUpdatedAt());
			
			final User candidate = userDao.getById(user.getId());
			candidate.setCreatedAt(user.getUpdatedAt());
			candidate.setUpdatedBy(user.getUpdatedBy());
			candidate.setIsActive(user.getIsActive());
			
			this.em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
		return true;
	}
	
	@Override
	public List<QuestionOption> getOption(Long questionId) throws SQLException{
		final List<QuestionOption> questionOptions = questionOptionDao.getOptionByQuestion(questionId);
		return questionOptions;
	}
	
	@Override
	public List<AnswerCandidate> getAnswerByType(String typeCode) throws SQLException {
		final List<AnswerCandidate> answerCandidates = answerCandidateDao.getByType(typeCode);
		return answerCandidates;
	}
}
