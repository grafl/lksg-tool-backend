package grafl.msg.lksg.tool.backend.clients;

import grafl.msg.lksg.tool.backend.clients.configurations.IrsFeignClientConfiguration;
import grafl.msg.lksg.tool.backend.clients.models.IrsRegistrationRequest;
import org.eclipse.tractusx.irs.component.Jobs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${irs.servicename}", configuration = IrsFeignClientConfiguration.class)
public interface IrsFeignClient {

    @PostMapping(
            path = "${irs.register.job.uri}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    String registerIrsJob(@RequestBody IrsRegistrationRequest irsRegistrationRequest);

    @GetMapping(
            path = "${irs.get.job.id.uri}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    Jobs getIrsJobId(@PathVariable("id") String id);

    @PutMapping(
            path = "${irs.register.job.uri}{id}/analysis/{state}/{impacted}/{aspect_type}",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    Jobs setAnalysisState(@PathVariable("id") String id,
                          @PathVariable("state") String state,
                          @PathVariable("impacted") String impacted,
                          @PathVariable("aspect_type") String aspect_type);

}
