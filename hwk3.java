import java.util.Scanner;
import java.util.Stack;

class hwk3 {

    public static void reverseWords(String sentence) {
        Stack<Character> stack = new Stack<>();
        String array[] = sentence.split(" ", 0);
        String newSentence = " ";

        for (String pos : array) {
            for (int i = pos.length() - 1; i >= 0; i--) {
                stack.push(pos.charAt(i));
                newSentence += stack.pop();
            }
            newSentence += " ";
        }

        System.out.printf("Your new word is:%s\n", newSentence);
    }

    public static void main(String[] main) {
        Scanner scan = new Scanner(System.in);

        String test = " ";

        while (!test.equals("end")) {
            System.out.printf("Type 'end' to end the program otherwise enter a sentence: ");
            test = scan.nextLine();

            if (test.equals("end") || test.equals("End")) {
                break;
            }
            System.out.printf("The original word is: %s\n", test);
            reverseWords(test);
        }
    }
}