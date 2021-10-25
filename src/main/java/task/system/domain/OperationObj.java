package task.system.domain;

import java.sql.Date;

public class OperationObj {

    private int idTaskOperation;
    private int idTask;
    private byte idState;
    private String description;
    private int plannedQuantity;
    private int actualQuantity;
    private int price;
    private int totalCost;
    private Date creationDate;
    private Date completionDate;

    public OperationObj(int idTaskOperation, int idTask, byte idState, String description, int plannedQuantity, int actualQuantity,
                        int price, int totalCost, Date creationDate, Date completionDate) {
        this.idTaskOperation = idTaskOperation;
        this.idTask = idTask;
        this.idState = idState;
        this.description = description;
        this.plannedQuantity = plannedQuantity;
        this.actualQuantity = actualQuantity;
        this.price = price;
        this.totalCost = totalCost;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
    }

    public OperationObj( int idTask, byte idState, String description, int plannedQuantity,
                        int price, int totalCost, Date creationDate) {
        this.idTask = idTask;
        this.idState = idState;
        this.description = description;
        this.plannedQuantity = plannedQuantity;
        this.price = price;
        this.totalCost = totalCost;
        this.creationDate = creationDate;
    }

    public OperationObj(int idTaskOperation, byte idState, int actualQuantity, int totalCost, Date completionDate) {
        this.idTaskOperation = idTaskOperation;
        this.idState = idState;
        this.actualQuantity = actualQuantity;
        this.totalCost = totalCost;
        this.completionDate = completionDate;
    }

    public OperationObj(int idTaskOperation, byte idState, int totalCost) {
        this.idTaskOperation = idTaskOperation;
        this.idState = idState;
        this.totalCost = totalCost;
    }

    public int getIdTaskOperation() {
        return idTaskOperation;
    }

    public void setIdTaskOperation(int idTaskOperation) {
        this.idTaskOperation = idTaskOperation;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public byte getIdState() {
        return idState;
    }

    public void setIdState(byte idState) {
        this.idState = idState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlannedQuantity() {
        return plannedQuantity;
    }

    public void setPlannedQuantity(int plannedQuantity) {
        this.plannedQuantity = plannedQuantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
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
