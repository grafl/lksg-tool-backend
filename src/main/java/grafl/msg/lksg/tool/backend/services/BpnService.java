package grafl.msg.lksg.tool.backend.services;

import grafl.msg.lksg.tool.backend.models.Bpn;
import grafl.msg.lksg.tool.backend.repositories.BpnRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BpnService {

    private final BpnRepository bpnRepository;

    public BpnService(BpnRepository bpnRepository) {
        this.bpnRepository = bpnRepository;
    }

    public List<Bpn> getAllBpns() {
        return this.bpnRepository.findAll();
    }

    public Optional<Bpn> getBpnById(String id) {
        return this.bpnRepository.findById(id);
    }

    public String storeBpn(Bpn bpn) {
        this.bpnRepository.save(bpn);
        return bpn.getBpn();
    }

}
