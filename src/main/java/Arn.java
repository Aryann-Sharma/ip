import java.util.Scanner;
import java.util.ArrayList;

public class Arn {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Arn");
        System.out.println("What can I do for you?");
        System.out.print("\n");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String input = "";

        while(true) {
            System.out.print("-> ");
            input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    int index = 1;
                    for (Task task : taskList) {
                        if (task == null) {
                            break;
                        }
                        System.out.println(index + "." + task);
                        index++;
                    }
                } else if (input.startsWith("mark")) {
                    String[] parts = input.split(" ");
                    int i = Integer.parseInt(parts[1]) - 1;
                    if (i >= 0 && i < taskList.size()) {
                        taskList.get(i).markAsDone();
                        System.out.println("Task marked as done:");
                        System.out.println(taskList.get(i));
                    } else {
                        throw new ArnException("Invalid task number");
                    }
                } else if (input.startsWith("unmark")) {
                    String[] parts = input.split(" ");
                    int i = Integer.parseInt(parts[1]) - 1;
                    if (i >= 0 && i < taskList.size()) {
                        taskList.get(i).markAsNotDone();
                        System.out.println("Task marked as not done:");
                        System.out.println(taskList.get(i));
                    } else {
                        throw new ArnException("Invalid task number");
                    }
                } else if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new ArnException("Empty task description.");
                    }
                    int i = input.indexOf("todo ");
                    String description = input.substring(i + 5);
                    taskList.add(new Todo(description));
                    System.out.println("added: " + taskList.get(taskList.size() - 1));
                } else if (input.startsWith("deadline")) {
                    int i = input.indexOf("deadline ");
                    int j = input.indexOf("/by");
                    if (j == -1) {
                        throw new ArnException("Deadline task must have a '/by' clause.");
                    }
                    String description = input.substring(i + 9, j).trim();
                    if (description.isEmpty()) {
                        throw new ArnException("Empty task description.");
                    }
                    String date = input.substring(j + 1).trim();
                    if (date.isEmpty()) {
                        throw new ArnException("Empty date.");
                    }
                    taskList.add(new Deadline(description, date));
                    System.out.println("added: " + taskList.get(taskList.size() - 1));
                } else if (input.startsWith("event")) {
                    int i = input.indexOf("event ");
                    int j = input.indexOf("/from");
                    int k = input.indexOf("/to");
                    if (j == -1 || k == -1) {
                        throw new ArnException("Event task must have both '/from' and '/to' clauses.");
                    }
                    String description = input.substring(i + 6, j - 1).trim();
                    if (description.isEmpty()) {
                        throw new ArnException("Empty task description");
                    }
                    String startDate = input.substring(j + 1, k - 1).trim();
                    String endDate = input.substring(k + 1).trim();
                    if (startDate.isEmpty()) {
                        throw new ArnException("Empty start date.");
                    }
                    if (endDate.isEmpty()) {
                        throw new ArnException("Empty end date.");
                    }
                    String date = startDate + " " + endDate;
                    taskList.add(new Event(description, date));
                    System.out.println("added: " + taskList.get(taskList.size() - 1));
                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    int i = Integer.parseInt(parts[1]) - 1;
                    if (i >= 0 && i < taskList.size()) {
                        Task t = taskList.remove(i);
                        System.out.println("Task removed: " + t.toString());
                    } else {
                        throw new ArnException("Invalid task number");
                    }
                } else {
                    throw new ArnException("Sorry, not a valid command.");
                }
            } catch (ArnException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            System.out.println("");
        }

        scanner.close();
    }
}
