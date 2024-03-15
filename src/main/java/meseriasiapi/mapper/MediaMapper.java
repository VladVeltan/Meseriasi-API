package meseriasiapi.mapper;

import lombok.AllArgsConstructor;
import meseriasiapi.domain.Listing;
import meseriasiapi.domain.Media;
import meseriasiapi.domain.Project;
import meseriasiapi.domain.User;
import meseriasiapi.dto.MediaDto;
import meseriasiapi.service.ListingService;
import meseriasiapi.service.ProjectService;
import meseriasiapi.service.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class MediaMapper {
    private final UserService userService;
    private final ListingService listingService;
    private final ProjectService projectService;

    public Media toEntity(MediaDto mediaDto) {

        User user = null;
        Listing listing = null;
        Project project = null;

        try {
            user = userService.findById(mediaDto.getUserId());
        } catch (Exception e) {
            // Ignoră excepția pentru user
        }

        try {
            listing = listingService.findById(mediaDto.getListingId());
        } catch (Exception e) {
            // Ignoră excepția pentru listing
        }

        try {
            project = projectService.findById(mediaDto.getProjectId());
        } catch (Exception e) {
            // Ignoră excepția pentru project
        }
        return Media.builder()
                .id(mediaDto.getId())
                .mediaUrl(mediaDto.getMediaUrl())
                .mediaType(mediaDto.getMediaType())
                .user(user)
                .listing(listing)
                .project(project)
                .build();

    }

    public MediaDto toDto(Media media) {
        UUID userId = null;
        UUID listingId = null;
        UUID projectId = null;

        try {
            userId = media.getUser() != null ? media.getUser().getId() : null;
        } catch (Exception e) {
            // Ignoră excepția pentru getUser
        }

        try {
            listingId = media.getListing() != null ? media.getListing().getId() : null;
        } catch (Exception e) {
            // Ignoră excepția pentru getListing
        }

        try {
            projectId = media.getProject() != null ? media.getProject().getId() : null;
        } catch (Exception e) {
            // Ignoră excepția pentru getProject
        }

        return MediaDto.builder()
                .id(media.getId())
                .mediaUrl(media.getMediaUrl())
                .mediaType(media.getMediaType())
                .userId(userId)
                .listingId(listingId)
                .projectId(projectId)
                .build();
    }


}
