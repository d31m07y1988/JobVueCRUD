package atb.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @SequenceGenerator(name = "job_seq", sequenceName = "job_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "persons_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "companies_id", nullable = false)
    private Company company;

    @Column(name = "date_start", nullable = false)
    private LocalDate date_start;

    @Column(name = "date_end", nullable = false)
    private LocalDate date_end;

    @Column(name = "manager", nullable = false)
    private boolean manager;

    @Column(name = "salary", nullable = false)
    private Double salary;

    public Job() {
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", person=" + person +
                ", company=" + company +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", manager=" + manager +
                ", salary=" + salary +
                '}';
    }

    public Job(Person person, Company company, LocalDate date_start, LocalDate date_end, boolean manager, Double salary) {
        this.person = person;
        this.company = company;
        this.date_start = date_start;
        this.date_end = date_end;
        this.manager = manager;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDate getDate_start() {
        return date_start;
    }

    public void setDate_start(LocalDate date_start) {
        this.date_start = date_start;
    }

    public LocalDate getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDate date_end) {
        this.date_end = date_end;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public boolean isNew() {
        return this.id==null || this.id==0;
    }
}
