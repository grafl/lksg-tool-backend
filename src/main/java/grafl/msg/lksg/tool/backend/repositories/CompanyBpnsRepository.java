package grafl.msg.lksg.tool.backend.repositories;

import grafl.msg.lksg.tool.backend.models.CompanyBpns;
import grafl.msg.lksg.tool.backend.models.CompanyBpnsPK;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyBpnsRepository extends JpaRepository<CompanyBpns, CompanyBpnsPK> {

    public Optional<List<CompanyBpns>> findByCompany(String company);
    public Optional<List<CompanyBpns>> findByBpn(String bpn);

}
