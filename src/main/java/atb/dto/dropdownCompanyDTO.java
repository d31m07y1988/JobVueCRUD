package atb.dto;

import atb.model.Company;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownCompanyDTO {

    Boolean success;
    List<DropDownCompany> results;

    public DropdownCompanyDTO(List<Company> companyList) {
        this.success = companyList.isEmpty()?false:true;
        this.results = dropdownCompanylist(companyList);
    }

    private List<DropDownCompany> dropdownCompanylist(List<Company> companyList) {
        return companyList.stream().map(DropDownCompany::new).collect(Collectors.toList());
    }

    public Boolean getSuccess() {
        return success;
    }

    public List<DropDownCompany> getResults() {
        return results;
    }

    private class DropDownCompany {
        private String name;
        private Integer value;
        private String text;

        public DropDownCompany(Company company) {
            this.name = company.getName()+" (инн:"+company.getInn()+")";
            this.value = company.getId();
            this.text = company.getName();
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }

        public String getText() {
            return text;
        }
    }
}
