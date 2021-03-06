package atb.service;

import atb.model.Job;

import java.util.List;

public interface JobService {
    Job save(Job job);
    // null if not found
    Job get(int id);

    // null if not found
    List<Job> getByPerson(Integer personId);

    List<Job> getByCompany(Integer companyId);

    List<Job> getAll();

    List<Job> getAllByPage(int page, int per_page);

    Integer totalCount();

    void delete(int id);

    void update(Job job);
}
