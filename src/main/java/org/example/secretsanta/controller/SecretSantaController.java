package org.example.secretsanta.controller;

import org.example.secretsanta.model.SecretSantaPairDTO;
import org.example.secretsanta.service.SecretSantaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class SecretSantaController {

    private final SecretSantaService secretSantaService;

    public SecretSantaController(SecretSantaService secretSantaService) {
        this.secretSantaService = secretSantaService;
    }

    @GetMapping
    public String getPairs(Model model) {
        List<SecretSantaPairDTO> pairs = secretSantaService.getPairs();
        model.addAttribute("pairs", pairs);
        return "secret-santa.html";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<SecretSantaPairDTO> generatePairs() {
        return secretSantaService.generateNewPairs();
    }
}
