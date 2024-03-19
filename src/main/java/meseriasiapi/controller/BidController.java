package meseriasiapi.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Bid;
import meseriasiapi.dto.BidDto;
import meseriasiapi.mapper.BidMapper;
import meseriasiapi.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;
    private final BidMapper bidMapper;

    @PostMapping
    public ResponseEntity<BidDto> createBid(@RequestBody @NonNull BidDto bidDto){
        return new ResponseEntity<>(bidMapper.toDto(bidService.createBid(bidMapper.toEntity(bidDto))),CREATED);
    }
    @GetMapping
    public ResponseEntity<List<BidDto>> getAllBids(){
        List<Bid> bidList = bidService.getAllBids();
        List<BidDto> bidDtoList = bidList.stream().map(bidMapper::toDto).toList();
        return new ResponseEntity<>(bidDtoList,OK);
    }
    @GetMapping("/{bidId}")
    public ResponseEntity<BidDto> getBidById(@PathVariable UUID bidId){
        return new ResponseEntity<>(bidMapper.toDto(bidService.findById(bidId)),OK);
    }
    @DeleteMapping("/{bidId}")
    public ResponseEntity<String> delete(@PathVariable UUID bidId){
        return new ResponseEntity<>(bidService.deleteBid(bidId),OK);

    }
}
