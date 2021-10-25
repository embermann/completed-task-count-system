package task.system.domain;

import java.sql.Date;

public class TaskObj {

    private int idTask;
    private String description;
    private int price;
    private byte idState;
    private Date creationDate;
    private Date completionDate;

    public TaskObj(int idTask, String description, int price, byte idState, Date creationDate) {
        this.idTask = idTask;
        this.description = description;
        this.price = price;
        this.idState = idState;
        this.creationDate = creationDate;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte getIdState() {
        return idState;
    }

    public void setIdState(byte idState) {
        this.idState = idState;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}
