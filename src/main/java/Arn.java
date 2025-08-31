import java.util.Scanner;

public class Arn {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Arn");
        System.out.println("What can I do for you?");
        System.out.print("\n");

        Scanner scanner = new Scanner(System.in);
        String[] strArr = new String[100];
        int freePointer = 0;
        String input = "";

        while(true) {
            System.out.print("-> ");
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int index = 1;
                for (String task: strArr) {
                    if (task == null) {
                        break;
                    }
                    System.out.println(index + ". " + task);
                    index++;
                }
                System.out.print("\n");
            } else {
                strArr[freePointer] = input;
                freePointer++;
                System.out.println("added: " + input);
                System.out.print("\n");
            }
        }

        scanner.close();
    }
}
