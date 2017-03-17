package atb.service;


import atb.model.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    // null if not found
    Person get(int id);

    // null if not found
    Person getByEmail(String email);

    // null if not found
    List<Person> getByName(String name);

    List<Person> getAll();
}
