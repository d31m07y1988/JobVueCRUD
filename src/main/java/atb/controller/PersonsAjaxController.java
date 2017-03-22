package atb.controller;

import atb.dto.DataTableDTO;
import atb.dto.DropdownPersonDTO;
import atb.model.Person;
import atb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/ajax/persons")
public class PersonsAjaxController {

    @Autowired
    private PersonService personService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DataTableDTO getAll(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "per_page", required = false) Integer per_page) {
        Integer total = personService.totalCount();
        if(page==null && per_page==null) {
            page = 1;
            per_page=total;
        }
        return new DataTableDTO(personService.getAllByPage(page,per_page),total, per_page, page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        personService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOrCreate(@Valid @RequestBody Person person) {

        if (person.isNew()) {
            personService.save(person);
        } else {
            personService.update(person);
        }
    }

    @RequestMapping(value = "/dropdown", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DropdownPersonDTO getDropdown() {
        return new DropdownPersonDTO(personService.getAll());
    }
}