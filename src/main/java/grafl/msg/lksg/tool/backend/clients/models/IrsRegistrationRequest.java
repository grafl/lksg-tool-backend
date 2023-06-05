package grafl.msg.lksg.tool.backend.clients.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder
@Data
@Jacksonized
@NoArgsConstructor
public class IrsRegistrationRequest {

    private String bomLifecycle;
    private String callbackUrl;
    private String globalAssetId;
    private List <String> incidentBpns;
}
