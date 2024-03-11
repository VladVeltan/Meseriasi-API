package meseriasiapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ProjectDto {
    private UUID id;
    private String title;
    private String description;
    private UUID user_id;
    private UUID media_id;

}
