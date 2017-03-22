package atb.repository;

import atb.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository("personRepository")
@Transactional
public class PersonRepositoryImpl implements PersonRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public PersonRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Person save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        Serializable saved = session.save(person);
        return get((int)saved);
    }

    @Override
    public Person get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    @Override
    public Person getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return (Person) session.createQuery(
                "select c from Person c where c.email = :email" )
                .setParameter( "email", email)
                .uniqueResult();
    }

    @Override
    public List<Person> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Person>) session.createQuery(
                "select c from Person c where LOWER(c.fullname) like :name")
                .setParameter("name", "%"+name.toLowerCase()+"%").getResultList();
    }

    @Override
    public List<Person> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person", Person.class).list();
    }

    @Override
    public Integer totalCount() {
        Session session = sessionFactory.getCurrentSession();
        return ((Long)session.createQuery("select count(*) from Person").uniqueResult()).intValue();
    }

    @Override
    public List<Person> getAllByPage(int offsetData, int limitData) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Person", Person.class).setFirstResult(offsetData).setMaxResults(limitData).getResultList();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(get(id));
    }

    @Override
    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }
}
