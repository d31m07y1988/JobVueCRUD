package atb.controller;

import atb.dto.DataTableDTO;
import atb.dto.DropdownCompanyDTO;
import atb.model.Company;
import atb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/ajax/companies")
public class CompaniesAjaxController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DataTableDTO getAll(@RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "per_page", required = false) Integer per_page) {
        Integer total = companyService.totalCount();
        if(page==null && per_page==null) {
            page = 1;
            per_page=total;
        }
        return new DataTableDTO(companyService.getAllByPage(page,per_page),total, per_page, page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        companyService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOrCreate(@Valid @RequestBody Company company) {

        if (company.isNew()) {
            companyService.save(company);
        } else {
            companyService.update(company);
        }
    }

    @RequestMapping(value = "/dropdown", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DropdownCompanyDTO getDropdown() {
       return new DropdownCompanyDTO(companyService.getAll());
    }
}
