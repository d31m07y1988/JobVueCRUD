package atb.controller;

import atb.dto.DataTableDTO;
import atb.dto.Pagination;
import atb.model.Job;
import atb.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/ajax/jobs")
public class JobAjaxController {

    @Autowired
    private JobService jobService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DataTableDTO getAll(@RequestParam(value = "page", required = false) int page,
                               @RequestParam(value = "per_page", required = false) int per_page) {
        Integer total = jobService.totalCount();
        Pagination pagination = new Pagination(total, per_page, page);
        return new DataTableDTO(pagination, jobService.getAllByPage(page,per_page));
    }

}