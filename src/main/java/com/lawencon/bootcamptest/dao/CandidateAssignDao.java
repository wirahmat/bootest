package com.lawencon.bootcamptest.dao;

import java.sql.SQLException;
import java.util.List;

import com.lawencon.bootcamptest.model.CandidateAssign;

public interface CandidateAssignDao {
	CandidateAssign insert(CandidateAssign candidateAssign) throws SQLException;
	List<CandidateAssign> getById(Long candidateId) throws SQLException;
}
