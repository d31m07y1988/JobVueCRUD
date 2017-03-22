package atb.service;

import atb.model.Company;
import atb.model.Job;
import atb.model.Person;
import atb.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {


    private JobRepository repository;

    @Autowired
    @Lazy
    private PersonService personService;

    @Autowired
    @Lazy
    private CompanyService companyService;

    @Autowired
    public JobServiceImpl(JobRepository repository) {
        this.repository = repository;
    }

    @Override
    public Job save(Job job) {
        job.setId(null);
        return repository.save(job);
    }

    @Override
    public Job get(int id) {
        return null;
    }

    @Override
    public List<Job> getByPerson(Integer personId) {
        return repository.getByPerson(personService.get(personId));
    }

    @Override
    public List<Job> getByCompany(Integer companyId) {
        return repository.getByCompany(companyService.get(companyId));
    }

    @Override
    public List<Job> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Job> getAllByPage(int page, int per_page) {
        return repository.getAllByPage((page-1)*per_page, per_page);
    }

    @Override
    public Integer totalCount() {
        return repository.totalCount();
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(Job job) {
        repository.update(job);
    }
}
