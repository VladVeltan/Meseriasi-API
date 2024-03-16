package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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

    public Project findById(UUID projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new EntityNotFoundException(NO_PROJECT_WITH_THIS_ID);
        }
        return project.get();
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {

        if (listingService.checkIfCategoryIsInEnum(project.getCategory().name())) {
            throw new EntityNotFoundException(PROJECT_CATEGORY_NOT_FOUND);
        }


        return projectRepository.save(project);
    }

    public Project updateProject(Project newProject) {
        if (listingService.checkIfCategoryIsInEnum(newProject.getCategory().name())) {
            throw new EntityNotFoundException(PROJECT_CATEGORY_NOT_FOUND);
        }
        Optional<Project> existingProjectById = projectRepository.findById(newProject.getId());
        if (existingProjectById.isEmpty()) {
            throw new EntityNotFoundException(NO_PROJECT_WITH_THIS_ID_FOUND);
        }
        Project existingProject = existingProjectById.get();

        Project updatedProject = Project.builder()
                .id(existingProject.getId())
                .title(newProject.getTitle())
                .description(newProject.getDescription())
                .category(newProject.getCategory())
                .status(newProject.getStatus())
                .creationDate(existingProject.getCreationDate())
                .county(newProject.getCounty())
                .city(newProject.getCity())
                .user(newProject.getUser())
                .build();
        return projectRepository.save(updatedProject);

    }

    public String deleteProject(UUID id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return PROJECT_WAS_DELETED_SUCCESSFULLY;
        } else {
            throw new EntityNotFoundException(NO_PROJECT_WITH_THIS_ID_FOUND);
        }
    }
}
