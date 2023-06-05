package grafl.msg.lksg.tool.backend.repositories;

import grafl.msg.lksg.tool.backend.models.EssIncidentResponse;
import grafl.msg.lksg.tool.backend.models.EssIncidentResponsePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssIncidentResponseRepository extends JpaRepository<EssIncidentResponse, EssIncidentResponsePK> {
}
