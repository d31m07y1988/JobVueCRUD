package atb.controller;

import atb.dto.DataTableDTO;
import atb.dto.Pagination;
import atb.model.Person;
import atb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        Pagination pagination = new Pagination(total, per_page, page);
        return new DataTableDTO(pagination, personService.getAllByPage(page,per_page));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        personService.delete(id);
    }
}