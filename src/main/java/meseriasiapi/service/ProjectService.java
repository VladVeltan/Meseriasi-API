package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.Media;
import meseriasiapi.domain.Project;
import meseriasiapi.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.*;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ListingService listingService;
    private final MediaService mediaService;

    public Project findById(UUID projectId){
        Optional<Project> project=projectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new EntityNotFoundException(NO_PROJECT_WITH_THIS_ID);
        }
        return project.get();
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {

        if(listingService.checkIfCategoryIsInEnum(project.getCategory().name()))
        {
            throw new EntityNotFoundException(PROJECT_CATEGORY_NOT_FOUND);
        }

        Media media = new Media();
        try {
            mediaService.findByMediaUrl(project.getMedia().getMediaUrl());
        } catch (Exception e) {
            media = mediaService.createMedia(Media.builder().mediaUrl(project.getMedia().getMediaUrl()).build());
            project.setMedia(media);
        }

        return projectRepository.save(project);
    }

    public Project updateProject(Project newProject) {
        if(listingService.checkIfCategoryIsInEnum(newProject.getCategory().name()))
        {
            throw new EntityNotFoundException(PROJECT_CATEGORY_NOT_FOUND);
        }
        Optional<Project>existingProjectById=projectRepository.findById(newProject.getId());
        if(existingProjectById.isEmpty()){
            throw new EntityNotFoundException(NO_PROJECT_WITH_THIS_ID_FOUND);
        }
        Project existingProject=existingProjectById.get();

        Project updatedProject=Project.builder()
                .id(existingProject.getId())
                .title(newProject.getTitle())
                .description(newProject.getDescription())
                .category(newProject.getCategory())
                .user(newProject.getUser())
                .media(newProject.getMedia())
                .build();
        return projectRepository.save(updatedProject);

    }
}
