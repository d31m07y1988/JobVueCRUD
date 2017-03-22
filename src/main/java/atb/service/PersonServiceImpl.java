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
        person.setId(null);
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

    @Override
    public Integer totalCount() {
        return repository.totalCount();
    }

    @Override
    public List<Person> getAllByPage(int page, int per_page) {
        return repository.getAllByPage((page-1)*per_page, per_page);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(Person person) {
        repository.update(person);
    }
}
