package grafl.msg.lksg.tool.backend.services;

import grafl.msg.lksg.tool.backend.models.Company;
import grafl.msg.lksg.tool.backend.models.CompanyBpns;
import grafl.msg.lksg.tool.backend.repositories.CompanyBpnsRepository;
import grafl.msg.lksg.tool.backend.repositories.CompanyRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyBpnsRepository companyBpnsRepository;

    public CompanyService(CompanyRepository bpnRepository,
                          CompanyBpnsRepository companyBpnsRepository) {
        this.companyRepository = bpnRepository;
        this.companyBpnsRepository = companyBpnsRepository;
    }

    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    public Optional<Company> getBpnById(String id) {
        return this.companyRepository.findById(id);
    }

    public String storeBpn(Company company) {
        this.companyRepository.save(company);
        return company.getId();
    }

    public void assignBpnToCompanyById(String id, String bpn) {
        this.companyBpnsRepository.save(
                CompanyBpns.builder().company(id).bpn(bpn).build());
    }
}
