package meseriasiapi.controller;



import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Listing;
import meseriasiapi.dto.ListingDto;
import meseriasiapi.mapper.ListingMapper;
import meseriasiapi.service.ListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
public class ListingController {
    private final ListingService listingService;
    private final ListingMapper listingMapper;
    @GetMapping
    public ResponseEntity<List<ListingDto>> getAllListings(){
        List<Listing>listingList=listingService.getAllListings();
        List<ListingDto> listingDtoList=listingList.stream().map(listingMapper::toDto).toList();
        return new ResponseEntity<>(listingDtoList, HttpStatus.OK);
    }

    @GetMapping("/{listingId}")
    public ResponseEntity<ListingDto>getListingById(@PathVariable UUID listingId){
        Listing listing=listingService.findById(listingId);
        ListingDto listingDto=listingMapper.toDto(listing);
        return new ResponseEntity<>(listingDto,HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<ListingDto>createListing(@RequestBody @NonNull ListingDto listingDto){
        return new ResponseEntity<>(listingMapper.toDto(listingService.createListing(listingMapper.toEntity(listingDto))),HttpStatus.CREATED);
    }
}
