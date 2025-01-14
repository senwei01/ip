package duke.util;

import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StoredTasks {
    private static final int TASKTYPE = 0;
    private static final int ISTASKDONE = 1;
    private static final int TASKDESCRIPTION = 2;
    private static final int TASKDATETIME = 3;
    private ArrayList<Task> storedTasks = new ArrayList<>(100);

    private String fileDir;
    private String filePath;

    /**
     * A constructor for SaveTasks
     * @param fileDir the directory of the txt database file
     * @param filePath the path to the txt database file
     */
    public StoredTasks(String fileDir, String filePath) {
        this.fileDir = fileDir;
        this.filePath = filePath;
    }

    /**
     * A method that loads data straight from the hard disk from the start up.
     * @return the taskList in the database
     * @throws DukeException if the user input can't be read, throws an exception.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File dir = new File(this.fileDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File fileName = new File(this.filePath);
            if (!fileName.exists()) {
                fileName.createNewFile();
            }

            Scanner fileReader = new Scanner(fileName);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                readEntry(entry);
            }
            fileReader.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return storedTasks;
    }

    public void readEntry(String entry) {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        switch (fields[TASKTYPE]) {
            case "T":
                taskToAdd = new Todos(fields[TASKDESCRIPTION]);
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                storedTasks.add(taskToAdd);
                break;
            case "E":
                taskToAdd = new Event(fields[TASKDESCRIPTION], fields[TASKDATETIME]);
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                storedTasks.add(taskToAdd);
                break;
            case "D":
                taskToAdd = new Deadlines(fields[TASKDESCRIPTION], fields[TASKDATETIME],
                        DateAndTimeFormatter.validateAndParse(fields[TASKDATETIME]));
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                storedTasks.add(taskToAdd);
                break;
        }
    }

    public void save(TaskList savedTasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(savedTasks.taskListToString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
