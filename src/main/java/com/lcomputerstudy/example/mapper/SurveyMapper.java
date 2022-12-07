package com.lcomputerstudy.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.lcomputerstudy.example.domain.Answer;
import com.lcomputerstudy.example.domain.Item;
import com.lcomputerstudy.example.domain.Question;
import com.lcomputerstudy.example.domain.Survey;

@Mapper
public interface SurveyMapper {
	
	public void surveyInsert(Survey survey);
	
	public void questionInsert(Map<String, Object> questionMap);
	
	public void itemInsert(Map<String, Object> itemMap);
	
	public int surveyCount();
	
	public List<Survey> selectSurveyList(Survey survey);
	
	public Survey selectSurveyDetail(int sId);
	
	public void surveyDelete(int sId);
	
	public void questionDelete(int sId);
	
	public void itemDelete(int sId);
	
	public void addSurveyAnswer(Map<String, Object> answerMap);
	
	public Survey selectSurveyUser(int sId);
	
	public Survey selectAnswerDetail(Survey survey);
	
	public List<Item> itemValueList(Answer answer);
	
	public int answerCount(String countWord);
}
