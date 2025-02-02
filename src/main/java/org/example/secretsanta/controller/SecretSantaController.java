package org.example.secretsanta.controller;

import org.example.secretsanta.model.SecretSantaPairDTO;
import org.example.secretsanta.service.SecretSantaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/secretsanta")
public class SecretSantaController {

    private final SecretSantaService secretSantaService;

    public SecretSantaController(SecretSantaService secretSantaService) {
        this.secretSantaService = secretSantaService;
    }

    @GetMapping
    public List<SecretSantaPairDTO> getPairs() {
        return secretSantaService.getPairs();
    }
}
