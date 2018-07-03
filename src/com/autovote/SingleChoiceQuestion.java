package com.autovote;

public class SingleChoiceQuestion extends Question implements QuestionInterface
{
	@Override
	public boolean validateQuestion()
	{
		if (this.getCorrectAnswer() != null || !this.getCorrectAnswer().isEmpty())
		{
			if(this.getCorrectAnswer().equals("true")||
					this.getCorrectAnswer().equals("false"))
			{
				return true; 
			}
		}
		return false;
	}
}
