package meseriasiapi.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Review;
import meseriasiapi.dto.ReviewDto;
import meseriasiapi.mapper.ReviewMapper;
import meseriasiapi.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody @NonNull ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewMapper.toDto(reviewService.createReview(reviewMapper.toEntity(reviewDto))), HttpStatus.CREATED);
    }

    @GetMapping("/{handymanEmail}")
    public ResponseEntity<List<ReviewDto>> getReviewsByHandymanEmail(@PathVariable String handymanEmail) {
        List<Review> reviews = reviewService.findByHandymanEmail(handymanEmail);
        List<ReviewDto> reviewDtos = reviews.stream().map(reviewMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }
}
