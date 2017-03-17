package atb.controller;

import atb.dto.tableDataDTO;
import atb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/ajax/companies")
public class CompaniesAjaxController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public tableDataDTO getAll(@RequestParam(value = "page", required = true) int page,
                               @RequestParam(value = "per_page", required = true) int perPage) {
        return new tableDataDTO(companyService.getAllByPage(page,perPage),companyService.totalCount());
    }

}