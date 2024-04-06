package meseriasiapi.controller;


import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Listing;
import meseriasiapi.dto.ListingDto;
import meseriasiapi.mapper.ListingMapper;
import meseriasiapi.service.ListingService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/listings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ListingController {
    private final ListingService listingService;
    private final ListingMapper listingMapper;

    @GetMapping
    public ResponseEntity<List<ListingDto>> getAllListings() {
        List<Listing> listingList = listingService.getAllListings();
        List<ListingDto> listingDtoList = listingList.stream().map(listingMapper::toDto).toList();
        return new ResponseEntity<>(listingDtoList, HttpStatus.OK);
    }

    @GetMapping("/sort/{fieldToSortBy}")
    public ResponseEntity<List<ListingDto>> getAllListingsWithPagination(@PathVariable String fieldToSortBy) {
        List<Listing> listingList = listingService.findAllListingsWithSorting(fieldToSortBy);
        List<ListingDto> listingDtoList = listingList.stream().map(listingMapper::toDto).toList();
        return new ResponseEntity<>(listingDtoList, HttpStatus.OK);
    }
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<ListingDto>> getAllListingsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {

        Page<Listing> listingList = listingService.findListingsWithPagination(offset,pageSize);
        Page<ListingDto> listingDtoList = listingList.map(listingMapper::toDto);

        return new ResponseEntity<>(listingDtoList, HttpStatus.OK);
    }

    @GetMapping("/pagination/sort/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<ListingDto>> getAllListingsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {

        Page<Listing> listingList = listingService.findListingsWithPaginationAndSorting(offset,pageSize,field);
        Page<ListingDto> listingDtoList = listingList.map(listingMapper::toDto);

        return new ResponseEntity<>(listingDtoList, HttpStatus.OK);
    }

    @GetMapping("/{listingId}")
    public ResponseEntity<ListingDto> getListingById(@PathVariable UUID listingId) {

        try {
            Listing listing = listingService.findById(listingId);
            ListingDto listingDto = listingMapper.toDto(listing);
            return new ResponseEntity<>(listingDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping()
    public ResponseEntity<ListingDto> createListing(@RequestBody @NonNull ListingDto listingDto) {
        return new ResponseEntity<>(listingMapper.toDto(listingService.createListing(listingMapper.toEntity(listingDto))), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ListingDto> updateListing(@RequestBody @NonNull ListingDto newListingDto) {
        return new ResponseEntity<>(listingMapper.toDto(listingService.updateListing(listingMapper.toEntity(newListingDto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListing(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(listingService.deleteListing(id), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}