package grafl.msg.lksg.tool.backend.controllers;

import grafl.msg.lksg.tool.backend.models.Company;
import grafl.msg.lksg.tool.backend.services.CompanyService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping(value = "/api/lksg_tool/companies", produces = { APPLICATION_JSON_VALUE })
@RestController
@Slf4j
@CrossOrigin
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> rv = this.companyService.getAllCompanies();
        return ok().body(rv);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Company>> getCompanyById(@PathVariable("id") String id) {
        return ok().body(this.companyService.getBpnById(id));
    }

    @PostMapping(value = "/")
    public List<String> createCompanies(@RequestBody List<Company> companies) {
        List<String> bpnIds = new ArrayList<>();
        for(Company company : companies) {
            bpnIds.add(this.companyService.storeBpn(company));
        }
        return bpnIds;
    }

    @PutMapping(value = "/{id}/{bpn}")
    public void assignBpnToCompanyById(@PathVariable("id") String id,
                                       @PathVariable("bpn") String bpn) {
        this.companyService.assignBpnToCompanyById(id, bpn);
    }

}
