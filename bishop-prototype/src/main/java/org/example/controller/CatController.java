package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.android.monitoring.module.MonitoringService;
import org.example.android.monitoring.module.Statistics;
import org.example.audit.android.module.WeylandWatchingYou;
import org.example.command.module.Command;
import org.example.command.module.Priority;
import org.example.command.module.ProducerCommand;
import org.example.model.dto.CatDto;
import org.example.service.CatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cat")
public class CatController {
    private final CatService catService;
    private final ProducerCommand producerCommand;
    private final MonitoringService monitoringService;

    @PostMapping("/create")
    @WeylandWatchingYou(name = "kafka")
    public ResponseEntity<CatDto> createCat(@RequestBody CatDto catDto,
                                            @RequestParam(name = "author") String author,
                                            @RequestParam(name = "priority") Priority priority) throws JsonProcessingException {
        monitoringService.submitTask(() -> {
            Statistics.incrementCompletedTasks(author);
        });
        producerCommand.sendCommand(Command.builder()
                .author(author)
                .description("method: create cat")
                .priority(priority)
                .time(LocalDate.now().toString())
                .build());
        return ResponseEntity.ok(catService.save(catDto));
    }

    @GetMapping("/{id}")
    @WeylandWatchingYou
    public ResponseEntity<CatDto> getCatById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(catService.getById(id));
    }


    @GetMapping("/all")
    public ResponseEntity<List<CatDto>> getAllCats() {
        return ResponseEntity.ok(catService.getAll());
    }

    @GetMapping("/quantity/tasks")
    public int getQueueSize() {
        return monitoringService.getQueueSize();
    }

    @GetMapping("/completed/by/author")
    public Map<String, Integer> getStats() {
        return Statistics.getAllCompleted();
    }
}
