package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.FileData;
import meseriasiapi.domain.Listing;
import meseriasiapi.domain.Project;
import meseriasiapi.domain.User;
import meseriasiapi.repository.FileDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.util.*;

import static meseriasiapi.exceptions.messages.Messages.*;
import static meseriasiapi.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class FileDataService {


    private final FileDataRepository fileDataRepository;
    private final ProjectService projectService;
    private final ListingService listingService;
    private final UserService userService;

    public List<String> uploadImagesToFileSystem(MultipartFile[] files, UUID id, String whichEntity) throws IOException {
        List<String> uploadedFiles = new ArrayList<>();
        User user = null;
        Listing listing = null;
        Project project = null;

        switch (whichEntity) {
            case LISTING_ID:
                try {
                    listing = listingService.findById(id);
                } catch (Exception e) {
                    // Ignoră excepția pentru listing
                }
                break;
            case PROJECT_ID:
                try {
                    project = projectService.findById(id);
                } catch (Exception e) {
                    // Ignoră excepția pentru project
                }
                break;
            case USER_ID:
                try {
                    user = userService.findById(id);
                } catch (Exception e) {
                    // Ignoră excepția pentru user
                }
                break;
            default:
                throw new EntityNotFoundException(ENTITY_TYPE_MUST_BE_ONE_OF_LISTING_ID_PROJECT_ID_OR_USER_ID);
        }

        for (MultipartFile file : files) {
            String filePath = C_USERS_VLAD_DESKTOP_MESERIASI_LICENTA_MEDIA_STORAGE + file.getOriginalFilename();
            fileDataRepository.save(FileData.builder()
                    .name(Objects.requireNonNull(file.getOriginalFilename()))
                    .type(Objects.requireNonNull(file.getContentType()))
                    .filePath(filePath)
                    .user(user)
                    .project(project)
                    .listing(listing)
                    .build());

            file.transferTo(new File(filePath));
            uploadedFiles.add(FILE_UPLOADED_SUCCESSFULLY + filePath);
        }

        return uploadedFiles;
    }


    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getFilePath();
            return Files.readAllBytes(new File(filePath).toPath());
        }
        throw new FileSystemException(FAILED_TO_DOWNLOAD);
    }

    public List<byte[]> findAllFileDataByTypeAndId(UUID id, String whichEntity) throws IOException {
        List<FileData> allFileData = fileDataRepository.findAll();
        List<byte[]> filteredFileData = new ArrayList<>();

        for (FileData data : allFileData) {
            if (whichEntity.equals(LISTING_ID) && data.getListing() != null && data.getListing().getId().equals(id)) {
                filteredFileData.add(Files
                        .readAllBytes(new File(data.getFilePath()).toPath()));
            } else if (whichEntity.equals(PROJECT_ID) && data.getProject() != null && data.getProject().getId().equals(id)) {
                filteredFileData.add(Files.readAllBytes(new File(data.getFilePath()).toPath()));
            } else if (whichEntity.equals(USER_ID) && data.getUser() != null && data.getUser().getId().equals(id)) {
                filteredFileData.add(Files.readAllBytes(new File(data.getFilePath()).toPath()));
            }
        }

        return filteredFileData;
    }

}
