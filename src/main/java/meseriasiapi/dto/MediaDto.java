package meseriasiapi.dto;

import lombok.Builder;
import lombok.Getter;
import meseriasiapi.domain.MediaType;

import java.util.UUID;

@Getter
@Builder
public class MediaDto {
    private UUID id;
    private String mediaUrl;
    private MediaType mediaType;
    private UUID userId;
    private UUID listingId;
    private UUID projectId;
}
