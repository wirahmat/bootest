package com.lawencon.bootcamptest.model;

public class ReviewDetail extends BaseModel {
	private Double grade;
	private String notes;
	private Review review;
	private CandidateAssign candidateAssign;
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public CandidateAssign getCandidateAssign() {
		return candidateAssign;
	}
	public void setCandidateAssign(CandidateAssign candidateAssign) {
		this.candidateAssign = candidateAssign;
	}
}
