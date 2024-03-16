package meseriasiapi.dto;

import lombok.Builder;
import lombok.Getter;
import meseriasiapi.domain.Category;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ListingDto {
    private UUID id;
    private String title;
    private String description;
    private Category category;
    private String county;
    private String city;
    private Boolean status;
    private LocalDateTime creationDate;
    private UUID user_id;
}
