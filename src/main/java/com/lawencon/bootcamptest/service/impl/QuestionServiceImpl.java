package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.constant.QuestionTypeEnum;
import com.lawencon.bootcamptest.dao.QuestionDao;
import com.lawencon.bootcamptest.dao.QuestionOptionDao;
import com.lawencon.bootcamptest.model.Question;
import com.lawencon.bootcamptest.model.QuestionOption;
import com.lawencon.bootcamptest.service.QuestionService;

public class QuestionServiceImpl implements QuestionService{
	private final QuestionDao questionDao;
	private final QuestionOptionDao questionOptionDao;
	private final EntityManager em;
	
	public QuestionServiceImpl(QuestionDao questionDao, QuestionOptionDao questionOptionDao,
			SessionFactory sessionFactory) throws SQLException{
		this.questionDao = questionDao;
		this.questionOptionDao = questionOptionDao;
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public List<Question> getAll() throws SQLException {
		final List<Question> questions = questionDao.getAll();
		return questions;
	}
	
	@Override
	public List<Question> getAllByType(Long questionTypeId) throws SQLException {
		final List<Question> questions = questionDao.getAllByType(questionTypeId);
		return questions;
	}
	
	@Override
	public List<Question> getByCandidate(Long candidateId) throws SQLException {
		final List<Question> questions = questionDao.getByIdCandidate(candidateId);
		return questions;
	}
	
	@Override
	public List<Question> getByReviewer(Long reviewerId) throws SQLException {
		final List<Question> questions = questionDao.getByIdReviewer(reviewerId);
		return questions;
	}
	
	@Override
	public boolean insert(List<Question> questions) throws SQLException {
		this.em.getTransaction().begin();
		try {
			for(int i = 0; i < questions.size(); i++) {
				final Question question = questionDao.insert(questions.get(i));
				if(QuestionTypeEnum.MULTIOPTION.typeCode.equalsIgnoreCase(questions.get(i).getQuestionType().getTypeCode())) {
					final List<QuestionOption> questionOptions = questions.get(i).getQuestionOptions();
					for (int j = 0; j < questionOptions.size(); j++) {
						QuestionOption questionOption = questionOptions.get(j);
						questionOption.setQuestion(question);
						questionOption = questionOptionDao.insert(questionOption);
					}
				}
			}
			this.em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
		return true;
	}
}
