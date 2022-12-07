package com.lcomputerstudy.example.service;

import java.util.List;
import java.util.Map;

import com.lcomputerstudy.example.domain.Answer;
import com.lcomputerstudy.example.domain.Item;
import com.lcomputerstudy.example.domain.Question;
import com.lcomputerstudy.example.domain.Survey;


public interface SurveyService {
	public void addSurvey(Survey survey);
	
	public int surveyCount();
	
	public List<Survey> selectSurveyList(Survey survey);
	
	public Survey selectSurveyDetail(int sId);
	
	public void surveyDelete(int sId);
	
	public void addSurveyAnswer(Survey survey);
	
	public Survey selectSurveyUser(int sId);
	
	public Survey selectAnswerDetail(Survey survey);
	
	public List<Item> itemValueList(Answer answer);
	
	public List<Map<String, Object>> answerCount(List<Item> itemlist);
}
