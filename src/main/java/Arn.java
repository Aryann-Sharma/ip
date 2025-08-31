import java.util.Scanner;

public class Arn {
    public static String taskType(Task task) {
        String type = "";
        if (task instanceof Todo) {
            type = ((Todo) task).getType();
        } else if (task instanceof Deadline) {
            type = ((Deadline) task).getType();
        } else if (task instanceof Event) {
            type = ((Event) task).getType();
        }
        return type;
    }

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
                    System.out.println(index + "." + task);
                    index++;
                }
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                int i = Integer.parseInt(parts[1]) - 1;
                if (taskArr[i] != null) {
                    taskArr[i].markAsDone();
                    System.out.println("Task marked as done:");
                    System.out.println(taskArr[i]);
                }
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                int i = Integer.parseInt(parts[1]) - 1;
                if (taskArr[i] != null) {
                    taskArr[i].markAsNotDone();
                    System.out.println("Task marked as not done:");
                    System.out.println(taskArr[i]);
                }
            } else if (input.startsWith("todo")) {
                int i = input.indexOf("todo ");
                String description = input.substring(i + 5);
                taskArr[freePointer] = new Todo(description);
                System.out.println("added: " + taskArr[freePointer]);
                freePointer++;
            } else if (input.startsWith("deadline")) {
                int i = input.indexOf("deadline ");
                int j = input.indexOf("/");
                String description = input.substring(i + 9, j - 1);
                String date = input.substring(j + 1);
                taskArr[freePointer] = new Deadline(description, date);
                System.out.println("added: " + taskArr[freePointer]);
                freePointer++;
            } else if (input.startsWith("event")) {
                int i = input.indexOf("event ");
                int j = input.indexOf("/from");
                int k = input.indexOf("/to");
                String description = input.substring(i + 6, j - 1);
                String startDate = input.substring(j + 1, k - 1);
                String endDate = input.substring(k + 1);
                String date = startDate + " " + endDate;
                taskArr[freePointer] = new Event(description, date);
                System.out.println("added: " + taskArr[freePointer]);
                freePointer++;
            }

            System.out.println("");
        }

        scanner.close();
    }
}
