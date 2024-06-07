package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.Category;
import meseriasiapi.domain.Listing;
import meseriasiapi.repository.ListingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.*;

@Service
@AllArgsConstructor
public class ListingService {

    private final ListingRepository listingRepository;

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Listing findById(UUID listingId) {
        Optional<Listing> listing = listingRepository.findById(listingId);
        if (listing.isEmpty()) {
            throw new EntityNotFoundException(NO_LISTING_WITH_THIS_ID_FOUND);
        }
        return listing.get();
    }

    public boolean checkIfCategoryIsInEnum(String category) {
        try {
            Category.valueOf(category);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public Listing createListing(Listing listing) {

        if (checkIfCategoryIsInEnum(listing.getCategory())) {
            throw new EntityNotFoundException(LISTING_CATEGORY_NOT_FOUND);
        }

        return listingRepository.save(listing);

    }

    public Listing updateListing(Listing newListing) {
        if (checkIfCategoryIsInEnum(newListing.getCategory())) {
            throw new EntityNotFoundException(CATEGORY_DOES_NOT_EXIST);
        }
        Optional<Listing> existingListingById = listingRepository.findById(newListing.getId());
        if (existingListingById.isEmpty()) {
            throw new EntityNotFoundException(NO_LISTING_WITH_THIS_ID_FOUND);
        }
        Listing existingListing = existingListingById.get();

        Listing updatedListing = Listing.builder()
                .id(existingListing.getId())
                .title(newListing.getTitle())
                .description(newListing.getDescription())
                .category(newListing.getCategory())
                .county(newListing.getCounty())
                .city(newListing.getCity())
                .user(newListing.getUser())
                .creationDate(existingListing.getCreationDate())
                .status(newListing.getStatus())
                .build();

        return listingRepository.save(updatedListing);
    }

        public String deleteListing(UUID id) {
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()) {
            listingRepository.delete(listing.get());
            return LISTING_WAS_SUCCESSFULLY_DELETED;
        } else {
            throw new EntityNotFoundException(NO_LISTING_WITH_THIS_ID_FOUND);
        }
    }

    public List<Listing> findAllListingsWithSorting(String fieldToSortBy) {
        return listingRepository.findAll(Sort.by(Sort.Direction.DESC, fieldToSortBy));
    }

    public Page<Listing> findListingsWithPagination(int offset, int pageSize) {
        return listingRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Page<Listing> findListingsWithPaginationAndSorting(int offset, int pageSize, String field) {
        return listingRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, field)));
    }

    public List<Listing> findListingsByUserEmail(String userEmail) {
        return listingRepository.findByUserEmail(userEmail);
    }

    public long countAllListings() {
        return listingRepository.count();
    }
}
