package com.lcomputerstudy.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcomputerstudy.example.domain.Answer;
import com.lcomputerstudy.example.domain.Item;
import com.lcomputerstudy.example.domain.Question;
import com.lcomputerstudy.example.domain.Survey;
import com.lcomputerstudy.example.mapper.SurveyMapper;

@Service("SurveyServiceImple")
public class SurveyServiceImpl implements SurveyService {
	
	@Autowired SurveyMapper surveyMapper;
	
	@Override
	public void addSurvey(Survey survey) {
		surveyMapper.surveyInsert(survey);
		
		for (int i=0; i<survey.getQuestionList().size(); i++) {
			Map<String, Object> questionMap = new HashMap<String, Object>();
			List<Map<String, Object>> qList = new ArrayList<Map<String, Object>>();
			Map<String, Object> qMap;
			questionMap.put("sId", survey.getsId());
			
			qMap = new HashMap<String, Object>();
			qMap.put("title", survey.getQuestionList().get(i).getqTitle());
			qMap.put("type", survey.getQuestionList().get(i).getqType());
			
			qList.add(qMap);
			questionMap.put("qList", qList);
			//questionMap.put("qId", 0);
			surveyMapper.questionInsert(questionMap);
			
			if( survey.getQuestionList().get(i).getqType() != 2) {
				Map<String, Object> itemMap = new HashMap<String, Object>();
				itemMap.put("qId", questionMap.get("qId"));
				itemMap.put("sId", survey.getsId());
				
				Map<String, Object> iMap;
				List<Map<String, Object>> iList = new ArrayList<Map<String, Object>>();
				for (int j=0; j<survey.getQuestionList().get(i).getItemList().size(); j++) {
					iMap = new HashMap<String, Object>();
					iMap.put("value", survey.getQuestionList().get(i).getItemList().get(j).getValue());
					
					iList.add(iMap);
				}
				itemMap.put("iList", iList);
				surveyMapper.itemInsert(itemMap);
			}
		}
	}
	
	@Override
	public int surveyCount() {
		return surveyMapper.surveyCount();
	}
	
	@Override
	public List<Survey> selectSurveyList(Survey survey) {
		return surveyMapper.selectSurveyList(survey);
	}
	
	@Override
	public Survey selectSurveyDetail(int sId) {
		return surveyMapper.selectSurveyDetail(sId);
	}
	
	@Override
	public void surveyDelete(int sId) {
		surveyMapper.surveyDelete(sId);
		surveyMapper.questionDelete(sId);
		surveyMapper.itemDelete(sId);
	}
	
	@Override
	public void addSurveyAnswer(Survey survey) {
		Map<String, Object> answerMap = new HashMap<String, Object>();
		List<Map<String, Object>> aList = new ArrayList<Map<String, Object>>();
		
		answerMap.put("sId", survey.getsId());
		answerMap.put("aWriter", survey.getsWriter());
		answerMap.put("uId", survey.getuId());
		
		for(int i=0; i<survey.getQuestionList().size(); i++) {
			for(int j=0; j<survey.getQuestionList().get(i).getAnswerList().size(); j++) {
				Map<String, Object> aMap = new HashMap<String, Object>();
				aMap.put("qId", survey.getQuestionList().get(i).getqId());
				aMap.put("value", survey.getQuestionList().get(i).getAnswerList().get(j).getValue());
				aList.add(aMap);
			}
		}
		
		answerMap.put("aList", aList);
		
		surveyMapper.addSurveyAnswer(answerMap);
	}
	
	@Override
	public Survey selectSurveyUser(int sId) {
		return surveyMapper.selectSurveyUser(sId);
	}
	
	@Override
	public Survey selectAnswerDetail(Survey survey) {
		return surveyMapper.selectAnswerDetail(survey);
	}
	
	@Override
	public List<Item> itemValueList(Answer answer) {
		return surveyMapper.itemValueList(answer);
	}
	
	@Override
	public List<Map<String, Object>> answerCount(List<Item> itemlist) {
		List<Map<String, Object>> answerCountList = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i<itemlist.size(); i++) {
			Map<String, Object> cMap = new HashMap<String, Object>();
			cMap.put("value", itemlist.get(i).getValue());
			String countWord = itemlist.get(i).getValue();
			int countValue = surveyMapper.answerCount(countWord);
			cMap.put("count", countValue);
			answerCountList.add(cMap);
		}
		
		return answerCountList;
	}
}
