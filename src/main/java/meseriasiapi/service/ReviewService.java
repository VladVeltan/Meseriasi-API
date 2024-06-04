package meseriasiapi.service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import meseriasiapi.domain.Review;
import meseriasiapi.domain.User;
import meseriasiapi.dto.ReviewDto;
import meseriasiapi.repository.ReviewRepository;
import meseriasiapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Transactional
    public Review createReview(ReviewDto reviewDto) {
        User user = userRepository.findByEmail(reviewDto.getUserEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        User handyman = userRepository.findByEmail(reviewDto.getHandymanEmail())
                .orElseThrow(() -> new EntityNotFoundException("Handyman not found"));

        Review review = new Review();
        review.setUser(user);
        review.setHandyman(handyman);
        review.setMark(reviewDto.getMark());
        review.setMessage(reviewDto.getMessage());

        reviewRepository.save(review);
        updateHandymanRating(handyman);
        return review;
    }

    public List<Review> getReviewsForHandyman(String handymanEmail) {
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
