package grafl.msg.lksg.tool.backend.repositories;

import grafl.msg.lksg.tool.backend.models.EssIncidentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssIncidentRequestRepository extends JpaRepository<EssIncidentRequest, String> {
}
