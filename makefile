# Author:	Chelsea Rath & Terry Payne
# Program:	Network Programming Assignment
# File:		makefile
# Due Date:	12/6/2011
# Description:	This is a make file to compile all the required files for this assignment.

all: fileClient

fileClient: fileServer fileClient.java
	javac fileClient.java

fileServer: fileServer.java
	javac fileServer.java
