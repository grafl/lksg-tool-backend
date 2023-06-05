package grafl.msg.lksg.tool.backend.models;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder
@Data
@Entity
@Jacksonized
@NoArgsConstructor
@Table(name = "company_bpns", schema = "lksg_tool")
@IdClass(CompanyBpnsPK.class)
public class CompanyBpns implements Serializable {

    @Id
    @Column(name = "company")
    private String company;

    @Id
    @Column(name = "bpn")
    private String bpn;

}
