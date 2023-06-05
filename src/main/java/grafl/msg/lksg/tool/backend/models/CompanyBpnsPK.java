package grafl.msg.lksg.tool.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.*;

@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class CompanyBpnsPK implements Serializable {

    @Id
    @Column(name = "company")
    private String company;
    @Id
    @Column(name = "bpn")
    private String bpn;

}
