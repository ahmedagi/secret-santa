package org.example.secretsanta.service;

import org.example.secretsanta.model.Employee;
import org.example.secretsanta.model.SecretSantaList;
import org.example.secretsanta.model.SecretSantaPair;
import org.example.secretsanta.model.SecretSantaPairDTO;
import org.example.secretsanta.repository.EmployeeRepository;
import org.example.secretsanta.repository.SecretSantaListRepository;
import org.example.secretsanta.repository.SecretSantaPairRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SecretSantaService {

    private final EmployeeRepository employeeRepository;
    private final SecretSantaListRepository secretSantaListRepository;
    private final SecretSantaPairRepository secretSantaRepository;

    public SecretSantaService(
            EmployeeRepository employeeRepository,
            SecretSantaListRepository secretSantaListRepository,
            SecretSantaPairRepository secretSantaRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.secretSantaListRepository = secretSantaListRepository;
        this.secretSantaRepository = secretSantaRepository;
    }

    public List<SecretSantaPairDTO> getPairs() {
        return secretSantaRepository.findPairs();
    }

    @Transactional
    public List<SecretSantaPairDTO> generateNewPairs() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAllByDeletedFalse();
        if (employees.isEmpty()) {
            return new ArrayList<>();
        }

        Collections.shuffle(employees);

        SecretSantaList newList = secretSantaListRepository.save(new SecretSantaList(null, LocalDateTime.now()));

        List<SecretSantaPair> pairs = new ArrayList<>();
        for (int i = 0; i < employees.size() - 1; i++) {
            pairs.add(new SecretSantaPair(
                            employees.get(i).getId(),
                            employees.get(i + 1).getId(),
                            newList.getId()
                    )
            );
        }

        pairs.add(new SecretSantaPair(
                employees.getLast().getId(),
                employees.getFirst().getId(),
                newList.getId()
        ));

        secretSantaRepository.saveAll(pairs);
        return secretSantaRepository.findPairs();
    }
}
