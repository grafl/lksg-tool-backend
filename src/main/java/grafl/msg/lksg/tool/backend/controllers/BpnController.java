package grafl.msg.lksg.tool.backend.controllers;

import grafl.msg.lksg.tool.backend.models.Bpn;
import grafl.msg.lksg.tool.backend.models.VBpn;
import grafl.msg.lksg.tool.backend.repositories.VBpnRepository;
import grafl.msg.lksg.tool.backend.services.BpnService;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping(value = "/api/lksg_tool/bpns", produces = { APPLICATION_JSON_VALUE })
@RestController
@Slf4j
@CrossOrigin
public class BpnController {

    private final BpnService bpnService;
    private final VBpnRepository vBpnRepository;

    public BpnController(BpnService bpnService, VBpnRepository vBpnRepository) {
        this.bpnService = bpnService;
        this.vBpnRepository = vBpnRepository;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Bpn>> getAllBpns() throws UnknownHostException {
        log.info("getAllBpns()");
        return ok().body(this.bpnService.getAllBpns());
    }

    @GetMapping(value = "/v/")
    public ResponseEntity<List<VBpn>> getAllVBpns() {
        List<VBpn> rv = this.vBpnRepository.findAll();
        // log.info("getAllVBpns() -----> {} rows", rv.size());
        return ok().body(rv);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Bpn>> getBpnById(@PathVariable("id") String id) {
        // log.info("getBpnById({})", id);
        return ok().body(this.bpnService.getBpnById(id));
    }

    @PostMapping(value = "/")
    public List<String> createBpns(@RequestBody List<Bpn> bpns) {
        List<String> bpnIds = new ArrayList<>();
        for(Bpn bpn : bpns) {
            // log.info("createBpn({})", bpn.toString());
            bpnIds.add(this.bpnService.storeBpn(bpn));
        }
        return bpnIds;
    }

}
