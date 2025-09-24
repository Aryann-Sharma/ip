package arn;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main entry point for Arn application
 * <p>
 * Initializes the user interface, loads tasks from
 * storage, and processes user commands until termination.
 */
public class Arn extends Application {
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

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
