package com.lawencon.bootcamptest.model;

public class Review extends BaseModel{
	private ProgressStatus progressStatus;
	private AcceptanceStatus acceptanceStatus;
	private User candidate;
	private User reviewer;
	public ProgressStatus getProgressStatus() {
		return progressStatus;
	}
	public void setProgressStatus(ProgressStatus progressStatus) {
		this.progressStatus = progressStatus;
	}
	public AcceptanceStatus getAcceptanceStatus() {
		return acceptanceStatus;
	}
	public void setAcceptanceStatus(AcceptanceStatus acceptanceStatus) {
		this.acceptanceStatus = acceptanceStatus;
	}
	public User getCandidate() {
		return candidate;
	}
	public void setCandidate(User candidate) {
		this.candidate = candidate;
	}
	public User getReviewer() {
		return reviewer;
	}
	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}
}
