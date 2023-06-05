package grafl.msg.lksg.tool.backend.services;

import grafl.msg.lksg.tool.backend.clients.IrsFeignClient;
import grafl.msg.lksg.tool.backend.clients.models.IrsRegistrationRequest;
import grafl.msg.lksg.tool.backend.models.*;
import grafl.msg.lksg.tool.backend.repositories.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvestigationService {

    private final CompanyRepository companyRepository;
    private final BpnRepository bpnRepository;
    private final CompanyBpnsRepository companyBpnsRepository;
    private final EssIncidentRequestRepository essIncidentRequestRepository;
    private final IrsFeignClient irsFeignClient;
    private final EssIncidentResponseRepository essIncidentResponseRepository;

    public InvestigationService(CompanyRepository companyRepository,
                                BpnRepository bpnRepository,
                                CompanyBpnsRepository companyBpnsRepository,
                                EssIncidentRequestRepository essIncidentRequestRepository,
                                IrsFeignClient irsFeignClient,
                                EssIncidentResponseRepository essIncidentResponseRepository) {
        this.companyRepository = companyRepository;
        this.bpnRepository = bpnRepository;
        this.companyBpnsRepository = companyBpnsRepository;
        this.essIncidentRequestRepository = essIncidentRequestRepository;
        this.irsFeignClient = irsFeignClient;
        this.essIncidentResponseRepository = essIncidentResponseRepository;
    }

    public List<EssIncidentRequest> getAllInvestigations() {
        return this.essIncidentRequestRepository.findAll();
    }

    public void createNewInvestigations(List<Investigation> investigations) {
        for(Investigation investigation : investigations) {
            Optional<Company> company = this.companyRepository.findById(investigation.getCompany());
            if(company.isEmpty()) {
                log.info("Storing new company '{}' ...", investigation.getCompany());
                this.companyRepository.save(Company.builder()
                        .id(investigation.getCompany())
                        .name("CO_" + investigation.getCompany())
                        .build());
            }
            Optional<Bpn> bpn = this.bpnRepository.findById(investigation.getBpn());
            if(bpn.isEmpty()) {
                log.info("Storing new BPN '{}' ...", investigation.getBpn());
                this.bpnRepository.save(Bpn.builder().bpn(investigation.getBpn()).build());
            }
            CompanyBpns companyBpns = CompanyBpns.builder()
                    .company(investigation.getCompany())
                    .bpn(investigation.getBpn())
                    .build();
            this.companyBpnsRepository.save(companyBpns);
            this.essIncidentRequestRepository.save(
                    EssIncidentRequest.builder()
                            .id(investigation.getId())
                            .company(investigation.getCompany())
                            .bpn(investigation.getBpn())
                            .build()
            );
            IrsRegistrationRequest irsRegistrationRequest =
                    IrsRegistrationRequest.builder()
                            .bomLifecycle("asBuilt")
                            .callbackUrl("https://hostname.com/callback?jobId={jobId}&jobState={jobState}")
                            .globalAssetId(investigation.getId())
                            .incidentBpns(List.of(investigation.getBpn()))
                            .build();
            log.info("Sending to IRS {}", irsRegistrationRequest);
            String investigationJobId = this.irsFeignClient.registerIrsJob(irsRegistrationRequest);
            if(investigationJobId != null) {
                EssIncidentResponse essIncidentResponse = EssIncidentResponse.builder()
                        .request(investigation.getId())
                        .job(investigationJobId)
                        .status("INITIAL")
                        .response("")
                        .build();
                this.essIncidentResponseRepository.save(essIncidentResponse);
            }
        }
    }

}
