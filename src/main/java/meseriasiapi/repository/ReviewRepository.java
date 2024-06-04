package meseriasiapi.repository;

import meseriasiapi.domain.Review;
import meseriasiapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByHandyman(User handyman);
}
