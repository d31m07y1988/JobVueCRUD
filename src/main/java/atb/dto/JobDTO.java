package atb.dto;

public class JobDTO {
    private Integer id;
    private Integer company;
    private Integer person;
    private String date_start;
    private String date_end;
    private String manager;
    private Double salary;

    public JobDTO() {
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "id=" + id +
                ", company=" + company +
                ", person=" + person +
                ", date_start='" + date_start + '\'' +
                ", date_end='" + date_end + '\'' +
                ", manager='" + manager + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
