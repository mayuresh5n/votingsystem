package com.autovote;

public class MultipleChoiceQuestion extends Question implements QuestionInterface
{
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;

	public String getChoiceA()
	{
		return choiceA;
	}

	public void setChoiceA(String choiceA)
	{
		this.choiceA = choiceA;
	}

	public String getChoiceB()
	{
		return choiceB;
	}

	public void setChoiceB(String choiceB)
	{
		this.choiceB = choiceB;
	}

	public String getChoiceC()
	{
		return choiceC;
	}

	public void setChoiceC(String choiceC)
	{
		this.choiceC = choiceC;
	}

	public String getChoiceD()
	{
		return choiceD;
	}

	public void setChoiceD(String choiceD)
	{
		this.choiceD = choiceD;
	}

	@Override
	public boolean validateQuestion()
	{
		boolean choicenotvalid = this.isEmptyStr(choiceA) || this.isEmptyStr(choiceB) || this.isEmptyStr(choiceC)
						|| this.isEmptyStr(choiceD);
		boolean answerNotvalidlength = this.getCorrectAnswer().length()!=1 ;
		
		boolean answerNotvalid = !(this.getCorrectAnswer().equals("a") || 
				this.getCorrectAnswer().equals("b")||
				this.getCorrectAnswer().equals("c")||
				this.getCorrectAnswer().equals("d"));
		
		if (choicenotvalid || answerNotvalidlength ||answerNotvalid )
		{
		
			return false;
		}
		return true;
	}

	public boolean isEmptyStr(String s)
	{
		if (s == null || s.isEmpty())
		{
			return true;
		}
		return false;
	}

}
