package org.example.secretsanta.controller;

import org.example.secretsanta.model.SecretSantaPairDTO;
import org.example.secretsanta.service.SecretSantaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secret-santa")
public class SecretSantaController {

    private final SecretSantaService secretSantaService;

    public SecretSantaController(SecretSantaService secretSantaService) {
        this.secretSantaService = secretSantaService;
    }

    @GetMapping
    public List<SecretSantaPairDTO> getPairs() {
        return secretSantaService.getPairs();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<SecretSantaPairDTO> generatePairs() {
        return secretSantaService.generateNewPairs();
    }
}
