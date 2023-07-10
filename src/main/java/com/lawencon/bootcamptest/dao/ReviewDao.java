package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.Review;

public interface ReviewDao {
	Review insert(Review review) throws SQLException;
	List<Review> getByReviewerId (Long reviewerId) throws SQLException;
	Review getByCandidateId(Long candidateId) throws SQLException;
	Review getById(Long id) throws SQLException;
}
