package com.lawencon.bootcamptest.service;


import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.CandidateAssign;
import com.lawencon.bootcamptest.model.QuestionCandidate;
import com.lawencon.bootcamptest.model.QuestionType;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;

public interface CandidateAssignService {
	boolean setCandidate(List<CandidateAssign> candidateAssign, Review review, List<ReviewDetail> reviewDetail, List<QuestionCandidate> questionCandidate);
	List<QuestionType> getAllType() throws SQLException;
	List<CandidateAssign> getId(Long candidateId) throws SQLException;
}
