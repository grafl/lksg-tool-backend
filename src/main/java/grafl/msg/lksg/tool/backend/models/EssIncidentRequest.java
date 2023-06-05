package grafl.msg.lksg.tool.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "ess_incident_requests", schema = "lksg_tool")
public class EssIncidentRequest implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "company")
    private String company;

    @Column(name = "bpn")
    private String bpn;

}
