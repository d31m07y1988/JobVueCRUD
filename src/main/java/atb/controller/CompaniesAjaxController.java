package atb.controller;

import atb.dto.Pagination;
import atb.dto.DataTableDTO;
import atb.model.Company;
import atb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        Pagination pagination = new Pagination(total, per_page, page);
        return new DataTableDTO(pagination, companyService.getAllByPage(page,per_page));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        companyService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOrCreate(@Valid @RequestBody Company company) {

        System.out.println("-----------------\n\n\n\n");
        System.out.println(company);
        System.out.println("\n\n\n\n-----------------");
        if (company.isNew()) {
            System.out.println("-----\n\n wanna save -----\n\n");
            companyService.save(company);
        }/* else {
            companyService.update(company, company.getId());
        }*/
    }
}
