import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class DataTesting {

	public static void main(String[] args) throws IOException {
		//This program uses Bayes' Theorem to calculate probabilities.
		//Instantiating new variable for later probability calculations.
		//Overall likelihood of being a member of this party.
		double bday = 0.0;
		double pity = 0.0;
		double pizza = 0.0;
		double toga = 0.0;
		//Number of parties
		final int numparty = 4;
		//Initial probability of being of a certain party
		double probbday = 1.0/numparty;
		double probpity = 1.0/numparty;
		double probpizza = 1.0/numparty;
		double probtoga = 1.0/numparty;
		//Variable for answer input from user is instantiated
		double ansbday = 0.0;
		double anspity = 0.0;
		double anspizza = 0.0;
		double anstoga = 0.0;
		//Values for percent of time members of a party agree with answer.
		//This is because not all members of a party agree on all issues.
		//Members of one party can agree with members of other parties on certain issues.
		double sameparty = 0.75;
		double diffparty = 0.15;
		//sum is sum of the products of probability as it updates and the likelihood of a party agreeing with answer.
		double sum;
		//Once a party probability is equal to or above this, the guessed party is displayed.
		double certainty = 0.90;
		
		String[][] questions = {
				{"Who should be president?",
					"\nA. Pizza the Hut from Spaceballs.",
					"\nB. We should have a Caesar, not a president.",
					"\nC. Whoever has a birthday coming up.",
					"\nD. Woe is me, I can't decide."},
				{"How can the government help the hungry?",
					"\nA. Give out pizza.",
					"\nB. Have a toga contest and feed the winners.",
					"\nC. Give out birthday cake.",
					"\nD. Pity the fools."},
				{"What is the role of government?",
					"\nA. I don't know, slice it however you want.",
					"\nB. Enfore laws about proper attire, namely togas.",
					"\nC. To issue birth certificates.",
					"\nD. To have pity on the less fortunate."},
				{"Do you approve of the job congress is doing?",
					"\nA. No, I'd like to give them a pizza my mind.",
					"\nB. No, they have got to-ga.",
					"\nC. Yes, it's what they were born to do.",
					"\nD. No, they're pitiful."},
				{"If the election was held today, would you go vote?",
					"\nA. If they're giving out pizza.",
					"\nB. No, I have a toga themed event to go to.",
					"\nC. It's my birthday and I'll vote if I want to.",
					"\nD. Yes, it's a pity that some people don't."},
				{"What is your stance on the death penalty?",
					"\nA. It's fine, but their last meal should be pizza.",
					"\nB. It should be a thing, but only on the ides of March.",
					"\nC. No, let's celebrate their birth, not their death.",
					"\nD. Have pity, don't kill them."},
				{"Should there be a minimun wage?",
					"\nA. Only for pizza delivery drivers.",
					"\nB. Yes, how else do you expect people to afford togas?",
					"\nC. Yes, it should increase on every fifth birthday.",
					"\nD. Yes, I pity those who can't afford a decent living."},
				{"Should elected officials adhere to the constitution?",
					"\nA. Yes, it's the crust of the whole government.",
					"\nB. No, except for any toga related amendments.",
					"\nC. Yes, everyone should from the day they're born.",
					"\nD. Yes, it's pitiful that they wouldn't."},
				{"What should be declared a national holiday?",
					"\nA. Pizza day.",
					"\nB. Toga day.",
					"\nC. My birthday.",
					"\nD. A day of pity."},
				{"Should members of the house of representatives have term limits?",
					"\nA. Yes, new candidates deserve a slice of the pie.",
					"\nB. Yes, except for members of the toga party.",
					"\nC. No, serving the people is their birthright.",
					"\nD. Yes, I pity new candidates who can't get in."},
				{"Do you approve of the current president?",
					"\nA. No, he's too cheesy.",
					"\nB. Yes, he's a total bruh.",
					"\nC. No, He wasn't born in my generation.",
					"\nD. Yes, he has pity for those in need."},
				{"Do you support keeping the supreme court at 9 justices?",
					"\nA. We should be topping it off with one more.",
					"\nB. There ahould only be one dictator, not justices.",
					"\nC. I was born on the 9th, so that sounds right.",
					"\nD. It's a pity there aren't more."},
				{"Should the government officially declare cereal a soup?",
					"\nA. Yes, because cereal is a soup, but pizza is not.",
					"\nB. Yes. Government can do whatever it wants. I wear a toga sometimes.",
					"\nC. No. Although cereal is a soup, it's not for the government to decide. I like birthdays.",
					"\nD. No. It's a pity to even have to discuss this since cereal is clearly a soup."},
				{"Which party should hold the senate?",
					"\nA. The Pizza Party.",
					"\nB. The Toga Party.",
					"\nC. The Birthday Party.",
					"\nD. The Pity Party."},
				{"Would you support legislation to ban the sale of flour?",
					"\nA. No. I need it for pizza.",
					"\nB. Really? Is this even a thing? I have a toga on, btw.",
					"\nC. No. I need it for birthday cake.",
					"\nD. Yes. It's a pity that it's high in carbs and makes people fat."},
				{"Should we become a full-blown communist country?",
					"\nA. No. Pizza is food and communists don't have food.",
					"\nB. As long as I can wear a toga.",
					"\nC. No. You wouldn't be allowed to celebrate birthdays.",
					"\nD. No, but I have pity for people in communist countries."},
				{"Who should be on the next coin?",
					"\nA. Chuck E. Cheese.",
					"\nB. Augustus Caesar.",
					"\nC. Shawty.",
					"\nD. Mr. T."},
				{"Should the USDA add another food group to the food pyramid?",
					"\nA. Yes. Pizza.",
					"\nB. Yes. Olives.",
					"\nC. Yes, actually 2 more, cake and ice cream.",
					"\nD. I'm too overcome with pity to even thing about this right now."},
				{"Should the maijuanas be legal in all states.",
					"\nA. No. Drugs are bad, mmmkay. Pizza is a better alternative.",
					"\nB. Yes. Everyone can get high and wear togas.",
					"\nC. No, except for on your birthday.",
					"\nD. No. It's a pity how many lives are ruined by drugs."},
				{"Should it be legal to wear a wreath on your head?",
					"\nA. I don't care as long as pizza is legal.",
					"\nB. Yes! That's exactly what life is all about.",
					"\nC. Sure. I prefer birthday cone hats though.",
					"\nD. It's a pity you have to ask this."}				
		};	
		//String arrays for input answers and answers stored in files.
		String[] answers = new String[questions.length];
		String[] file1answers = new String[questions.length];
		String[] file2answers = new String[questions.length];
		String[] file3answers = new String[questions.length];
		String[] file4answers = new String[questions.length];
		
		System.out.println("For each question, type the letter for your stance on the matter.\n"
				+ "I will guess your political party based on your beliefs.\n"
				+ "The parties are: Birthday, Pity, Pizza, and Toga.\n");
		//Scanner for user input
		Scanner input = new Scanner(System.in);
		//Import files
		File file1 = new File("bday.txt");
		File file2 = new File("pity.txt");
		File file3 = new File("pizza.txt");
		File file4 = new File("toga.txt");
		//Scanner to read files
		Scanner infile1 = new Scanner(file1);
		Scanner infile2 = new Scanner(file2);
		Scanner infile3 = new Scanner(file3);
		Scanner infile4 = new Scanner(file4);
		
		//For loop. It starts by filling the 5 arrays as each question is asked.
		for (int i = 0; i < answers.length; i++) {
			System.out.println(Arrays.deepToString(questions[i]));
			answers[i] = input.nextLine();			
			file1answers[i] = infile1.nextLine();
			file2answers[i] = infile2.nextLine();
			file3answers[i] = infile3.nextLine();
			file4answers[i] = infile4.nextLine();
		//These if statements compare input answer to the party files and update the variable to later calculate probability.
			if(answers[i].equalsIgnoreCase(file1answers[i])) {
				System.out.println("same as birthday");
				ansbday = sameparty;
				anspity = diffparty;
				anspizza = diffparty;
				anstoga = diffparty;
			} 
			else if(answers[i].equalsIgnoreCase(file2answers[i])) {
				System.out.println("same as pity");
				ansbday = diffparty;
				anspity = sameparty;
				anspizza = diffparty;
				anstoga = diffparty;
			}	
			else if(answers[i].equalsIgnoreCase(file3answers[i])) {
				System.out.println("same as pizza");
				ansbday = diffparty;
				anspity = diffparty;
				anspizza = sameparty;
				anstoga = diffparty;
			}
			else if(answers[i].equalsIgnoreCase(file4answers[i])) {
				System.out.println("same as toga");
				ansbday = diffparty;
				anspity = diffparty;
				anspizza = diffparty;
				anstoga = sameparty;
			}
			else {
			System.out.println("not same");
			}
		//New probability is calculated.
			sum = probbday*ansbday + probpity*anspity + probpizza*anspizza + probtoga*anstoga;
			bday = ansbday*probbday/sum;
			pity = anspity*probpity/sum;
			pizza = anspizza*probpizza/sum;
			toga = anstoga*probtoga/sum;
			probbday = bday;
			probpity = pity;
			probpizza = pizza;
			probtoga = toga;
		//Probabilities are displayed. I did this to monitor that the math was working correctly.
			System.out.println("\nProb of pizza is " + probpizza);
			System.out.println("Prob of toga is " + probtoga);
			System.out.println("Prob of birthday is " + probbday);
			System.out.println("Prob of pity is " + probpity);
			System.out.println("Total probability is " + (probbday + probpity + probpizza + probtoga) +"\n");
		//Compares to certainty threshold to exit loop and make prediction
			
			if (probpizza >= certainty) {
				System.out.println('\n' + "You're a member of the pizza party.");
				break;
			}
			else if (probtoga >= certainty) {
				System.out.println('\n' + "You're a member of the toga party.");
				break;
			}
			else if (probbday >= certainty) {
				System.out.println('\n' + "You're a member of the birthday party.");
				break;
			}
			else if (probpity >= certainty) {
				System.out.println('\n' + "You're a member of the pity party.");
				break;
			}

		}
		//Displays in case no party is at 90% probability.
		if (probbday < certainty && probpity < certainty && probpizza < certainty && probtoga < certainty) {
			System.out.println('\n' + "You got me beat.");
		}
	//Close scanners.
	input.close();
	infile1.close();
	infile2.close();
	infile3.close();
	infile4.close();
	}
}