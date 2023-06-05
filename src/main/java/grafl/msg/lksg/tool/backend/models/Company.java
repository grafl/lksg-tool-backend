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
@Table(name = "companies", schema = "lksg_tool")
public class Company implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "nr")
    private String nr;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "location")
    private String location;

    @Column(name = "country")
    private String country;

}
