package atb.repository;

import atb.model.Company;
import atb.model.Job;
import atb.model.Person;

import java.util.List;

/**
 * Created by Ramil on 16.03.2017.
 */
public interface JobRepository {

    Job save(Job job);
    // null if not found
    Job get(int id);

    // null if not found
    List<Job> getByPerson(Person person);

    List<Job> getByCompany(Company company);

    List<Job> getAll();
}
