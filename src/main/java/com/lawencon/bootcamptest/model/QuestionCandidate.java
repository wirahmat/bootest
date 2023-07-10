package com.lawencon.bootcamptest.model;

public class QuestionCandidate extends BaseModel{
	private User candidate;
	private Question question;
	private User hr;
	private CandidateAssign candidateAssign;
	public User getCandidate() {
		return candidate;
	}
	public void setCandidate(User candidate) {
		this.candidate = candidate;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public User getHr() {
		return hr;
	}
	public void setHr(User hr) {
		this.hr = hr;
	}
	public CandidateAssign getCandidateAssign() {
		return candidateAssign;
	}
	public void setCandidateAssign(CandidateAssign candidateAssign) {
		this.candidateAssign = candidateAssign;
	}
}
