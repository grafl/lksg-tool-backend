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
@Table(name = "v_bpns", schema = "lksg_tool")
public class VBpn {

    @Id
    private String bpn;
    private String company_name;

}
