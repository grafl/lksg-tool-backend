package grafl.msg.lksg.tool.backend.controllers;

import grafl.msg.lksg.tool.backend.clients.IrsFeignClient;
import grafl.msg.lksg.tool.backend.clients.models.IrsRegistrationRequest;
import grafl.msg.lksg.tool.backend.models.EssIncidentRequest;
import grafl.msg.lksg.tool.backend.models.EssIncidentResponse;
import grafl.msg.lksg.tool.backend.models.Investigation;
import grafl.msg.lksg.tool.backend.models.VInvestigation;
import grafl.msg.lksg.tool.backend.repositories.CompanyBpnsRepository;
import grafl.msg.lksg.tool.backend.repositories.EssIncidentRequestRepository;
import grafl.msg.lksg.tool.backend.repositories.EssIncidentResponseRepository;
import grafl.msg.lksg.tool.backend.repositories.VInvestigationRepository;
import grafl.msg.lksg.tool.backend.services.InvestigationService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tractusx.irs.component.Jobs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping(value = "/api/lksg_tool/investigations", produces = { APPLICATION_JSON_VALUE })
@RestController
@Slf4j
@CrossOrigin
public class InvestigationController {

    private static final String SUPPLY_CHAIN_ASPECT_TYPE = "supply_chain_impacted";

    private final InvestigationService investigationService;
    private final VInvestigationRepository vInvestigationRepository;
    private final IrsFeignClient irsFeignClient;

    private final EssIncidentResponseRepository essIncidentResponseRepository;
    private final EssIncidentRequestRepository essIncidentRequestRepository;

    public InvestigationController(InvestigationService investigationService,
                                   VInvestigationRepository vInvestigationRepository, IrsFeignClient irsFeignClient, EssIncidentResponseRepository essIncidentResponseRepository, EssIncidentRequestRepository essIncidentRequestRepository, CompanyBpnsRepository companyBpnsRepository) {
        this.investigationService = investigationService;
        this.vInvestigationRepository = vInvestigationRepository;
        this.irsFeignClient = irsFeignClient;
        this.essIncidentResponseRepository = essIncidentResponseRepository;
        this.essIncidentRequestRepository = essIncidentRequestRepository;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<EssIncidentRequest>> getAllInvestigations() {
        List<EssIncidentRequest> rv = this.investigationService.getAllInvestigations();
        log.info("getAllInvestigations() -----> {} rows", rv.size());
        return ok().body(rv);
    }

    @GetMapping(value = "/v/")
    public ResponseEntity<List<VInvestigation>> getAllVInvestigations() {
        List<EssIncidentResponse> allResponses = this.essIncidentResponseRepository.findAll();
        for(EssIncidentResponse response : allResponses) {
            Jobs jobs = this.irsFeignClient.getIrsJobId(response.getJob());
            response.setStatus(jobs.getJob().getState().name());
            if(jobs != null && jobs.getSubmodels() != null && jobs.getSubmodels().size() > 0) {
                response.setImpacted((String) jobs.getSubmodels().get(0).getPayload().get(SUPPLY_CHAIN_ASPECT_TYPE));
            }
            this.essIncidentResponseRepository.save(response);
        }
        List<VInvestigation> rv = this.vInvestigationRepository.findAll();
        log.info("getAllVInvestigations() -----> {} rows", rv.size());
        return ok().body(rv);
    }

    @PostMapping(value = "/")
    public void createNewInvestigations(@RequestBody List<Investigation> investigationRequests) {
        // ResponseEntity<List<Investigation>>
        log.info("createNewInvestigations() {}", investigationRequests);
        this.investigationService.createNewInvestigations(investigationRequests);
    }

    @GetMapping(value = "/irs/{job_id}")
    public ResponseEntity<Jobs> getSupplyChainImpactedInformation(@PathVariable("job_id") String job_id) {
        Jobs jobs = this.irsFeignClient.getIrsJobId(job_id);
        // log.info("getSupplyChainImpactedInformation({}: {})", job_id, jobs.getJob().getState());
        return ok().body(jobs);
    }

    @GetMapping(value = "/irs/{job_id}/{state}/{impacted}")
    public ResponseEntity<Jobs> setAnalysisState(@PathVariable("job_id") String job_id,
                                            @PathVariable("state") String state,
                                            @PathVariable("impacted") String impacted) {
        Jobs jobs = this.irsFeignClient.setAnalysisState(job_id, state, impacted, "SingleLevelUsageAsBuilt");
        log.info("request IRS set analysis state({}: {} - {})", job_id, state, impacted);
        return ok().body(jobs);
    }

    @DeleteMapping(value = "/")
    public void deleteEverything() {
        this.essIncidentResponseRepository.deleteAll();
        this.essIncidentRequestRepository.deleteAll();
        log.info("Everything has been deleted!");
    }

}
