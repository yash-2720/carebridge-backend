package com.kinthrahub.backend.sequence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sequence")
public class SequenceGeneratorController {

    private final SequenceGenerator sequenceGenerator;

    public SequenceGeneratorController(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @GetMapping("/{code}")
    public String generate(@PathVariable String code) {
        return sequenceGenerator.generateId(code);
    }

}