package com.lawencon.bootcamptest.service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.ReviewDao;
import com.lawencon.bootcamptest.dao.ReviewDetailDao;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;
import com.lawencon.bootcamptest.model.User;
import com.lawencon.bootcamptest.service.ReviewService;

public class ReviewServiceImpl implements ReviewService{
	private final ReviewDao reviewDao;
	private final ReviewDetailDao reviewDetailDao;
	private final EntityManager em;
	
	public ReviewServiceImpl(ReviewDao reviewDao, ReviewDetailDao reviewDetailDao,
			SessionFactory sessionFactory) throws SQLException{
		this.reviewDao = reviewDao;
		this.reviewDetailDao = reviewDetailDao;
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Review setReviewer(Long candidateId, Long hrId, Long reviewerId) throws SQLException{
		this.em.getTransaction().begin();
		final Review review = new Review();
		
		final User candidate = new User();
		candidate.setId(candidateId);
		
		final User reviewer = new User();
		reviewer.setId(reviewerId);
		
		review.setCandidate(candidate);
		review.setReviewer(reviewer);
		review.setCreatedBy(hrId);
		
		final LocalDateTime timeNow = LocalDateTime.now();
		review.setCreatedAt(timeNow);
		
		review.setIsActive(true);
		review.setVersionNum(0);
		reviewDao.insert(review);
		this.em.getTransaction().commit();
		return review;
	}
	
	@Override
	public List<Review> getAllById(Long reviewerId) throws SQLException {
		final List<Review> reviews = reviewDao.getByReviewerId(reviewerId);
		return reviews;
	}
	
	@Override
	public List<ReviewDetail> getAllDetail(Long reviewerId) throws SQLException {
		final List<ReviewDetail> reviewDetails = reviewDetailDao.getAllById(reviewerId);
		return reviewDetails;
	}
	
	@Override
	public Review update(Review review) throws SQLException {
		this.em.getTransaction().begin();
		final Review reviewResult = reviewDao.getById(review.getId());
		reviewResult.setProgressStatus(review.getProgressStatus());
		reviewResult.setAcceptanceStatus(review.getAcceptanceStatus());
		reviewResult.setUpdatedBy(review.getUpdatedBy());
		reviewResult.setUpdatedAt(review.getUpdatedAt());
		reviewResult.setIsActive(review.getIsActive());
		this.em.getTransaction().commit();
		return reviewResult;
	}
	
	@Override
	public boolean update(ReviewDetail reviewDetail, Review review) throws SQLException {
		this.em.getTransaction().begin();
		try {
			final ReviewDetail reviewDetailResult = reviewDetailDao.getById(reviewDetail.getId());
			reviewDetailResult.setGrade(reviewDetail.getGrade());
			reviewDetailResult.setNotes(reviewDetail.getNotes());
			reviewDetailResult.setUpdatedBy(reviewDetail.getUpdatedBy());
			reviewDetailResult.setUpdatedAt(reviewDetail.getUpdatedAt());
			reviewDetailResult.setIsActive(reviewDetail.getIsActive());

			final Review reviewResult = reviewDao.getById(review.getId());
			reviewResult.setAcceptanceStatus(review.getAcceptanceStatus());
			reviewResult.setUpdatedBy(review.getUpdatedBy());
			reviewResult.setUpdatedAt(review.getUpdatedAt());
			reviewResult.setIsActive(review.getIsActive());
			
			this.em.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}
		return true;
	}
	
	@Override
	public ReviewDetail getByReview(Long reviewId) throws SQLException {
		final ReviewDetail reviewDetail = reviewDetailDao.getByReview(reviewId);
		return reviewDetail;
	}
	
	@Override
	public ReviewDetail getByCandidate(Long candidateId) throws SQLException {
		final ReviewDetail reviewDetail = reviewDetailDao.getByCandidateId(candidateId);
		return reviewDetail;
	}
	
	@Override
	public ReviewDetail getIdByCandidateAssign(Long candidateAssignId) throws SQLException {
		final ReviewDetail reviewDetail = reviewDetailDao.getByCandidateAssignId(candidateAssignId);
		return reviewDetail;
	}
	
	@Override
	public List<ReviewDetail> getDetailById(Long reviewerId) throws SQLException {
		final List<ReviewDetail> reviewDetails = reviewDetailDao.getDetailById(reviewerId);
		return reviewDetails;
	}
	
	@Override
	public Review getReviewByCandidate(Long candidateId) throws SQLException {
		final Review review = reviewDao.getByCandidateId(candidateId);
		return review;
	}
	
	@Override
	public ReviewDetail countScore(ReviewDetail reviewDetail, double isAnswer, double totalMultiChoice) throws SQLException {
		if(reviewDetail != null) {
			final double grade = ((double)isAnswer/(double)totalMultiChoice) * 100.0;
			reviewDetail.setGrade(grade);
			reviewDetail.setNotes("Kamu benar " + isAnswer + " dari total " + totalMultiChoice + " pertanyaan pilihan ganda");
		}
		return reviewDetail;
	}
}
