package atb.controller;

import atb.dto.Pagination;
import atb.dto.DataTableDTO;
import atb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/ajax/companies")
public class CompaniesAjaxController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DataTableDTO getAll(@RequestParam(value = "page", required = false) int page,
                               @RequestParam(value = "per_page", required = false) int per_page) {
        Integer total = companyService.totalCount();
        Pagination pagination = new Pagination(total, per_page, page);
        return new DataTableDTO(pagination, companyService.getAllByPage(page,per_page));
    }

}
