package meseriasiapi.repository;


import meseriasiapi.domain.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ListingRepository extends JpaRepository<Listing, UUID> {
    long count();
    List<Listing> findByUserEmail(String userEmail);
}
