package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.ReviewDetail;


public interface ReviewDetailDao {
	ReviewDetail insert(ReviewDetail reviewDetail) throws SQLException;
	List<ReviewDetail> getAllById(Long reviewerId) throws SQLException;
	ReviewDetail getByReview(Long reviewId) throws SQLException;
	ReviewDetail getByCandidateId(Long candidateId) throws SQLException;
	List<ReviewDetail> getDetailById(Long reviewerId) throws SQLException;
	ReviewDetail getByCandidateAssignId (Long candidateAssignId) throws SQLException;
	ReviewDetail getById(Long id) throws SQLException;
}
