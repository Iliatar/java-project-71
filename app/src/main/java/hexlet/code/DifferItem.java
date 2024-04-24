package hexlet.code;

public class DifferItem {
    public static final String DELETED = "deleted";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private String statusName;
    private Object oldValue;
    private Object newValue;

    /*DifferItem(String statusname, Object oldvalue, Object newvalue) {
        this.statusName = statusname;
        this.oldValue = oldvalue;
        this.newValue = newvalue;
    }*/

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public String getStatusName() {
        return statusName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
