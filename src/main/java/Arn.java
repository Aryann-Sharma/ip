import java.util.Scanner;

public class Arn {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Arn");
        System.out.println("What can I do for you?");
        System.out.print("\n");

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(true) {
            System.out.print("-> ");
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
                System.out.print("\n");
            }
        }

        scanner.close();
    }
}
