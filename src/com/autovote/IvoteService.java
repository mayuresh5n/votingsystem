package com.autovote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IvoteService
{
	private static List<Student> iVotestudentList;
	private static List<Question> iVotequestionlist;

	public void execute()
	{
		for (Student s : iVotestudentList)
		{
			Map<String, String> qAndAMap = s.getQuestionAndAnswerMap();

			System.out.println("Result for Student ID: " + s.getStudentID());

			for (Map.Entry<String, String> entry : qAndAMap.entrySet())
			{

				if (correctAnswerSelected(entry.getKey(), entry.getValue()))
				{
					System.out.println("Question " + entry.getKey() + " : " + "Correct");
				} else
				{
					System.out.println("Question " + entry.getKey() + " : " + "Wrong");
				}
			}
		}
	}

	private boolean correctAnswerSelected(String key, String value)
	{
		for(Question q :iVotequestionlist)
		{
			if(q.getQuestionID().equals(key) && q.getCorrectAnswer().equalsIgnoreCase(value.trim()))
			{
				return true;
			}
		}
		return false;
	}

	public static List<Student> getiVotestudentList()
	{
		return iVotestudentList;
	}

	public static void setiVotestudentList(List<Student> iVotestudentList)
	{
		IvoteService.iVotestudentList = iVotestudentList;
	}

	public static List<Question> getiVotequestionlist()
	{
		return iVotequestionlist;
	}

	public static void setiVotequestionlist(List<Question> iVotequestionlist)
	{
		IvoteService.iVotequestionlist = iVotequestionlist;
	}

}
