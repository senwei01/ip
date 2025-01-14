package duke.task;

import duke.exception.DukeDuplicateTaskException;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
/**
 * A class that contains the task list
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * This method removes a task from the taskList
     * @param i the index of the task to be removed.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    /**
     * This method returns the number of tasks in the taskList
     * @return the number of tasks in the taskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * This method adds a task into the taskList only if the task has not been added before
     * @param task the task to be added.
     */
    public void add(Task task) throws DukeDuplicateTaskException {
        for (Task t : this.tasks) {
            if (task.equals(t)) {
                throw new DukeDuplicateTaskException();
            }
        }
        this.tasks.add(task);
    }

    /**
     * This method filters task based on the keyword
     * @param keyword the keyword to filter against
     * @return a list of task that contains the keyword
     */
    public TaskList filter(String keyword) {
        List<Task> task = this.tasks.stream()
                .filter(x -> x.description.contains(keyword))
                .collect(Collectors.toList());

        return new TaskList(new ArrayList<Task>(task));
    }
    /**
     * This method converts tasks in the taskList to a String that can be later written into a file
     * @return tasks in String.
     */
    public String taskListToString() {
        StringBuilder saveString = new StringBuilder();
        for (Task task : this.tasks) {
            saveString.append(task.savedTaskString()).append("\n");
        }
        return saveString.toString();
    }


}
