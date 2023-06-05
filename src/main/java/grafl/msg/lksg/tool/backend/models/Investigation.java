package grafl.msg.lksg.tool.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Investigation implements Serializable {

    @Id
    private String id;
    private String company;
    private String bpn;

}
