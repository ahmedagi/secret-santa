package org.example.secretsanta.repository;

import org.example.secretsanta.model.SecretSantaPair;
import org.example.secretsanta.model.SecretSantaPairDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SecretSantaPairRepository extends CrudRepository<SecretSantaPair, Long> {

    @Query("""
    SELECT pair.giver_id, giver.name AS giver_name, 
           pair.receiver_id, receiver.name AS receiver_name
    FROM pair 
    LEFT JOIN list ON pair.list_id = list.id
    LEFT JOIN employee giver ON pair.giver_id = giver.id
    LEFT JOIN employee receiver ON pair.receiver_id = receiver.id
    WHERE list.created_at = (SELECT MAX(created_at) FROM list);
""")
    List<SecretSantaPairDTO> findPairs();

    @Query("""
    SELECT EXISTS(
        SELECT 1 
        FROM pair 
        WHERE giver_id = :employeeId OR receiver_id = :employeeId
    )
""")
    Boolean existsByGiverIdOrReceiverId(Long employeeId);
}
