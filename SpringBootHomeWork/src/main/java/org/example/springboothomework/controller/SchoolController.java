package org.example.springboothomework.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboothomework.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/profit")
    public ResponseEntity<Double> showProfit() {
        return ResponseEntity.ok(schoolService.showProfit());
    }
}
