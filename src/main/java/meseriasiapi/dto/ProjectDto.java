package meseriasiapi.dto;

import lombok.Builder;
import lombok.Getter;
import meseriasiapi.domain.Category;

import java.util.UUID;

@Getter
@Builder
public class ProjectDto {
    private UUID id;
    private String title;
    private String description;
    private UUID user_id;
    private Category category;
}
