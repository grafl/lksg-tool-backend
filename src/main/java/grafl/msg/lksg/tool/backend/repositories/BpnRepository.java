package grafl.msg.lksg.tool.backend.repositories;

import grafl.msg.lksg.tool.backend.models.Bpn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BpnRepository extends JpaRepository<Bpn, String> {
}
