package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.Category;
import meseriasiapi.domain.Listing;
import meseriasiapi.repository.ListingRepository;
import org.springframework.stereotype.Service;

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
        if (checkIfCategoryIsInEnum(listing.getCategory().name())) {
            throw new EntityNotFoundException(LISTING_CATEGORY_NOT_FOUND);
        }

        return listingRepository.save(listing);

    }

    public Listing updateListing(Listing newUser) {
        if (checkIfCategoryIsInEnum(newUser.getCategory().name())) {
            throw new EntityNotFoundException(CATEGORY_DOES_NOT_EXIST);
        }
        Optional<Listing> existingListingById = listingRepository.findById(newUser.getId());
        if (existingListingById.isEmpty()) {
            throw new EntityNotFoundException(NO_LISTING_WITH_THIS_ID_FOUND);
        }
        Listing existingListing = existingListingById.get();

        Listing updatedListing = Listing.builder()
                .id(existingListing.getId())
                .title(newUser.getTitle())
                .description(newUser.getDescription())
                .category(newUser.getCategory())
                .county(newUser.getCounty())
                .city(newUser.getCity())
                .media(newUser.getMedia())
                .user(newUser.getUser())
                .build();

        return listingRepository.save(updatedListing);
    }
}
