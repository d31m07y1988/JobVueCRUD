package atb.dto;


public class tableDataDTO {

    private Object object;
    private int totalObjects;

    public tableDataDTO(Object object, int totalObjects) {
        this.object = object;
        this.totalObjects = totalObjects;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }
}
