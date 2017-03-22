package atb.dto;

import atb.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownPersonDTO {

    Boolean success;
    List<DropDownPerson> results;

    public DropdownPersonDTO(List<Person> personList) {
        this.success = personList.isEmpty()?false:true;
        this.results = dropdownCompanylist(personList);
    }

    private List<DropDownPerson> dropdownCompanylist(List<Person> companyList) {
        return companyList.stream().map(DropDownPerson::new).collect(Collectors.toList());
    }

    public Boolean getSuccess() {
        return success;
    }

    public List<DropDownPerson> getResults() {
        return results;
    }

    private class DropDownPerson {
        private String name;
        private Integer value;
        private String text;

        public DropDownPerson(Person person) {
            this.name = person.getFullname()+" ("+person.getEmail()+")";
            this.value = person.getId();
            this.text = person.getFullname();
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
