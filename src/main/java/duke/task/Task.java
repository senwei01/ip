package duke.task;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Task) {
            Task task = (Task) obj;
            return this.description.equals(task.description);
        }

        return false;
    }

    public abstract String savedTaskString();
    @Override
    public String toString() {
        String str = "[" + this.getStatusIcon() + "] " + this.getDescription();
        return str;

    }
}
