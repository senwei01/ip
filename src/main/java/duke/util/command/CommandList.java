package duke.util.command;

import duke.task.TaskList;
import duke.util.SaveTasks;

public class CommandList extends Command {

    public CommandList(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, SaveTasks saveTasks) {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(" ").append(String.valueOf(i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return listString.toString();
    }
}
