package task.system.utils;

public enum StateEnum {

    PROJECT                         ((byte)1, "Project"),
    IN_PROGRESS                         ((byte)2, "In progress"),
    COMPLETED                         ((byte)3, "Completed")
    ;

    private byte id;
    private String name;

    StateEnum(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public static StateEnum getByID(byte id) {
        for (StateEnum s : values()) {
            if (s.id == id) return s;
        }
        return null;
    }

    public byte getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
