package grafl.msg.lksg.tool.backend.services;

import grafl.msg.lksg.tool.backend.models.Analysis;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnalysisService {

    public List<Analysis> getAllAnalyses() {
        return new ArrayList<>();
    }

}
