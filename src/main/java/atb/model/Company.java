package atb.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @SequenceGenerator(name = "companies_seq", sequenceName = "companies_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @Column(name = "inn", nullable = false, unique = true)
    @NotEmpty
    @Pattern(regexp = "^([0-9]{10}|[0-9]{12})$",
            message = "ИНН должен состоять из 10 цифр для юр.лиц и 12 для ИП")
    private String inn;

    public Company() {
    }

    public Company(String name, String inn) {
        this.name = name;
        this.inn = inn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isNew() {
        return this.id==null || this.id==0;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inn='" + inn + '\'' +
                '}';
    }
}
