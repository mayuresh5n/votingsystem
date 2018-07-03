package com.autovote;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Student
{
	 
	private String studentID;
	private Map<String, String> questionAndAnswerMap;

	 

	public String getStudentID()
	{
		return studentID;
	}

	public void setStudentID(String studentID)
	{
		this.studentID = studentID;
	}

	public Map<String, String> getQuestionAndAnswerMap()
	{
		return questionAndAnswerMap;
	}

	public void setQuestionAndAnswerMap(Map<String, String> questionAndAnswerMap)
	{
		this.questionAndAnswerMap = questionAndAnswerMap;
	}

	public void addToMap(String questionID, String answer)
	{

		if (this.questionAndAnswerMap != null)
		{
			if ((questionID != null || !questionID.isEmpty()) && (answer != null || !answer.isEmpty()))
			{
				this.questionAndAnswerMap.put(questionID, answer.trim());

			} else
			{
				System.out.println("Opps wrong Inputs!! Answer not recorded");

			}
		} else
		{
			this.questionAndAnswerMap = new HashMap<String, String>();
			this.questionAndAnswerMap.put(questionID, answer.trim());
		}
	}

}
