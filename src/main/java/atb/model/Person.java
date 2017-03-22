package atb.model;

import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @SequenceGenerator(name = "persons_seq", sequenceName = "persons_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_seq")
    private Integer id;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "phone", nullable = false)
    @Pattern(regexp = "^((8|0|((\\+|00)\\d{1,2}))[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "телефон введен не корректно")
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    public Person() {
    }

    public Person(String fullname, String phone, String email) {
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }

    public boolean isNew() {
        return this.id==null || this.id==0;
    }
}
