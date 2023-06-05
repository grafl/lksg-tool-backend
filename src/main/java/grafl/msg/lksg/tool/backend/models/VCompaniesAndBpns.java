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
@Table(name = "v_companies_bpns", schema = "lksg_tool")
public class VCompaniesAndBpns {

    @Id
    private String company_id;
    private String company_name;
    private String street;
    private String nr;
    private String zip_code;
    private String location;
    private String country;
    private String bpns;

}
