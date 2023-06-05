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
@Table(name = "bpns", schema = "lksg_tool")
public class Bpn implements Serializable {

    @Id
    @Column(name = "bpn")
    private String bpn;

}
