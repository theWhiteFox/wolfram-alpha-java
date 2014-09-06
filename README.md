CONTENTS OF THIS FILE
---------------------
* Introduction
* Requirements
* Recommended modules
* Installation
* Configuration
* Troubleshooting

Introduction
-------------
This program was developed as part of my computer programming module in college. 
The program connects to the Wolfram Alpha website, which is a Computational Knowledge Engine.  
It is more than a search engine. 
It gives you access to the world's facts and data and calculates answers across a range of topics
and returns a concise answer. 
The project consisted of the requirements bellow:
1 - Access the Wolfram Alpha site via the API and submit a user generated question to it 
2 - Display a concise answer to the user Question. 
	This should be done by splitting the returned string(answer) 
3- The Answer and the question should be stored in files.
	When a user submits a Question the program should check if the question has been asked before. 
	if the question has the program should return the relevant answer in the Answer file. 
4 - The program should keep asking for a user Question until the user exits the program.
ADDITIONAL REQUIREMENTS 
These requirements where added by myself at a later stage
5 - Add a config file.
	This file can be edited by the user. 
	The user can configure the name, path and extension of the Question, headings and answer file.
	for example \user\you\Documents\answers.csv , or the  .txt, .list extension can be used.
6 - The project was only meant to answer 7 questions 
	1 - Who is the president of Ireland? -: Result Tag
	2 - How are you? -: Response Tag
	3 - How many minutes are there until 14:15 10th of January? -: Result Tag
	4 - Is Wolfram Alpha better than Google?  -: Response Tag
	5 - When is the next leap year? -: Result Tag
	6 - How many calories are in a bottle of Coke? -: Result Tag
	7 - who is the president of the usa ?  -: Result Tag

	This meant that we only has to find the answer tag for these questions.
	-: Result Tag
	-: Response Tag
	
	I have included a method which will find and record new tags found from all Questions asked.
	As of writing this WA has over 140 response tags.
	
Requirements
------------
1 - Java runtime and SDK installed.
2 - Connection to the Internet
3 - IDE for example Eclipse 
4 - Wolfram Alpha API
	This API is available via the wolframalpha.com site.
	Registration is required. 
	Also the   
	* commons-codec-1.3.jar
 	* httpclient-4.0.1.jar
 	* httpcore-4.0.1.jar
	* commons-logging.jar
	
	are also needed. This files will be in the “JAR_files” folder on github.	

Installation
------------
IN THIS EXAMPLE ECLIPSE IDE IS USED. OTHER IDE'S MAY DIFFER.
1 - Create a project called Asign and create a package in that folder called WolfRam.
2 - Copy the  program files to the WolfRam package on your IDE
				Programfiles.java
				WolframAlpha.java
				WolframMain.java
				 
	EXMAPLE FILE STRUCTURE 
		
		Asign
		src
			wolfRam  (package name)
				Programfiles.java
				WolframAlpha.java
				WolframMain.java
			JRESystem Libary
				Standard libraries 
			Referenced Libaries (this folder will be created when the jar file are installed and built)
			lib (this folder will also be created during the build jars processes.
		EXAMPLEconfig.csv
	RemoteSystemTempFiles

3 - Download the required libraries
	Download and build a path to the libraries memtioned above.
	* WolframAlpha.jar
 		From the wolfram site
 		
 	Bellow are libaries need to run the program also.
 	these file are included in the github folder
 	* commons-codec-1.3.jar
 	* httpclient-4.0.1.jar
 	* httpcore-4.0.1.jar
 	* commons-logging.jar
	
4 - Create the lib folder as shown in the file structure above.
5 - copy the libraries to this file.
6 - right click on the Asign folder. In the menu go to 
		Build path
		and then Configure Build path
7 - in the resulting dialog click on the libraries tab
8 - click on the Add JARs... button
9 - locate the lib folder and select the jar files and click OK
		the libraries are now in the build path
10 -  click on the ok on the properties dialog 
		the files are now in the Referenced folder.
11 – click on the Asign folder and select create a file.
	In this option create a file named EXAMPLEconfig.csv
	Copy the contents of the file of the same name in github to the new file.
	

11 - You can now compile the program and run the program.

Additional info
---------------
This Program is configured to create program file such as the answer file.
If you wish to change the paths and names in the IDE open the EXAMPLEconfig.csv
and change the example data. Then rename the file to config.csv.
The program will now save the files to the locations in the config file.
!! Absolute paths should be user or an error will be created.



Create A Runnable JAR file
--------------------------
To run this program outside of the IDE you must create a runnable jar file.
To do this follow the instructions bellow.

1 - Click on the Asign folder.
2 - click on the Export menu option.
3 - In the resulting dialog click the Java folder
		then select Runnable JAR file
4 - In the Export Jar file dialog 
		1 - click the launch configuration rolldown menu.
			There will be many options here depending on your setup and history
			click the most recent version of the Asign program.
		2 - Select the export path where the file will be saved.
		3 - on the library handling option select the second option 
			Package required libraries to generated JAR
		4 - Then click OK
This will create the JAR file will be in the saved path.

RUNNING THE FILE
---------------

An additional config file option is available in this program. 
	If you create a .csv .txt or .list file in the JAR program location you can 
	configure where program files are located and customise their names.
	To set up this file open your text editor and enter the following to change file names.
		for example
			headings,C:\Users\me\Documents\headingNew.list
			questions,C:\Users\me\Documents\questionnew.list
			answers,C:\Users\me\Documents\answers.txt
			
			!! Absolute paths should be user or an error will be created
	The system will automatically place all program will in the current working directory otherwise
	
To run the program on Windows machines 
	1 - open the command line
	2 - move to directory of the file. 
	3 run the command "java -jar path/to/file"
		The user can also cahnge to the dir and run the file form that dir 
		
	4 type in your question. 

To run on Linux Systems 
	1 - open the terminal
	2 - move to directory of the file.
	4 - change the permissions of the Dir with the command 
		"chmod 755 /path/to/file" without quotes  
	4 - run the command ./fileName . where the filename is the name of the file
	5 - type in your question.


**note if the config file dose not exit the program files will be created in the present working directory 
the user is in. 


Troubleshooting
---------------
For answer to any trouble shooting questions please leave comments on the github page.

