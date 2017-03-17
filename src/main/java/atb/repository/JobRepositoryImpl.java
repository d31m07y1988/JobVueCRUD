package atb.repository;

import atb.model.Company;
import atb.model.Job;
import atb.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository("jobRepository")
@Transactional
public class JobRepositoryImpl implements JobRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public JobRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Job save(Job job) {
        Session session = sessionFactory.getCurrentSession();
        Serializable saved = session.save(job);
        return get((int)saved);
    }

    @Override
    public Job get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Job.class,id);
    }

    @Override
    public List<Job> getByPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Job>) session.createQuery(
                "select j from Job j where j.persons_id = :id")
                .setParameter("id", person.getId()).getResultList();
    }

    @Override
    public List<Job> getByCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Job>) session.createQuery(
                "select j from Job j where j.companies_id = :id")
                .setParameter("id", company.getId()).getResultList();
    }

    @Override
    public List<Job> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Job", Job.class).list();
    }
}
