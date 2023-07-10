package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.CandidateAssignDao;
import com.lawencon.bootcamptest.dao.QuestionCandidateDao;
import com.lawencon.bootcamptest.dao.QuestionTypeDao;
import com.lawencon.bootcamptest.dao.ReviewDao;
import com.lawencon.bootcamptest.dao.ReviewDetailDao;
import com.lawencon.bootcamptest.model.CandidateAssign;
import com.lawencon.bootcamptest.model.QuestionCandidate;
import com.lawencon.bootcamptest.model.QuestionType;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;
import com.lawencon.bootcamptest.service.CandidateAssignService;

public class CandidateAssignServiceImpl implements CandidateAssignService{
	private final QuestionTypeDao questionTypeDao;
	private final CandidateAssignDao candidateAssignDao;
	private final QuestionCandidateDao questionCandidateDao;
	private final ReviewDao reviewDao;
	private final ReviewDetailDao reviewDetailDao;
	private final EntityManager em;
	
	public CandidateAssignServiceImpl(QuestionTypeDao questionTypeDao, 
			CandidateAssignDao candidateAssignDao, 
			QuestionCandidateDao questionCandidateDao, 
			ReviewDao reviewDao, ReviewDetailDao reviewDetailDao,
			SessionFactory sessionFactory) throws SQLException{
		this.candidateAssignDao = candidateAssignDao;
		this.questionTypeDao = questionTypeDao;
		this.questionCandidateDao = questionCandidateDao;
		this.reviewDao = reviewDao;
		this.reviewDetailDao = reviewDetailDao;
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public boolean setCandidate(List<CandidateAssign> candidateAssign, Review review, List<ReviewDetail> reviewDetail, List<QuestionCandidate> questionCandidate) {
		this.em.getTransaction().begin();
		try {
			for(int i = 0; i < candidateAssign.size(); i++) {
				candidateAssignDao.insert(candidateAssign.get(i));
			}
			
			reviewDao.insert(review);
			
			for(int i = 0; i < reviewDetail.size(); i++) {
				reviewDetailDao.insert(reviewDetail.get(i));
			}
			
			for(int i = 0; i < questionCandidate.size(); i++) {
				questionCandidateDao.insert(questionCandidate.get(i));
			}
			this.em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
		return true;
	}
	
	@Override
	public List<QuestionType> getAllType() throws SQLException{
		final List<QuestionType> questionTypes = questionTypeDao.getAll();
		return questionTypes;
	}
	
	@Override
	public List<CandidateAssign> getId(Long candidateId) throws SQLException {
		final List<CandidateAssign> candidateAssign = candidateAssignDao.getById(candidateId);
		return candidateAssign;
	}
}
