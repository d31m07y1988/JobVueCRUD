package atb.repository;


import atb.model.Company;

import java.util.List;

public interface CompanyRepository {
    Company save(Company company);

    // null if not found
    Company get(int id);

    // null if not found
    Company getByInn(String inn);

    // null if not found
    List<Company> getByName(String companyName);

    List<Company> getAll();

    Integer totalCount();

    List<Company> getAllByPage(int offsetData, int limitData);
}
