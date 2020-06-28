package programs;
import java.util.Scanner;

public class WelcomeOutline {
	private Scanner scan = new Scanner(System.in); 
	
	public WelcomeOutline() {
		displayTitle();
		displayMainTopic();
	}
	
	private void displayMainTopic() {
		System.out.println(
				  "\t+----------------------------------------------+\n"
				+ "\t|           ###  #  ### ###  ###  ##           |\n"
				+ "\t|            #  # # ###  #   #    #            |\n"
				+ "\t|            #   #  #   ###  ### ##            |\n"
				+ "\t+----------------------------------------------+\n");
		
		System.out.println("\t                       (1)\n"
				+ "\t+----------------------------------------------+\n"
				+ "\t|                 What is Java?                |\n" 
				+ "\t+----------------------------------------------+\n");
		System.out.println("\t                       (2)\n"
				+ "\t+----------------------------------------------+\n"	
				+ "\t|               How to setup Java?             |\n" 
				+ "\t+----------------------------------------------+\n");
		
		System.out.print("\tAnong gusto mong pag-usapan : ");
		pickTopic(scan.nextLine());
	}
	
	private void pickTopic(String topic) {
		System.out.println();
		switch(topic.toLowerCase()) {
		case "":
		case "1":
			whatIsJava();
			break;
		case "2":
			howToSetupJava();
			break;
		case "wala na":
			System.out.println("\tPaalam :)");
			System.exit(0);
		}
		displayMainTopic();
	}
	
	private void whatIsJava() {
		System.out.println(
				  "\t+----------------------------------------------+\n"
				+ "\t|                 What is Java?                |\n" 
				+ "\t+----------------------------------------------+\n"
				+ "\t|                                              |");
		
		System.out.print("\t|  ~~> A High Level Language                   |\n"
				+ "\t|  paliwanag o sunod : ");
		String str = scan.nextLine();
		System.out.println("\t|                                              |");
		
		if(str.equalsIgnoreCase("paliwanag"))
			System.out.println(
				  "\t|  > High Level Language or HLL means that the |\n"
				+ "\t|  programming language is closer to Natural   |\n"
				+ "\t|  Language or Human Language and further from |\n"
				+ "\t|  Machine Language (010101001).               |\n"
				+ "\t|                                              |\n"
				+ "\t|  > HLL is easier to read than a Low Level    |\n"
				+ "\t|  Language (Assembly Code).                   |\n"
				+ "\t|                                              |\n"
				+ "\t|  > Some HLL are : C, C++, C#, Java, PHP,     |\n"
				+ "\t|  JavaScript, Python.                         |\n"
				+ "\t|                                              |\n"
				+ "\t|                                              |");
		
		System.out.print("\t|  ~~> An Object-Oriented Programming          |\n"
				+ "\t|  paliwanag o sunod : ");
		str = scan.nextLine();
		System.out.println("\t|                                              |");
		
		if(str.equalsIgnoreCase("paliwanag"))
			System.out.println(
				  "\t|  > Object-Oriented Language or OOP is a      |\n"
				+ "\t|  programming paradigm that uses \"objects\" to |\n"
				+ "\t|  encapsulate fields (properties) and methods |\n"
				+ "\t|  (actions).                                  |\n"
				+ "\t|                                              |\n"
				+ "\t|  > OOP has the ability to classify real      |\n"
				+ "\t|  world objects into a code.                  |\n"
				+ "\t|                                              |\n"
				+ "\t|                                              |");
		
		System.out.print("\t|  ulit o sunod : ");
		str = scan.nextLine();
		System.out.println(
				  "\t|                                              |\n"
				+ "\t+----------------------------------------------+\n");
		if(str.equalsIgnoreCase("ulit"))	whatIsJava();
	}
	
