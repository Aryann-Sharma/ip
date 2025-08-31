import java.util.Scanner;

public class Arn {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Arn");
        System.out.println("What can I do for you?");
        System.out.print("\n");

        Scanner scanner = new Scanner(System.in);
        Task[] taskArr = new Task[100];
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
                for (Task task: taskArr) {
                    if (task == null) {
                        break;
                    }
                    System.out.println(index + ". [" + task.getStatusIcon() + "] " + task.description);
                    index++;
                }
            } else if (input.contains(" ")) {
                String[] parts = input.split(" ");
                if (parts[0].equals("mark")) {
                    int i = Integer.parseInt(parts[1]) - 1;
                    if (taskArr[i] != null) {
                        taskArr[i].markAsDone();
                        System.out.println("Task marked as done:");
                        System.out.println(" [" + taskArr[i].getStatusIcon() + "] " + taskArr[i].description);
                    }
                } else if (parts[0].equals("unmark")) {
                    int i = Integer.parseInt(parts[1]) - 1;
                    if (taskArr[i] != null) {
                        taskArr[i].markAsNotDone();
                        System.out.println("Task marked as not done:");
                        System.out.println(" [" + taskArr[i].getStatusIcon() + "] " + taskArr[i].description);
                    }
                } else {
                    taskArr[freePointer] = new Task(input);
                    freePointer++;
                    System.out.println("added: " + input);
                }
            } else {
                taskArr[freePointer] = new Task(input);
                freePointer++;
                System.out.println("added: " + input);
            }
            System.out.println("");
        }

        scanner.close();
    }
}
