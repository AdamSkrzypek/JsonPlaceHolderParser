# JsonPlaceHolderParser
This is a project designed for fun and personal development. 
Enitire project is build on Java 18 and Maven 3.8.6.

As part of a project, I'm downloading JSON from https://jsonplaceholder.typicode.com, convert it to domain object and save it separate file.
	
Before first use of program run:
- mvn clean install
- mvn clean package 

Three arguments are required to run the program correctly:
 - url, e.g. url=https://jsonplaceholder.typicode.com/posts
 - directory, e.g. directory=C:\OutputPosts\\
 - type, e.g. type=posts
 
 To run the program, use the previously built JsonPlaceholderParser-jar-with-dependencies.jar in target destination of project.
 Program can be run by calling :
 - java -jar .\JsonPlaceholderParser-jar-with-dependencies.jar url=https://jsonplaceholder.typicode.com/posts directory=C:\OutputPosts\ type=posts

