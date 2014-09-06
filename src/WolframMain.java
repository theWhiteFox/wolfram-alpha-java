package wolfRam;
/*
 *
 * Author :James Martin Gogarty
 * Date : 30-08-2014
 *
 * THIS PROGRAM USES THE WOFLRAM ALPHA API TO TAKE USER INPUTS I.E QUESTIONS
 * AND RESPOND WITH A CONSISE ANSWER.
 * FOR MORE INFO PLEASE SEE THE "README.md" FILE.
 */
// IMPORTS
import java.io.*;
import java.util.Scanner;
//----------------------------------------------------------------------------------------------------
//******************************************Main Method***********************************************
//----------------------------------------------------------------------------------------------------
public class WolframMain {	
	// global objects for program.
	static ProgramFiles headings;
	static ProgramFiles questions;
	static ProgramFiles answers;
	

	public static String splitFile(String currentLine){
		String fileName =null;
		String [] split = currentLine.split(",");
		for (int i =0; i < split.length; i++){
			if (split[i].contains(".list") || split[i].contains(".txt") || split[i].contains(".csv")){
				fileName = split[i];

			}
		}
		return fileName;
	}
	public static void  setup() throws FileNotFoundException, IOException{
		String splitFileReturnedValue =null;

		// read the config file
		File config = new File ("config.csv");
		// if the config.txt file exists create the file to the pars in the file 
		
		if (config.isFile()){
			System.out.println(config.isFile());
			// READ FILE 
			
			Scanner readConfig = new Scanner(config.getName());

			while (readConfig.hasNextLine()){
				String currentLine = readConfig.nextLine();
				// change to cases

				if (currentLine.contains("heading")){

					splitFileReturnedValue = splitFile(currentLine);
					headings = new ProgramFiles(splitFileReturnedValue);


				}
				else if (currentLine.contains("question")){
					splitFileReturnedValue = splitFile(currentLine);
					questions = new ProgramFiles(splitFileReturnedValue);
					//fileNames.add(questions);	
				}
				else if (currentLine.contains("answers")){
					splitFileReturnedValue= splitFile(currentLine);
					answers = new ProgramFiles(splitFileReturnedValue);
					//fileNames.add(answers);
				}
				else {
					System.out.println("ERROR :File name not found. Please check ReadMe under Config File.");
					
				}	 
			}readConfig.close();
		}
		else {
			// file names are default
				headings = new ProgramFiles("headings.list");
				questions = new ProgramFiles("questions.list");
				answers = new ProgramFiles("answers.list");

		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		setup();
		try {
			// staring position to ask user to input a question.
			System.out.print("JAVA PROGRAMING ASIGNMENT VER 0.01.\n" +
					"\n" +
					"Hello and wellcome to Wolfran Alpha Java interface program.\n" +
					"Wolfram Alpha is a Computational Knowledge Engine.\n" +
					"Wolfram Alpha is more than a search engine.\n" +
					"It gives you access to the world's facts and data and calculates answers\n" +
					"across a range of topics, including science, nutrition, history, geography,\n" +
					"engineering, mathematics, linguistics, sports, finance, music and many more.\n" +
					"\n\n");
			// A looping prompt to ask the user to enter a question, with an Exit condition
			Scanner userQ = new Scanner(System.in);
			boolean loop = true;
			while (loop){

				System.out.println("Please enter your question bellow." +
						"\n" +
						"\n" +
						"Type \"exit\" to quit program");
				String userQuestion = userQ.nextLine();
				// Exit Condition, when user enters "exit"
				if (userQuestion.equals("exit")){
					System.out.println("The program will now close.");
					loop = false;
				}
				// condition to ensure the user dose not leave the field blank
				else if (userQuestion.length() != 0){
					// if the field is not empty, search the question file to check if the question has been
					// asked before in the searchQuestionFile method bellow

					searchQuestionFile (userQuestion);
				}	
			}
			userQ.close();
		} catch (FileNotFoundException e) {
			System.out.println("Trouble reading from the file: " + e.getMessage()+"\nPrinting Error Stack.\n");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Trouble reading from the file: " + e.getMessage()+"\nPrinting Error Stack.\n");
			e.printStackTrace();
		}
	}

	public static void split (String splitString){
		// variables
		int titleAndSectioncontentsIndex;
		int sectioncontentsIndex;
		// statement to find where the answer substring( + 25 chars i.e the end of the String bellow) begins
		titleAndSectioncontentsIndex = splitString.indexOf("</title><sectioncontents>");
		// statement to find where the answer substring ends
		sectioncontentsIndex = splitString.indexOf("</sectioncontents>");
		// print to JOptionPane the answer substring within the two constraints
		String answer = splitString.substring(titleAndSectioncontentsIndex + 25,sectioncontentsIndex);

		
		String oneLineAnswer = null;
		if (answer.contains("\n")){

			String [] multipleLineAnswwer = answer.split("\n");

			for (int i = 0; i < multipleLineAnswwer.length; i ++){
				oneLineAnswer = oneLineAnswer + " : " + multipleLineAnswwer[i];
			}
			answers.writeTofile(oneLineAnswer, answers);
		}
		else 
		{
			answers.writeTofile(answer, answers);
		}
		System.out.println("The answer to your question is :\n\n"
				+ answer+"\n\n");
	}

	public static void splitingAnswer(String fullResponse) throws IOException {
		
		String [] splitString = fullResponse.split("<section><title>");
		

		// a loop to find all occurrences of "<section><title>"

		Scanner readHeadings = new Scanner(new File (headings.getFileName()));

		boolean found = false;
		boolean headingFound = false;
		// assign the current line to a variable heading
		while (readHeadings.hasNextLine() && found == false){
			String currentLine = readHeadings.nextLine();
			
			// then check if the heading exists in the split strings.
			for (int i =0 ; i < splitString.length-1; i++){
				if (splitString[i].contains(currentLine)){
					found =true;
					headingFound = true;

					String foundAnswerLine = splitString[i];
					
					split (foundAnswerLine);
				}
			}
		}
		readHeadings.close();
		// if the heading dose not exist in the headings file we get the answer 
		// from the second element on the array 
		if (headingFound == false) {
			String [] heading = splitString[2].split("</title><sectioncontents>");
			String newHeading =heading[0];

		
			
				PrintWriter writeHeading = new PrintWriter(
						new BufferedWriter(
								new FileWriter (headings.getFileName(),true)));
				writeHeading.append(newHeading + "\n");

				writeHeading.close();
			
			// 
			splitingAnswer(fullResponse);
		}
	}
	//----------------------------------------------------------------------------------------------------
	//********************************Wolfranm Alpha method **********************************************
	//----------------------------------------------------------------------------------------------------
	/*
	 * Method to search Wolfram Alpha
	 */
	public static void wolframQuery (String userQuestion)throws FileNotFoundException, IOException {
		WolframAlpha WOLFRAM_ALPHA = new WolframAlpha("329W98-HQ232VPJ92");
		String fullResponse = WOLFRAM_ALPHA.query(userQuestion);
		// condition if Wolfram Alpha has no data on a subject
		if (fullResponse.equals("<noresults></noresults>")){
			System.out.println("Currently Wolfram Alpha has not data on this topic\n\nTry another question");
		}
		else{
			splitingAnswer(fullResponse);
		}
	}

	//----------------------------------------------------------------------------------------------------
	//********************************SEARCH QUESTION FILE method ****************************************
	//----------------------------------------------------------------------------------------------------
	// code to check if question has been asked before
	public static void searchQuestionFile (String userQuestion)throws FileNotFoundException, IOException{
		// check if the files exist
		// scanner set up

		Scanner questionFileReader = new Scanner (questions.getFileName());

		boolean foundQ= false;
		int count = 0;
		while (questionFileReader.hasNextLine() && foundQ == false){
			//loop through the questions line by line,
			//counting the lines until user question is found
			String currentQuestion = questionFileReader.nextLine();
			if (currentQuestion.equalsIgnoreCase(userQuestion)){
				//questionPosition = count;
				System.out.println("This Question Has been asked before.\n\n" +
						"Please wiat while it is retreaved.\n\n"
						);
				foundQ = true;
				searchAnswersFile (count, userQuestion);

			}

			else {
				count ++;
			}
		}

		if (foundQ == false){
			questions.writeTofile(userQuestion, questions);
			//writeQuestionToFile(userQuestion);
			wolframQuery(userQuestion);
		}
		// closing fileReader
		questionFileReader.close();
	}

	//----------------------------------------------------------------------------------------------------
	//****************************************SEARCH ANSWERS FILE*****************************************
	//----------------------------------------------------------------------------------------------------
	public static void searchAnswersFile (int questionPosition, String userQuestion )throws FileNotFoundException, IOException{
		System.out.println(userQuestion);
		Scanner answerFileReader = new Scanner (answers.getFileName());
		int answerCounter = 0;
		try {
			for (int i =0; i <5; i++){
				Thread.sleep(1000);
				System.out.print(" > ");
			}
			//1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}


		boolean found = false;
		while (answerFileReader.hasNextLine() && found == false){
			String currentLineA = answerFileReader.nextLine();

			if (answerCounter == questionPosition){
				found = true;
				System.out.println("The Answer to your Question is :\n\n" +
						currentLineA+"\n\n");
			}
			answerCounter ++;
		}
		// close file reader
		answerFileReader.close();
	}	

}// end of main program bracket