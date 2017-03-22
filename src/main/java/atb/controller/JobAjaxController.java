package atb.controller;

import atb.dto.DataTableDTO;
import atb.dto.JobDTO;
import atb.model.Job;
import atb.service.CompanyService;
import atb.service.JobService;
import atb.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping(value = "/ajax/jobs")
public class JobAjaxController {
    private static final Logger LOG = LoggerFactory.getLogger(JobAjaxController.class);

    @Autowired
    private JobService jobService;

    @Autowired
    @Lazy
    private CompanyService companyService;

    @Autowired
    @Lazy
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DataTableDTO getAll(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "per_page", required = false) Integer per_page) {
        Integer total = jobService.totalCount();
        if(page==null && per_page==null) {
            page = 1;
            per_page=total;
        }
        return new DataTableDTO(jobService.getAllByPage(page,per_page),total, per_page, page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        jobService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOrCreate(@RequestBody JobDTO jobdto) {

        Job job = dtoToJob(jobdto);

        if (job.isNew()) {
            jobService.save(job);
        } else {
            jobService.update(job);
        }
    }

    private Job dtoToJob(JobDTO dto) {

        LOG.info("\t\t\t :::::Object job creat:::::");
        Job job = new Job();
        LOG.info("\t\t\t set Id:::::");
        job.setId(dto.getId());
        LOG.info("\t\t\t set Company:::::");
        job.setCompany(companyService.get(dto.getCompany()));
        LOG.info("\t\t\t set Person:::::");
        job.setPerson(personService.get(dto.getPerson()));
        LOG.info("\t\t\t set Manager:::::");
        job.setManager(dto.getManager().equals("false")?false:true);
        LOG.info("\t\t\t set Salary:::::");
        job.setSalary(dto.getSalary());

        LOG.info("\t\t\t set Date:::::");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate dateStart = LocalDate.parse(dto.getDate_start(),formatter);
        LocalDate dateEnd = LocalDate.parse(dto.getDate_start(),formatter);
        job.setDate_start(dateStart);
        job.setDate_end(dateEnd);
        return job;
    }

}