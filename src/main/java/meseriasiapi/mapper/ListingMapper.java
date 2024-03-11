package meseriasiapi.mapper;


import lombok.AllArgsConstructor;
import meseriasiapi.domain.Listing;
import meseriasiapi.dto.ListingDto;
import meseriasiapi.service.MediaService;
import meseriasiapi.service.UserService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListingMapper {
    private final UserService userService;
    private final MediaService mediaService;

    public ListingDto toDto(Listing listing) {
        return ListingDto.builder()
                .id(listing.getId())
                .title(listing.getTitle())
                .description(listing.getDescription())
                .category(listing.getCategory())
                .county(listing.getCounty())
                .city(listing.getCity())
                .media_id(listing.getMedia().getId())
                .user_id(listing.getUser().getId())
                .build();
    }

    public Listing toEntity(ListingDto listingDto) {
        return Listing.builder()
                .id(listingDto.getId())
                .title(listingDto.getTitle())
                .description(listingDto.getDescription())
                .category(listingDto.getCategory())
                .county(listingDto.getCounty())
                .city(listingDto.getCity())
                .media(mediaService.findById(listingDto.getMedia_id()))
                .user(userService.findById(listingDto.getUser_id()))
                .build();
    }
}
