package org.example.secretsanta.controller;

import org.example.secretsanta.model.SecretSantaPairDTO;
import org.example.secretsanta.service.SecretSantaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if (!model.containsAttribute("pairs")) {
            List<SecretSantaPairDTO> pairs = secretSantaService.getPairs();
            model.addAttribute("pairs", pairs);
            System.out.println("hhh");
        }

        return "secret-santa.html";
    }

    @PostMapping
    public String generatePairs(RedirectAttributes redirectAttributes) {
        List<SecretSantaPairDTO> pairs = secretSantaService.generateNewPairs();
        redirectAttributes.addFlashAttribute("pairs", pairs);
        return "redirect:/";
    }
}
