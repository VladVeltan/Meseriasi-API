package meseriasiapi.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Media;
import meseriasiapi.dto.MediaDto;
import meseriasiapi.mapper.MediaMapper;
import meseriasiapi.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    @PostMapping()
    public ResponseEntity<MediaDto> createMediaDto(@RequestBody @NonNull MediaDto mediaDto) {
        return new ResponseEntity<>(mediaMapper.toDto(mediaService.createMedia(mediaMapper.toEntity(mediaDto))), CREATED);
    }

    @GetMapping("/{mediaId}")
    public ResponseEntity<MediaDto> getMediaById(@PathVariable UUID mediaId) {
        try {
            return new ResponseEntity<>(mediaMapper.toDto(mediaService.findById(mediaId)), OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MediaDto>> getAllMedias() {
        List<Media> mediaList = mediaService.getAllMedias();
        List<MediaDto> mediaDtoList = mediaList.stream().map(mediaMapper::toDto).toList();
        return new ResponseEntity<>(mediaDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedia(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(mediaService.deleteMedia(id), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{idType}/{id}")
    public ResponseEntity<List<MediaDto>> getMediaByTypeAndId(@PathVariable String idType, @PathVariable UUID id) {
        List<Media> mediaList = mediaService.findMediaByTypeAndId(id, idType);
        List<MediaDto> mediaDtoList = mediaList.stream().map(mediaMapper::toDto).toList();
        return new ResponseEntity<>(mediaDtoList, OK);
    }

}
