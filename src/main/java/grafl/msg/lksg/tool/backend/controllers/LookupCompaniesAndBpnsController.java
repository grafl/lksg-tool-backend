package grafl.msg.lksg.tool.backend.controllers;

import grafl.msg.lksg.tool.backend.models.VCompaniesAndBpns;
import grafl.msg.lksg.tool.backend.repositories.LookupCompaniesAndBpnsRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping(value = "/api/lksg_tool/lookup/companiesAndBpns", produces = { APPLICATION_JSON_VALUE })
@RestController
@Slf4j
@CrossOrigin
public class LookupCompaniesAndBpnsController {

    private final LookupCompaniesAndBpnsRepository lookupCompaniesAndBpnsRepository;

    public LookupCompaniesAndBpnsController(LookupCompaniesAndBpnsRepository lookupCompaniesAndBpnsRepository) {
        this.lookupCompaniesAndBpnsRepository = lookupCompaniesAndBpnsRepository;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<VCompaniesAndBpns>> getAllCompaniesAndBpns() {
        List<VCompaniesAndBpns> rv = this.lookupCompaniesAndBpnsRepository.findAll();
        return ok().body(rv);
    }

}
