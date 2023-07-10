package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.QuestionCandidateDao;
import com.lawencon.bootcamptest.model.CandidateAssign;
import com.lawencon.bootcamptest.model.Question;
import com.lawencon.bootcamptest.model.QuestionCandidate;
import com.lawencon.bootcamptest.model.User;
import com.lawencon.bootcamptest.service.QuestionAssignService;

public class QuestionAssignServiceImpl implements QuestionAssignService{
	private final QuestionCandidateDao questionCandidateDao;
	private final EntityManager em;
	
	public QuestionAssignServiceImpl(QuestionCandidateDao questionCandidateDao,
			SessionFactory sessionFactory) throws SQLException{
		this.questionCandidateDao = questionCandidateDao;
		this.em = EntityManagerConfig.get(sessionFactory);
	}

	@Override
	public QuestionCandidate setQuestion(Long candidateId, List<Long> questionId, Long hrId, Long candidateAssignId) throws SQLException{
		this.em.getTransaction().begin();
		final QuestionCandidate questionCandidate = new QuestionCandidate();
		
		for(int i = 0; i < questionId.size(); i++) {
			final User candidate = new User();
			candidate.setId(candidateId);
			
			final Question question = new Question();
			question.setId(questionId.get(i));
			
			final User hr = new User();
			hr.setId(hrId);
			
			final CandidateAssign candidateAssign = new CandidateAssign();
			candidateAssign.setId(candidateAssignId);
			
			questionCandidate.setCandidate(candidate);
			questionCandidate.setQuestion(question);
			questionCandidate.setHr(hr);
			questionCandidate.setCandidateAssign(candidateAssign);
			questionCandidate.setCreatedBy(hrId);
			
			final LocalDateTime timeNow = LocalDateTime.now();
			questionCandidate.setCreatedAt(timeNow);
			
			questionCandidate.setIsActive(true);
			questionCandidate.setVersionNum(0);
			questionCandidateDao.insert(questionCandidate);
			this.em.getTransaction().commit();
		}
		return questionCandidate;
	}
	
	@Override
	public List<QuestionCandidate> getByType(String typeCode) throws SQLException {
		final List<QuestionCandidate> questionCandidates = questionCandidateDao.getByType(typeCode);
		return questionCandidates;
	}
	
}
