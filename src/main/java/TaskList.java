import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> get() {
        return taskList;
    }

    public Task get(int index) throws ArnException {
        if (index < 0 || index >= taskList.size()) {
            throw new ArnException("Invalid task number");
        }
        return taskList.get(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int index) throws ArnException {
        if (index < 0 || index >= taskList.size()) {
            throw new ArnException("Invalid task number");
        }
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

}
