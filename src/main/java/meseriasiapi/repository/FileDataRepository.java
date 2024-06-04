package meseriasiapi.repository;


import meseriasiapi.domain.FileData;
import meseriasiapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    Optional<FileData> findByName(String fileName);

    List<FileData> findByUser(User user);
}