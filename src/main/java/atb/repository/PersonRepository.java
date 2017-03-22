package atb.repository;

import atb.model.Person;

import java.util.List;

public interface PersonRepository {

    Person save(Person person);

    // null if not found
    Person get(int id);

    // null if not found
    Person getByEmail(String email);

    // null if not found
    List<Person> getByName(String companyName);

    List<Person> getAll();

    Integer totalCount();

    List<Person> getAllByPage(int offsetData, int limitData);

    void delete(int id);

    void update(Person person);
}
