package meseriasiapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Builder
@Getter
public class BidDto {
    private UUID id;
    private double amount;
    private String message;
    private UUID bidderId;
    private UUID projectId;
}
