package meseriasiapi.service;

import lombok.AllArgsConstructor;
import meseriasiapi.repository.ListingRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListingService {
    private final ListingRepository listingRepository;

}
