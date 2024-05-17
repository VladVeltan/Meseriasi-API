package meseriasiapi.mapper;

import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Bid;
import meseriasiapi.dto.BidDto;
import meseriasiapi.service.ProjectService;
import meseriasiapi.service.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BidMapper {
    private final UserService userService;
    private final ProjectService projectService;


    public Bid toEntity(BidDto bidDto){
        return Bid.builder()
                .id(bidDto.getId())
                .amount(bidDto.getAmount())
                .message(bidDto.getMessage())
                .creationDate(bidDto.getCreationDate())
                .bidder(userService.findByEmail(bidDto.getBidderEmail()))
                .project(projectService.findById(bidDto.getProjectId()))
                .build();
    }

    public BidDto toDto(Bid bid){
        return BidDto.builder()
                .id(bid.getId())
                .amount(bid.getAmount())
                .message(bid.getMessage())
                .creationDate(bid.getCreationDate())
                .bidderEmail(bid.getBidder().getEmail())
                .projectId(bid.getProject().getId())
                .build();
    }

}
