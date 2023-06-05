package grafl.msg.lksg.tool.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.*;

@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class EssIncidentResponsePK implements Serializable {

    @Id
    @Column(name = "request")
    private String request;

    @Id
    @Column(name = "job")
    private String job;

}