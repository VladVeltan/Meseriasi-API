package meseriasiapi.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDto {
    private String userEmail;
    private String handymanEmail;
    private double mark;
    private String message;
}
