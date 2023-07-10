package com.lawencon.bootcamptest.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.lawencon.bootcamptest.config.EntityManagerConfig;
import com.lawencon.bootcamptest.dao.ReviewDao;
import com.lawencon.bootcamptest.model.AcceptanceStatus;
import com.lawencon.bootcamptest.model.Profile;
import com.lawencon.bootcamptest.model.ProgressStatus;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.User;

public class ReviewDaoImpl implements ReviewDao{
	
	private final EntityManager em;
	
	public ReviewDaoImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Review insert(Review review) throws SQLException {
		em.persist(review);
		return review;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Review> getByReviewerId(Long reviewerId) throws SQLException {
		final String sql = "SELECT "
				+ "* "
				+ "FROM "
				+ "t_review tr "
				+ "INNER JOIN "
				+ "t_user tu ON tu.id = tr.candidate_id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "WHERE tr.reviewer_id = :reviewerId";
		
		final List<Review> reviews = 
				this.em.createNativeQuery(sql, Review.class).
				setParameter("reviewerId", reviewerId).
				getResultList();
		
		return reviews;
	}
	
	@Override
	public Review getByCandidateId(Long candidateId) throws SQLException {
		final String sql = "SELECT "
				+ "tr.id, acceptance_status_id, progress_status_id, candidate_id, reviewer_id, user_name "
				+ "FROM "
				+ "t_review tr "
				+ "INNER JOIN "
				+ "t_user tu ON tu.id = tr.candidate_id "
				+ "INNER JOIN "
				+ "t_profile tp ON tu.profile_id = tp.id "
				+ "WHERE tr.candidate_id = :candidateId";
		
		final Object reviewObj = this.em.createNativeQuery(sql)
				.setParameter("candidateId", candidateId)
				.getSingleResult();
		
		final Object[] reviewArr = (Object[]) reviewObj;
		
		Review review = null;

		if(reviewArr.length > 0) {
			review = new Review();
			review.setId(Long.valueOf(reviewArr[0].toString()));
			
			if(reviewArr[1] != null) {
				final AcceptanceStatus acceptanceStatus = new AcceptanceStatus();
				acceptanceStatus.setId(Long.valueOf(reviewArr[1].toString()));
				review.setAcceptanceStatus(acceptanceStatus);
			}
			
			if(reviewArr[2] != null) {
				final ProgressStatus progressStatus = new ProgressStatus();
				progressStatus.setId(Long.valueOf(reviewArr[2].toString()));
				review.setProgressStatus(progressStatus);
			}
			
			final Profile profile = new Profile();
			profile.setUserName(reviewArr[5].toString());
			final User candidate = new User();
			candidate.setId(Long.valueOf(reviewArr[3].toString()));
			candidate.setProfile(profile);
			review.setCandidate(candidate);
			
			final User reviewer = new User();
			reviewer.setId(Long.valueOf(reviewArr[4].toString()));
			review.setReviewer(reviewer);
		}
		return review;
	}
	@Override
	public Review getById(Long id) throws SQLException {
		final Review review = this.em.find(Review.class, id);
		return review;
	}
	
}
