import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskFileHandler {
    protected String filePath;
    public TaskFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> readTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader br = null;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("(New save file created called arn.txt inside ./data/)");
                System.out.println("");
                return taskList;
            }

            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                try {
                    Task task = parseTask(line);
                    if (task != null) {
                        taskList.add(task);
                    }
                } catch (Exception e) {
                    System.out.println("Skipped a corrupted line");
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error in reading saved tasks: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage());
                }
            }
        }

        return taskList;
    }

    public Task parseTask(String line) {
        Task task = null;
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        if (taskType.equals("T")) {
            task = new Todo(parts[2].trim());
        } else if (taskType.equals("D")) {
            task = new Deadline(parts[2].trim(), parts[3].trim());
        } else if (taskType.equals("E")) {
            task = new Event(parts[2].trim(), "from " + parts[3].trim() + " to " + parts[4].trim());
        }

        if (parts[1].trim().equals("1")) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }

        return task;
    }

    public void writeTasks(ArrayList<Task> taskList) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task: taskList) {
                String line = "";
                if (task instanceof Todo) {
                    line = "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
                } else if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    line = "D | " + (task.isDone ? "1" : "0") + " | " + d.description + " | " + d.date;
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    line = "E | " + (task.isDone ? "1" : "0") + " | " + e.description + " | ";
                    String date = e.date;
                    String startDate = date.substring(5, date.indexOf(" to "));
                    String endDate = date.substring(date.indexOf(" to ") + 4);
                    line = line + startDate + " | " + endDate;
                }

                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error in writing tasks: " + e.getMessage());
        }
    }
}
