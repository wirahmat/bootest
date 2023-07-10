package com.lawencon.bootcamptest.model;

public class AnswerCandidate extends BaseModel{
	private Question question;
	private User candidate;
	private String answerEssay;
	private QuestionOption questionOption;
	private CandidateAssign candidateAssign;
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public User getCandidate() {
		return candidate;
	}
	public void setCandidate(User candidate) {
		this.candidate = candidate;
	}
	public String getAnswerEssay() {
		return answerEssay;
	}
	public void setAnswerEssay(String answerEssay) {
		this.answerEssay = answerEssay;
	}
	public QuestionOption getQuestionOption() {
		return questionOption;
	}
	public void setQuestionOption(QuestionOption questionOption) {
		this.questionOption = questionOption;
	}
	public CandidateAssign getCandidateAssign() {
		return candidateAssign;
	}
	public void setCandidateAssign(CandidateAssign candidateAssign) {
		this.candidateAssign = candidateAssign;
	}
}