	private void howToSetupJava() {
		System.out.println(
				  "\t+----------------------------------------------+\n"
				+ "\t|               How to setup Java?             |\n"
				+ "\t+----------------------------------------------+\n"
				+ "\t|                                              |");
		
		System.out.print(
				  "\t|  ~~> Install Java Development Kit            |\n"
				+ "\t|  paliwanag o sunod : ");
		String str = scan.nextLine();
		System.out.println("\t|                                              |");
		
		if(str.equalsIgnoreCase("paliwanag"))
			System.out.println(
				  "\t|  > Java Development Kit or JDK consists of   |\n"
				+ "\t|  tools that would help in the development of |\n"
				+ "\t|  programs. These tools are : compiler, jar,  |\n"
				+ "\t|  Javadoc, and debugger.                      |\n"
				+ "\t|                                              |\n"
				+ "\t|   >> Compiler is a program that converts a   |\n"
				+ "\t|   code written in a programming language     |\n"
				+ "\t|   into a machine / binary / object code that |\n"
				+ "\t|   a computer can understand. In the case of  |\n"
				+ "\t|   Java codes are compiled into .class file   |\n"
				+ "\t|   or byte code that will then be translated  |\n"
				+ "\t|   during runtime.                            |\n"
				+ "\t|                                              |\n"
				+ "\t|   >> Jar is a package file format that       |\n"
				+ "\t|   connects all of the packages, classes, and |\n"
				+ "\t|   important resources into one production    |\n"
				+ "\t|   file. Jar file format has an extension of  |\n"
				+ "\t|   (.jar).                                    |\n"
				+ "\t|                                              |\n"
				+ "\t|   >> Javadoc is the documentation generator  |\n"
				+ "\t|   of Java. It generates Application          |\n"
				+ "\t|   Programming Interface or API documentation |\n"
				+ "\t|   in HTML format. Javadoc helps other        |\n"
				+ "\t|   programmers reading other programmer's     |\n"
				+ "\t|   source code.                               |\n"
				+ "\t|                                              |\n"
				+ "\t|   >> Debugger is a tool that brings the      |\n"
				+ "\t|   programmer closer to its program. It is    |\n"
				+ "\t|   like a program simulator giving the        |\n"
				+ "\t|   programmer a full control of the program.  |\n"
				+ "\t|                                              |\n"
				+ "\t|  > JDK also contains Java Runtime            |\n"
				+ "\t|  Environment                                 |\n"
				+ "\t|                                              |\n"
				+ "\t|   >> Java Runtime Environment or JRE         |\n"
				+ "\t|   includes supporting libraries (precompiled |\n"
				+ "\t|   programs), core classes, and Java Virtual  |\n"
				+ "\t|   Machine.                                   |\n"
				+ "\t|                                              |\n"
				+ "\t|    >>> Java Virtual Machine or JVM is the    |\n"
				+ "\t|    one responsible in translating a .class   |\n"
				+ "\t|    file to binary executable in runtime.     |\n"
				+ "\t|                                              |\n"
				+ "\t|  > There are three editions of Java :        |\n"
				+ "\t|  Standard Edition (Java SE), Enterprise      |\n"
				+ "\t|  Edition (Java EE), and Micro Edition (Java  |\n"
				+ "\t|  ME). For now, just use the Java SE.         |\n"
				+ "\t|                                              |\n"
				+ "\t|                                              |");
		
		System.out.print(
				  "\t|  ~~> Install a Text Editor or better an IDE  |\n"
				+ "\t|  paliwanag o sunod : ");
		str = scan.nextLine();
		System.out.println("\t|                                              |");
		
		if(str.equalsIgnoreCase("paliwanag")) 
			System.out.println(
				  "\t|  > Text Editors are programs that edits a    |\n"
				+ "\t|  plain text. Example of these are :          |\n"
				+ "\t|  Vim, NotePad, NotePad++, Sublime, VSCode,   |\n"
				+ "\t|  and Atom.                                   |\n"
				+ "\t|                                              |\n"
				+ "\t|  > Integrated Development Environment is a   |\n"
				+ "\t|  software that gives a programmer a place to |\n"
				+ "\t|  write code. It comes with a Code Editor,    |\n"
				+ "\t|  Compiler, and Debugger.                     |\n"
				+ "\t|                                              |\n"
				+ "\t|                                              |");
		
		System.out.print("\t|  ulit o sunod : ");
		str = scan.nextLine();
		System.out.println(
				  "\t|                                              |\n"
				+ "\t+----------------------------------------------+\n");
		if(str.equalsIgnoreCase("ulit"))	howToSetupJava();
	}
	
	private void displayTitle() {
		System.out.println(
				"\t################################################\n"
			  + "\t#### ### ####  ## #####  ###  #### ### ####  ###\n"
			  + "\t### ##### ## #### #### #### ## ## # # # ## #####\n"
			  + "\t### ## ## ##  ### #### #### ## ## ## ## ##  ####\n"
			  + "\t#### # # ### #### #### #### ## ## ##### ## #####\n"
			  + "\t##### # #####  ###  ###  ###  #### ### ####  ###\n"
			  + "\t################################################\n"
			  + "\t###################   ####  ####################\n"
			  + "\t################## # # ## ## ###################\n"
			  + "\t#################### #### ## ###################\n"
			  + "\t#################### #### ## ###################\n"
			  + "\t#################### #####  ####################\n"
			  + "\t################################################\n"
			  + "\t############   ####   #### # #####  ############\n"
			  + "\t########### # # ## ### ## ### ## ### ###########\n"
			  + "\t############# #### ### ## ### ## ### ###########\n"
			  + "\t########### # ####     ### # ###     ###########\n"
			  + "\t############ ##### ### #### #### ### ###########\n"
			  + "\t################################################\n");
		
		System.out.print("\tAnong gagawin : ");
		String start = scan.nextLine();
		if(start.equalsIgnoreCase("Simulan Na") || start.equalsIgnoreCase("Let's Go"))
			System.out.println("\tOKI :)\n");
		else
			System.out.println("\tSnobber :P\n");
	}
	
	public static void main(String args[]) {
		new WelcomeOutline();
	}
}
