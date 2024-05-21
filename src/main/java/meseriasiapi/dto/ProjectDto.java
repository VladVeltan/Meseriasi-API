package meseriasiapi.dto;

import lombok.Builder;
import lombok.Getter;
import meseriasiapi.domain.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class ProjectDto {
    private UUID id;
    private String title;
    private String description;
    private Boolean status;
    private LocalDateTime creationDate;
    private Category category;
    private String county;
    private String city;
    private String userEmail;
    private String userPhone;
    private String userFirstName;
    private String userLastName;
    private LocalDate expectedDueDate;
    private String actionDuration;
    private Boolean acceptBids;
}
