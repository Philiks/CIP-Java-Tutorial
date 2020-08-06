package programs;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Identifiers {
	private ArrayList<String> identifiers = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);

	private Identifiers() {
		System.out.println("Test your Identifier");
		System.out.println("----------------------------");
		
		menu();
		
		sc.close();
	}
	
	private void menu() {
		System.out.println("\nWhat to do?\n"
						 + "1. Test an identifier.\n"
						 + "2. Access previous identifier.\n"
						 + "3. Get the hell out of here.");
		System.out.print("Choose action: ");
		
		switch(sc.nextLine()) {
			case "1":
				System.out.print("\nEnter your Identifier: ");
				test(1, sc.nextLine());
				break;
			case "2":
				access();
				break;
			case "3":
				System.out.println("\nBye bye.");
				System.exit(0);
				break;
			default:
				System.out.println("\nThat is not on the menu.");
				break;
		}
		
		menu();
	}
	
	/**
	 * @param accessBy 1 if via menu() & 0 if via access()
	 * @param id	   identifier to be tested
	 * @return tells whether the identifier passed or failed
	 */
	private boolean test(int accessBy, String id) {
		if(Pattern.matches("[a-zA-Z_$][\\w$]*", id)) {
			// KEYWORD
			if(isKeyword(id)) {
				System.out.println("How NOT to create identifiers : "
						+ "Be careful. Using JAVA KEYWORDS as identifier.");
				return false;
			} else {
				System.out.println("Your identifier is valid");
				identifiers.add(id);
				return true;
			}
		} 
		
		if(Pattern.matches("[0-9][\\w$]*", id)) {
			// DIGIT IN FRONT
			System.out.println("How NOT to create identifiers : "
					+ "Be careful. Using DIGITS in front of an identifier.");
			return false;
		} 
		
		if(Pattern.matches("[a-zA-Z_$][\\s]*[\\w$]*[\\s]+[\\w$]*", id)) {
			// CONTAINS WHITESPACE
			System.out.println("How NOT to create identifiers : "
					+ "Be careful. Identifiers must not contain WHITESPACE/S.");
			return false;
		}
		
		// USE SYMBOLS OTHER THAN _ AND %
		System.out.println("How NOT to create identifiers : "
				+ "Be careful. [a-z], [A-Z], [0-9], _ (underscore) and $ (dollar sign) \nare the ONLY CHARACTERS VALID.");
		return false;
	}
	
	// CASE-SENSITIVE
	private void access() {
		System.out.println();
		
		if(identifiers.isEmpty()) {
			System.out.println("You haven't tested any identifiers.");
			return;
		}
		
		System.out.print("Enter your Identifier: ");
		String id = sc.nextLine();
		
		if(test(0, id) == false) {
			System.out.println("Your identifier is not even a right one.");
			return;
		}
		
		int result = findIgnoreCase(id);
		if (result == -1) {
			System.out.println("Identifier does not exist");
		} else if(!id.equals(identifiers.get(result))) {
			System.out.println("\"" + id + "\" and \"" + identifiers.get(result) + "\" are not the same.");
			System.out.println("How NOT to create identifiers : "
					+ "Be careful. Identifiers are CASE-SENSITIVE.");
		} else {
			System.out.println("\"" + id + "\" exists.");
		}
	}
	
	/**
	 * @param id identifier to find regardless of casing
	 * @return index if have found
	 */
	private int findIgnoreCase(String id) {
		for(int i = 0; i < identifiers.size(); i++)
			if(id.equalsIgnoreCase(identifiers.get(i)))
				return i;
		
		return -1;	//id did not found
	}
	
	/**
	 * @param id identifier to be checked
	 * @return whether is falls under Java Keywords
	 */
	private boolean isKeyword(String id) {
		String[] keywords = {
				"abstract",	"continue",	"for",	"new",	"switch",
				"assert",	"default",	"goto",	"package",	"synchronized",
				"boolean",	"do",	"if",	"private",	"this",
				"break",	"double",	"implements",	"protected",	"throw",
				"byte",	"else",	"import",	"public",	"throws",
				"case",	"enum",	"instanceof",	"return",	"transient",
				"catch",	"extends",	"int",	"short",	"try",
				"char",	"final",	"interface",	"static",	"void",
				"class",	"finally",	"long",	"strictfp",	"volatile",
				"const",	"float",	"native",	"super",	"while"};
		String[] reserved_keywords = {"const", "goto"};
		String[] literals = {"true", "false", "null"};
		
		for(String temp : keywords) {
			if(id.equals(temp)) {
				System.out.println("\"" + id + "\" is a Java Keyword");
				return true;
			}
		}
		
		for(String temp : reserved_keywords) {
			if(id.equals(temp)) {
				System.out.println("\"" + id + "\" is a Java Reserved Keyword");
				return true;
			}
		}
		
		for(String temp : literals) {
			if(id.equals(temp)) {
				System.out.println("\"" + id + "\" is a Java Literals");
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String args[]) {
		new Identifiers();
	}
}
