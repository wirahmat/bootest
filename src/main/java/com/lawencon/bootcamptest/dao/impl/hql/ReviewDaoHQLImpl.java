package com.lawencon.bootcamptest.dao.impl.hql;

import java.sql.SQLException;
import java.util.ArrayList;
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

public class ReviewDaoHQLImpl implements ReviewDao{
	
	private final EntityManager em;
	
	public ReviewDaoHQLImpl(SessionFactory sessionFactory) throws SQLException {
		this.em = EntityManagerConfig.get(sessionFactory);
	}
	
	@Override
	public Review insert(Review review) throws SQLException {
		em.persist(review);
		return review;
	}
	
	@Override
	public List<Review> getByReviewerId(Long reviewerId) throws SQLException {
		final List<Review> reviews = new ArrayList<>();
		final String sql = "SELECT "
				+ "r.id, r.candidate.id, r.candidate.profile.userName "
				+ "FROM "
				+ "Review r "
				+ "WHERE "
				+ "r.reviewer.id = :reviewerId";
		
		final List<?> reviewObjs = this.em.createQuery(sql)
				.setParameter("reviewerId", reviewerId)
				.getResultList();
		
		if(reviewObjs.size() > 0) {
			for(Object reviewObj : reviewObjs) {
				final Object[] revieweArr = (Object[]) reviewObj;
				final Review review = new Review();
				review.setId(Long.valueOf(revieweArr[0].toString()));
				
				final Profile profile = new Profile();
				profile.setUserName(revieweArr[2].toString());
				
				final User user = new User();
				user.setId(Long.valueOf(revieweArr[1].toString()));
				user.setProfile(profile);
							
				review.setCandidate(user);
				reviews.add(review);
			}
		}
		
		return reviews;
	}
	
	@Override
	public Review getByCandidateId(Long candidateId) throws SQLException {
		final String sql = "SELECT "
				+ "r.id, r.acceptanceStatus.id, r.progressStatus.id, r.candidate.id, r.reviewer.id, r.candidate.profile.userName "
				+ "FROM "
				+ "Review r "
				+ "WHERE "
				+ "r.candidate.id = :candidateId";
		
		final Object reviewObj = this.em.createQuery(sql)
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
