package atb.service;

import atb.model.Company;
import atb.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository repository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Company save(Company company) {
        return repository.save(company);
    }

    @Override
    public Company get(int id) {
        return repository.get(id);
    }

    @Override
    public Company getByInn(String inn) {
        return repository.getByInn(inn.trim());
    }

    @Override
    public List<Company> getByName(String companyName) {
        return repository.getByName(companyName.trim());
    }

    @Override
    public List<Company> getAll() {
        return repository.getAll();
    }
}
