package grafl.msg.lksg.tool.backend.services;

import grafl.msg.lksg.tool.backend.clients.IrsFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AService {

    private final IrsFeignClient irsFeignClient;

    public AService(IrsFeignClient irsFeignClient) {
        this.irsFeignClient = irsFeignClient;
    }
}
