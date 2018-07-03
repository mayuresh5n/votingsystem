package com.autovote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimulatorDriver
{
	private static List<Question> questionlist = new ArrayList<Question>();
	private static List<Student> studentList = new ArrayList<Student>();

	public static void main(String[] args)
	{
		try
		{
			createQuestions();

			printAllquestionsWithCorrectAnswer();

			configureQuestionsForIvoteSystem();

			generateStudentsAndAnswers();
			
			validateWithIVoteService();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void validateWithIVoteService()
	{
		
		IvoteService service = new IvoteService();
		service.setiVotestudentList(studentList);
		service.setiVotequestionlist(questionlist);
		service.execute();
	}

	private static void generateStudentsAndAnswers()
	{
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter number of Students : ");
		//int input = Integer.parseInt(br.readLine());
		Integer input = getRandomNumber(1,5);
		System.out.println(input);
		

		for (int numberOfStudents = 0; numberOfStudents < input; numberOfStudents++)
		{

			System.out.println("Enter 3 digit Student ID (eg. 102,448) : ");
			//String sid = br.readLine().trim();
			String sid = getRandomNumber(101,105).toString();
		    System.out.println(sid);
			
			Student studentObject = getStudentObject(sid);

			 
			
			System.out.println(
					"Answer below questions: Type the choice name (eg. A,C) for Multiple choice questions");

			for (Question question : questionlist)
			{
				printQuestion(question);
				//String answer =br.readLine().trim().toLowerCase();
				String answer = getRandomAnswer(question);
				System.out.println(answer);
				studentObject.addToMap(question.getQuestionID(),answer );

			}

			updateStudentList(studentObject);

		}

	}

	private static String getRandomAnswer(Question question)
	{
         if("M".equals(question.getType()))
         {
        	 Integer m = getRandomNumber(1, 4);
        	 
        	 switch(m)
        	 {
        		 case 1: return "a";
        		 case 2: return "b";
        		 case 3: return "c";
        		 case 4: return "d";
        		 default : return "a";
        	 }
         }
         if(getRandomNumber(1, 5) /2 ==0)
         {
        	 return "true";
         }
         return "false";
       
	}

	private static Integer getRandomNumber(int i, int j)
	{
		Integer randomNum = i + (int)(Math.random() * j);
		return randomNum;
	}

	private static void updateStudentList(Student studentObject)
	{
		for (Iterator<Student> it = studentList.iterator(); it.hasNext();)
		{
			if (it.next().getStudentID().equals(studentObject.getStudentID()))
			{
				it.remove();
			}
		}
		studentList.add(studentObject);
	}
	
	private static void updateQuestionList(Question questionObject)
	{
		for (Iterator<Question> it = questionlist.iterator(); it.hasNext();)
		{
			if (it.next().getQuestionID().equals(questionObject.getQuestionID()))
			{
				it.remove();
			}
		}
		questionlist.add(questionObject);
	}


	private static Student getStudentObject(String sid)
	{
		for (Student s : studentList)
		{
			if (s.getStudentID().equals(sid))
			{
				return s;
			}
		}
		Student s = new Student();
		s.setStudentID(sid);
		return s;
	}

	private static void configureQuestionsForIvoteSystem()
	{

	}

	private static void createQuestions() throws IOException
	{
		BufferedReader br = null;
		try
		{

			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter number of questions : ");
			int input = Integer.parseInt(br.readLine());

			for (int numberOfQuestions = 0; numberOfQuestions < input; numberOfQuestions++)
			{
				System.out.println("Select questionType - Multiple or Single choice (M/S): ");
				String questionType = br.readLine();
				Integer qid = numberOfQuestions + 1;
				if ("M".equals(questionType))
				{
					MultipleChoiceQuestion mcq = new MultipleChoiceQuestion();
					mcq.setType(questionType);

					mcq.setQuestionID(qid.toString());

					System.out.print("Enter question " + (numberOfQuestions + 1) + " :");
					mcq.setQuestionStr(br.readLine());

					System.out.print("Enter answer for choiceA : ");
					mcq.setChoiceA(br.readLine().trim().toLowerCase());
					System.out.print("Enter answer for choiceB : ");
					mcq.setChoiceB(br.readLine().trim().toLowerCase());
					System.out.print("Enter answer for choiceC : ");
					mcq.setChoiceC(br.readLine().trim().toLowerCase());
					System.out.print("Enter answer for choiceD : ");
					mcq.setChoiceD(br.readLine().trim().toLowerCase());

					System.out.print("Enter Correct choice for answer ( eg .A,C) : ");
					String ans =br.readLine().trim().toLowerCase();
					mcq.setCorrectAnswer(ans);
					while(!mcq.validateQuestion())
					{
						System.out.print("Wrong format Please Enter again : ");
						  ans =br.readLine().trim().toLowerCase();
						  mcq.setCorrectAnswer(ans);
					}

					updateQuestionList(mcq);
					System.out.println("!!Question " + (numberOfQuestions + 1) + " Created");
				} else if ("S".equals(questionType))
				{
					SingleChoiceQuestion scq = new SingleChoiceQuestion();
					scq.setType(questionType);
					scq.setQuestionID(qid.toString());

					System.out.print("Enter question " + (numberOfQuestions + 1) + " :");
					scq.setQuestionStr(br.readLine());
					System.out.print("Enter answer (eg.true,false): ");
					String ans =br.readLine().trim().toLowerCase();
					 
					scq.setCorrectAnswer(ans);
					while(!scq.validateQuestion())
					{
						System.out.print("Wrong format Please Enter again :");
						  ans =br.readLine().trim().toLowerCase();
						  scq.setCorrectAnswer(ans);
					}
					updateQuestionList(scq);
					System.out.println("!!Question " + (numberOfQuestions + 1) + " Created");
				}

			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			/*if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}

			}*/
		}
	}

	private static void printAllquestionsWithCorrectAnswer()
	{
		System.out.println("*******Configured Questions: ");
		for (Question q : questionlist)
		{
			printQuestion(q);
			System.out.println("Correct Answer :" + q.getCorrectAnswer());
			System.out.println("********************************************");
		}
	}

	private static void printQuestion(Question q)
	{
		if ("M".equals(q.getType()))
		{
			MultipleChoiceQuestion m = (MultipleChoiceQuestion) q;
			System.out.println(m.getQuestionStr() + "|Available options:");
			System.out.println("choice A : " + m.getChoiceA());
			System.out.println("choice B : " + m.getChoiceB());
			System.out.println("choice C : " + m.getChoiceC());
			System.out.println("choice D : " + m.getChoiceD());

		} else
		{
			System.out.println(q.getQuestionStr());

		}
	}

}
