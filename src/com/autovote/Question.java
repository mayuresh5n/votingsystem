package com.autovote;

public class Question 
{
	private String questionID;
	private String questionStr;	
	private String type;
	private String correctAnswer;
	
	public String getQuestionStr()
	{
		return questionStr;
	}
	public void setQuestionStr(String questionStr)
	{
		this.questionStr = questionStr;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getCorrectAnswer()
	{
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}
	public String getQuestionID()
	{
		return questionID;
	}
	public void setQuestionID(String questionID)
	{
		this.questionID = questionID;
	}
}
