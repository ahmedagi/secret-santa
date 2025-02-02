package org.example.secretsanta.service;

import org.example.secretsanta.model.SecretSantaPairDTO;
import org.example.secretsanta.repository.SecretSantaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretSantaService {

    private final SecretSantaRepository secretSantaRepository;

    public SecretSantaService(SecretSantaRepository secretSantaRepository) {
        this.secretSantaRepository = secretSantaRepository;
    }

    public List<SecretSantaPairDTO> getPairs() {
        return secretSantaRepository.findPairs();
    }
}
