package grafl.msg.lksg.tool.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

@AllArgsConstructor
@Builder
@Data
@Entity
@Immutable
@NoArgsConstructor
@Table(name = "v_incidents", schema = "lksg_tool")
public class VInvestigation {

    @Id
    private String i;
    private String req_id;
    private String bpn;
    private String co_name;
    private String job_id;
    private String job_status;
    private String impacted;

}
