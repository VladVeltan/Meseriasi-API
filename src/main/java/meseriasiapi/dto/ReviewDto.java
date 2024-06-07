package meseriasiapi.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ReviewDto {
    private UUID id;
    private String userEmail;
    private String handymanEmail;
    private double mark;
    private String message;
    private UUID project_id;
    private UUID listing_id;
    private LocalDateTime createdAt;
    private String userFirstName;
    private String userLastName;
    private String postTitle;
}
