package atb.model;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @SequenceGenerator(name = "companies_seq", sequenceName = "companies_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_seq")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "inn", nullable = false, unique = true)
    private String inn;

    public Company() {
    }

    public Company(String name, String inn) {
        this.name = name;
        this.inn = inn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
