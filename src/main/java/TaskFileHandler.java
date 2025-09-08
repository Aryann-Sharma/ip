import java.io.*;
import java.util.ArrayList;
public class TaskFileHandler {
    protected String filePath;
    public TaskFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> readTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("(New save file created called arn.txt inside ./data/)");
                System.out.println("");
                return taskList;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
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
}
