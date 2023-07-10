package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;

import com.lawencon.bootcamptest.model.CandidateFile;

public interface CandidateFileDao {
	CandidateFile insert(CandidateFile candidateFile) throws SQLException;
}
