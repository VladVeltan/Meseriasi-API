package meseriasiapi.mapper;

import lombok.AllArgsConstructor;
import meseriasiapi.domain.Listing;
import meseriasiapi.domain.Project;
import meseriasiapi.domain.Review;
import meseriasiapi.dto.ReviewDto;
import meseriasiapi.service.ListingService;
import meseriasiapi.service.ProjectService;
import meseriasiapi.service.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class ReviewMapper {

    private final UserService userService;
    private final ProjectService projectService;
    private final ListingService listingService;

    public ReviewDto toDto(Review review){

        UUID project_id=null;
        UUID listing_id=null;

        try{project_id=review.getProject().getId();}catch (Exception e){}
        try{listing_id=review.getListing().getId();}catch (Exception e){}

        String postTitle=null;

        if(listing_id!=null)
        {
            postTitle=listingService.findById(listing_id).getTitle();
        }
        else if (project_id!=null)
        {
            postTitle=projectService.findById(project_id).getTitle();

        }
        System.out.println("postTitle"+postTitle);

        return ReviewDto.builder()
                .id(review.getId())
                .project_id(project_id)
                .listing_id(listing_id)
                .message(review.getMessage())
                .mark(review.getMark())
                .handymanEmail(review.getHandyman().getEmail())
                .userEmail(review.getUser().getEmail())
                .createdAt(review.getCreatedAt())
                .userLastName(review.getUser().getLastName())
                .userFirstName(review.getUser().getFirstName())
                .postTitle(postTitle)
                .build();
    }


    public Review toEntity(ReviewDto reviewDto){

        Project project=null;
        Listing listing=null;

        try{project=projectService.findById(reviewDto.getProject_id());}catch (Exception e){}
        try{listing=listingService.findById(reviewDto.getListing_id());}catch (Exception e){}

        return Review.builder()
                .id(reviewDto.getId())
                .user(userService.findByEmail(reviewDto.getUserEmail()))
                .handyman(userService.findByEmail(reviewDto.getHandymanEmail()))
                .mark(reviewDto.getMark())
                .message(reviewDto.getMessage())
                .project(project)
                .listing(listing)
                .createdAt(reviewDto.getCreatedAt())
                .postTitle(reviewDto.getPostTitle())
                .build();
    }

}
