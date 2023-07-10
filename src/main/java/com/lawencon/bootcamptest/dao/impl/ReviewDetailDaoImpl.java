package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.ReviewDetailDao;
import com.lawencon.bootcamptest.model.CandidateAssign;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;
import com.lawencon.bootcamptest.model.User;

public class ReviewDetailDaoImpl implements ReviewDetailDao{
	private final EntityManager em;
	
	public ReviewDetailDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public ReviewDetail insert(ReviewDetail reviewDetail) throws SQLException {
		em.persist(reviewDetail);
		return reviewDetail;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewDetail> getAllById(Long reviewerId) throws SQLException {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_review_detail rd "
				+ "INNER JOIN t_review r ON r.id = rd.review_id "
				+ "WHERE "
				+ "r.reviewer_id = :reviewerId";
		
		final List<ReviewDetail> reviewDetails = 
				this.em.createNativeQuery(sql, User.class).
				setParameter("reviewerId", reviewerId).
				getResultList();
		
		return reviewDetails;
	}
	
	@Override
	public ReviewDetail getByReview(Long reviewId) throws SQLException {
		final String sql = "SELECT "
				+ "grade, notes, review_id, candidate_assign_id "
				+ "FROM "
				+ "t_review_detail " 
				+ "WHERE "
				+ "review_id = :reviewId";
		
		final Object detailObj = this.em.createNativeQuery(sql)
				.setParameter("reviewId", reviewId)
				.getSingleResult();
		
		final Object[] detailArr = (Object[]) detailObj;
		
		ReviewDetail reviewDetail = null;

		if(detailArr.length > 0) {
			reviewDetail = new ReviewDetail();
			reviewDetail.setId(Long.valueOf(detailArr[0].toString()));
			reviewDetail.setGrade(Double.valueOf(detailArr[1].toString()));
			reviewDetail.setNotes(detailArr[2].toString());
			
			final Review review = new Review();
			review.setId(Long.valueOf(detailArr[3].toString()));
			reviewDetail.setReview(review);
			
			final CandidateAssign candidateAssign = new CandidateAssign();
			candidateAssign.setId(Long.valueOf(detailArr[4].toString()));
			reviewDetail.setCandidateAssign(candidateAssign);	
		}
		return reviewDetail;
	}
	
	@Override
	public ReviewDetail getByCandidateId(Long candidateId) throws SQLException {
		final String sql = "SELECT "
				+ "grade, notes, review_id, candidate_assign_id "
				+ "FROM "
				+ "t_review_detail rd "
				+ "INNER JOIN "
				+ "t_review r ON r.id = rd.review_id " 
				+ "WHERE "
				+ "r.candidate_id = :candidateId";
		
		final Object detailObj = this.em.createNativeQuery(sql)
				.setParameter("candidateId", candidateId)
				.getSingleResult();
		
		final Object[] detailArr = (Object[]) detailObj;
		
		ReviewDetail reviewDetail = null;

		if(detailArr.length > 0) {
			reviewDetail = new ReviewDetail();
			reviewDetail.setId(Long.valueOf(detailArr[0].toString()));
			reviewDetail.setGrade(Double.valueOf(detailArr[1].toString()));
			reviewDetail.setNotes(detailArr[2].toString());
			
			final Review review = new Review();
			review.setId(Long.valueOf(detailArr[3].toString()));
			reviewDetail.setReview(review);
			
			final CandidateAssign candidateAssign = new CandidateAssign();
			candidateAssign.setId(Long.valueOf(detailArr[4].toString()));
			reviewDetail.setCandidateAssign(candidateAssign);	
		}
		return reviewDetail;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewDetail> getDetailById(Long reviewerId) throws SQLException {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_review_detail trd  "
				+ "INNER JOIN "
				+ "t_review tr ON tr.id = trd.review_id "
				+ "INNER JOIN "
				+ "t_user tu ON tu.id = tr.candidate_id "
				+ "INNER JOIN "
				+ "t_profile tp ON tp.id = tu.profile_id "
				+ "WHERE "
				+ "tr.reviewer_id = :reviewerId ";
		
		final List<ReviewDetail> reviewDetails = 
				this.em.createNativeQuery(sql, User.class).
				setParameter("reviewerId", reviewerId).
				getResultList();
		
		return reviewDetails;
	}
	
	@Override
	public ReviewDetail getByCandidateAssignId(Long candidateAssignId) throws SQLException {
		final String sql = "SELECT "
				+ "trd.id, grade, notes , review_id, candidate_assign_id "
				+ "FROM "
				+ "t_review_detail trd "
				+ "INNER JOIN "
				+ "t_review tr ON trd.review_id = tr.id "
				+ "WHERE "
				+ "candidate_assign_id = :candidateAssignId ";
		
		final Object detailObj = this.em.createNativeQuery(sql)
				.setParameter("candidateAssignId", candidateAssignId)
				.getSingleResult();
		
		final Object[] detailArr = (Object[]) detailObj;
		
		ReviewDetail reviewDetail = null;

		if(detailArr.length > 0) {
			reviewDetail = new ReviewDetail();
			reviewDetail.setId(Long.valueOf(detailArr[0].toString()));
			
			if(detailArr[1] != null) {
				reviewDetail.setGrade(Double.valueOf(detailArr[1].toString()));
			}
			
			if(detailArr[2] != null) {
				reviewDetail.setNotes(detailArr[2].toString());
			}
			
			final Review review = new Review();
			review.setId(Long.valueOf(detailArr[3].toString()));
			reviewDetail.setReview(review);
			
			final CandidateAssign candidateAssign = new CandidateAssign();
			candidateAssign.setId(Long.valueOf(detailArr[4].toString()));
			reviewDetail.setCandidateAssign(candidateAssign);	
		}
		return reviewDetail;
	}	
	
	@Override
	public ReviewDetail getById(Long id) throws SQLException {
		final ReviewDetail reviewDetail = this.em.find(ReviewDetail.class, id);
		return reviewDetail;
	}
}
