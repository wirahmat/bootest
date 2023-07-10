package com.lawencon.bootcamptest.service;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.AnswerCandidate;
import com.lawencon.bootcamptest.model.CandidateFile;
import com.lawencon.bootcamptest.model.FileType;
import com.lawencon.bootcamptest.model.QuestionOption;
import com.lawencon.bootcamptest.model.Review;
import com.lawencon.bootcamptest.model.ReviewDetail;
import com.lawencon.bootcamptest.model.User;

public interface AnswerCandidateService {
	List<FileType> getAllFileType() throws SQLException;
	boolean setAnswer(List<CandidateFile> candidateFile, List<AnswerCandidate> answerCandidate, ReviewDetail reviewDetail, Review updateStatus, User user, double isAnswer, double totalMultiChoice, Long candidateId);
	List<QuestionOption> getOption(Long questionId) throws SQLException; 
	List<AnswerCandidate> getAnswerByType(String typeCode) throws SQLException;
	
}
