package atb.dto;


public class DataTableDTO {

    private Object data;
    private Pagination pagination;

    public DataTableDTO(Pagination pagination, Object data) {
        this.pagination = pagination;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
