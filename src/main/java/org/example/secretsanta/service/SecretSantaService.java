package org.example.secretsanta.service;

import org.example.secretsanta.model.Employee;
import org.example.secretsanta.model.SecretSantaList;
import org.example.secretsanta.model.SecretSantaPair;
import org.example.secretsanta.model.SecretSantaPairDTO;
import org.example.secretsanta.repository.EmployeeRepository;
import org.example.secretsanta.repository.SecretSantaListRepository;
import org.example.secretsanta.repository.SecretSantaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SecretSantaService {

    private final EmployeeRepository employeeRepository;
    private final SecretSantaListRepository secretSantaListRepository;
    private final SecretSantaRepository secretSantaRepository;

    public SecretSantaService(
            EmployeeRepository employeeRepository,
            SecretSantaListRepository secretSantaListRepository,
            SecretSantaRepository secretSantaRepository
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
        SecretSantaList newList = secretSantaListRepository.save(new SecretSantaList(null, LocalDateTime.now()));

        List<Employee> employees = (List<Employee>) employeeRepository.findAllByDeletedFalse();
        Collections.shuffle(employees);

        List<SecretSantaPair> pairs = new ArrayList<>();
        for (int i = 1; i < employees.size(); i += 2) {
            pairs.add(new SecretSantaPair(
                            employees.get(i - 1).getId(),
                            employees.get(i).getId(),
                            newList.getId()
                    )
            );
            pairs.add(new SecretSantaPair(
                            employees.get(i).getId(),
                            employees.get(i - 1).getId(),
                            newList.getId()
                    )
            );
        }

        if ((employees.size() % 2) != 0) {
            pairs.add(new SecretSantaPair(
                    employees.getLast().getId(),
                    null,
                    newList.getId()
            ));
        }

        secretSantaRepository.saveAll(pairs);
        return secretSantaRepository.findPairs();
    }
}
