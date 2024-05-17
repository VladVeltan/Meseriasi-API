package meseriasiapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ListingDto {
    private UUID id;
    private String title;
    private String description;
    private String category;
    private String county;
    private String city;
    private Boolean status;
    private LocalDateTime creationDate;
    private String userEmail;
    private String userPhone;
    private String userFirstName;
    private String userLastName;
}
