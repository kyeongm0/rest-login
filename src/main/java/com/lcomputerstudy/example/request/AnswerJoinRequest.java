package com.lcomputerstudy.example.request;

import java.util.List;

import com.lcomputerstudy.example.domain.Answer;
import com.lcomputerstudy.example.domain.Question;

public class AnswerJoinRequest {
	private int sId;
	private String uId;
	private String aWriter;
	//private List<Answer> answerList;
	private List<Question> questionList;
		
	
	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getaWriter() {
		return aWriter;
	}

	public void setaWriter(String aWriter) {
		this.aWriter = aWriter;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	/*public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}*/
	
	
	
}
