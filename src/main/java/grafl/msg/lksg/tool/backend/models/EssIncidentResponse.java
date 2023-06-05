package grafl.msg.lksg.tool.backend.models;

import jakarta.persistence.*;
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
@Table(name = "ess_incident_responses", schema = "lksg_tool")
@IdClass(EssIncidentResponsePK.class)
public class EssIncidentResponse implements Serializable {

    @Id
    @Column(name = "request")
    private String request;

    @Id
    @Column(name = "job")
    private String job;

    @Column(name = "status")
    private String status;

    @Column(name = "impacted")
    private String impacted;

    @Column(name = "response")
    private String response;

}
