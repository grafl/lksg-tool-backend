package grafl.msg.lksg.tool.backend.controllers;

import grafl.msg.lksg.tool.backend.models.Analysis;
import grafl.msg.lksg.tool.backend.services.AnalysisService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RequestMapping(value = "/api/lksg_tool/analyses", produces = { APPLICATION_JSON_VALUE })
@RestController
@Slf4j
@CrossOrigin
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Analysis>> getAllAnalyses() {
        // log.info("getAllAnalyses()");
        return ok().body(this.analysisService.getAllAnalyses());
    }

}
