package atb.repository;

import atb.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository("companyRepository")
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CompanyRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Company save(Company company) {
        Session session = sessionFactory.getCurrentSession();
        Serializable saved = session.save(company);
        System.out.println("-----\n\n save servise -----\n\n");

        return get((int)saved);
    }

    @Override
    public Company get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Company.class,id);
    }

    @Override
    public Company getByInn(String inn) {
        Session session = sessionFactory.getCurrentSession();
        return (Company) session.createQuery(
                "select c from Company c where c.inn = :inn" )
                .setParameter( "inn", inn)
                .uniqueResult();
    }

    @Override
    public List<Company> getByName(String companyName) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Company>) session.createQuery(
                "select c from Company c where LOWER(c.name) like :companyName")
                .setParameter("companyName", "%"+companyName.toLowerCase()+"%").getResultList();
    }

    @Override
    public List<Company> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Company", Company.class).list();
    }

    @Override
    public Integer totalCount() {
        Session session = sessionFactory.getCurrentSession();
        return ((Long)session.createQuery("select count(*) from Company").uniqueResult()).intValue();
    }

    @Override
    public List<Company> getAllByPage(int offsetData, int limitData) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Company", Company.class).setFirstResult(offsetData).setMaxResults(limitData).getResultList();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(get(id));
    }

    @Override
    public void update(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.update(company);
    }
}
