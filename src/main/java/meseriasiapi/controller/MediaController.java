package meseriasiapi.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.dto.MediaDto;
import meseriasiapi.mapper.MediaMapper;
import meseriasiapi.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    @PostMapping()
    public ResponseEntity<MediaDto> createMediaDto(@RequestBody @NonNull MediaDto mediaDto) {
        try {
            return new ResponseEntity<>(mediaMapper.toDto(mediaService.createMedia(mediaMapper.toEntity(mediaDto))), CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
