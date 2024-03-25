package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.Media;
import meseriasiapi.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.NO_MEDIA_WITH_THIS_ID;
import static meseriasiapi.exceptions.messages.Messages.NO_MEDIA_WITH_THIS_URL_FOUND;

@Service
@AllArgsConstructor
public class MediaService {


    public static final String MEDIA_WAS_SUCCESSFULLY_DELETED = "Media was successfully deleted";
    private final MediaRepository mediaRepository;

    public Media findById(UUID mediaId) {
        Optional<Media> media = mediaRepository.findById(mediaId);
        if (media.isEmpty()) {
            throw new EntityNotFoundException(NO_MEDIA_WITH_THIS_ID);
        }
        return media.get();
    }

    public Media findByMediaUrl(String mediaUrl) {
        Optional<Media> media = mediaRepository.findByMediaUrl(mediaUrl);
        if (media.isEmpty()) {
            throw new EntityNotFoundException(NO_MEDIA_WITH_THIS_URL_FOUND);
        }
        return media.get();
    }

    public Media createMedia(Media media) {
        return mediaRepository.save(media);
    }

    public String deleteMedia(UUID id) {
        Optional<Media> media = mediaRepository.findById(id);
        if (media.isPresent()) {
            mediaRepository.delete(media.get());
            return MEDIA_WAS_SUCCESSFULLY_DELETED;
        } else {
            throw new EntityNotFoundException(NO_MEDIA_WITH_THIS_ID);
        }
    }

    public List<Media> getAllMedias() {
        return mediaRepository.findAll();
    }
}
