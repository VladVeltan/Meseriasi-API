package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.Media;
import meseriasiapi.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.NO_MEDIA_WITH_THIS_ID;

@Service
@AllArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;

    public Media findById(UUID mediaId) {
        Optional<Media> media = mediaRepository.findById(mediaId);
        if (media.isEmpty()) {
            throw new EntityNotFoundException(NO_MEDIA_WITH_THIS_ID);
        }
        return media.get();
    }
}
