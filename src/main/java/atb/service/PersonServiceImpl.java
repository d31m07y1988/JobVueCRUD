package atb.service;

import atb.model.Person;
import atb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personsService")
public class PersonServiceImpl implements PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Person get(int id) {
        return repository.get(id);
    }

    @Override
    public Person getByEmail(String email) {
        return repository.getByEmail(email.trim());
    }

    @Override
    public List<Person> getByName(String name) {
        return repository.getByName(name.trim());
    }

    @Override
    public List<Person> getAll() {
        return repository.getAll();
    }
}
