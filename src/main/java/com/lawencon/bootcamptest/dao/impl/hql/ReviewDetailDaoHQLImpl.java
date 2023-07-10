package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.ReviewDetailDao;
import com.lawencon.bootcamptest.model.CandidateAssign;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;

public class ReviewDetailDaoHQLImpl implements ReviewDetailDao{
	private final EntityManager em;
	
	public ReviewDetailDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public ReviewDetail insert(ReviewDetail reviewDetail) throws SQLException {
		em.persist(reviewDetail);
		return reviewDetail;
	}
	
	@Override
	public List<ReviewDetail> getAllById(Long reviewerId) throws SQLException {
		final String sql = "SELECT "
				+ "rd "
				+ "FROM "
				+ "ReviewDetail rd "
				+ "WHERE "
				+ "rd.review.reviewer.id = :reviewerId";
		
		final List<ReviewDetail> reviewDetails = 
				this.em.createQuery(sql, ReviewDetail.class).
				setParameter("reviewerId", reviewerId).
				getResultList();
		
		return reviewDetails;
	}
	
	@Override
	public ReviewDetail getByReview(Long reviewId) throws SQLException {
		final String sql = "SELECT "
				+ "rd.grade, rd.notes, rd.review.id, rd.candidateAssign.id "
				+ "FROM "
				+ "ReviewDetail rd " 
				+ "WHERE "
				+ "rd.review.id = :reviewId";
		
		final Object detailObj = this.em.createQuery(sql)
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
				+ "rd.grade, rd.notes, rd.review.id, rd.candidateAssign.id "
				+ "FROM "
				+ "ReviewDetail rd " 
				+ "WHERE "
				+ "rd.review.candidateId = :candidateId";
		
		final Object detailObj = this.em.createQuery(sql)
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
	
	@Override
	public List<ReviewDetail> getDetailById(Long reviewerId) throws SQLException {
		final String sql = "SELECT "
				+ "rd "
				+ "FROM "
				+ "ReviewDetail rd  "
				+ "WHERE "
				+ "r.reviewer.id = :reviewerId ";
		
		final List<ReviewDetail> reviewDetails = 
				this.em.createQuery(sql, ReviewDetail.class).
				setParameter("reviewerId", reviewerId).
				getResultList();
		
		return reviewDetails;
	}
	
	@Override
	public ReviewDetail getByCandidateAssignId(Long candidateAssignId) throws SQLException {
		final String sql = "SELECT "
				+ "rd.id, rd.grade, rd.notes , rd.review.id, rd.candidateAssign.id "
				+ "FROM "
				+ "ReviewDetail rd "
				+ "WHERE "
				+ "rd.candidateAssign.id = :candidateAssignId ";
		
		final Object detailObj = this.em.createQuery(sql)
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
