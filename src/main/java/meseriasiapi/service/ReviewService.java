package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Review;
import meseriasiapi.domain.User;
import meseriasiapi.repository.ListingRepository;
import meseriasiapi.repository.ProjectRepository;
import meseriasiapi.repository.ReviewRepository;
import meseriasiapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ListingRepository listingRepository;

    public Review createReview(Review review) {
        reviewRepository.save(review);
        Optional<User> optionalHandyman = userRepository.findByEmail(review.getHandyman().getEmail());
        User handyman;
        if (optionalHandyman.isPresent()) {
            handyman = optionalHandyman.get();
        } else {
            throw new EntityNotFoundException("Handyman not found for review");
        }
        updateHandymanRating(handyman);
        return review;
    }

    public List<Review> findByHandymanEmail(String handymanEmail) {
        User handyman = userRepository.findByEmail(handymanEmail)
                .orElseThrow(() -> new EntityNotFoundException("Handyman not found"));
        return reviewRepository.findByHandyman(handyman);
    }

    private void updateHandymanRating(User handyman) {
        List<Review> reviews = reviewRepository.findByHandyman(handyman);
        double averageRating = reviews.stream()
                .mapToDouble(Review::getMark)
                .average()
                .orElse(0.0);
        handyman.setRating(averageRating);
        userRepository.save(handyman);
    }
}
