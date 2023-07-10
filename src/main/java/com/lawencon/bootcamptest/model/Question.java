package com.lawencon.bootcamptest.model;

import java.util.List;

public class Question extends BaseModel{
	private String question;
	private QuestionTopic questionTopic;
	private QuestionType questionType;
	private QuestionPackage questionPackage;
	private List<QuestionOption> questionOptions;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public QuestionTopic getQuestionTopic() {
		return questionTopic;
	}
	public void setQuestionTopic(QuestionTopic questionTopic) {
		this.questionTopic = questionTopic;
	}
	public QuestionType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	public QuestionPackage getQuestionPackage() {
		return questionPackage;
	}
	public void setQuestionPackage(QuestionPackage questionPackage) {
		this.questionPackage = questionPackage;
	}
	public List<QuestionOption> getQuestionOptions() {
		return questionOptions;
	}
	public void setQuestionOptions(List<QuestionOption> questionOptions) {
		this.questionOptions = questionOptions;
	}
	
}
