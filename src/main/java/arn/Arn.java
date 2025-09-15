package arn;
import java.util.ArrayList;

public class Arn {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.displayGreet();

        TaskFileHandler taskFileHandler = new TaskFileHandler("./src/main/java/arn/data/arn.txt");
        TaskList taskList = new TaskList(taskFileHandler.readTasks());
        Parser parser = new Parser(taskList, ui);
        String input = "";

        while(true) {
            input = ui.readCommand();
            try {
                parser.parse(input);
                if (input.equals("bye")) {
                    break;
                }
            } catch (ArnException e) {
                ui.displayMsg("Error: " + e.getMessage());
            }

            taskFileHandler.writeTasks(taskList.get());
            ui.displayMsg("");
        }

        ui.close();
    }
}
