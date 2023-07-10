package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;

public interface ReviewService {
	Review setReviewer(Long candidateId, Long hrId, Long reviewerId) throws SQLException;
	List<Review> getAllById(Long reviewerId) throws SQLException;
	List<ReviewDetail> getAllDetail(Long reviewerId) throws SQLException;
	Review update(Review review) throws SQLException;
	boolean update(ReviewDetail reviewDetail, Review review) throws SQLException;
	ReviewDetail getByReview(Long reviewId) throws SQLException;
	ReviewDetail getByCandidate(Long candidateId) throws SQLException;
	ReviewDetail getIdByCandidateAssign(Long candidateAssignId) throws SQLException;
	List<ReviewDetail> getDetailById(Long reviewerId) throws SQLException;
	Review getReviewByCandidate(Long candidateId) throws SQLException;
	ReviewDetail countScore(ReviewDetail reviewDetail, double isAnswer, double totalMultiChoice) throws SQLException;
}
