package meseriasiapi.controller;

import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Review;
import meseriasiapi.dto.ReviewDto;
import meseriasiapi.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto reviewDto) {
        Review review = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/{handymanEmail}")
    public ResponseEntity<List<Review>> getReviewsForHandyman(@PathVariable String handymanEmail) {
        List<Review> reviews = reviewService.getReviewsForHandyman(handymanEmail);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
