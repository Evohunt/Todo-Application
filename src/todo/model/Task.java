package todo.model;

public class Task {

    private String task;
    private long datecreated;
    private String description;

    public Task() {
    }

    public Task(String task, long datecreated, String description) {
        this.task = task;
        this.datecreated = datecreated;
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public long getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(long datecreated) {
        this.datecreated = datecreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
