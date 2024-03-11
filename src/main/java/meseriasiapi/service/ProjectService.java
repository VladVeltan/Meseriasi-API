package meseriasiapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import meseriasiapi.domain.Project;
import meseriasiapi.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static meseriasiapi.exceptions.messages.Messages.NO_PROJECT_WITH_THIS_ID;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Project findById(UUID projectId){
        Optional<Project> project=projectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new EntityNotFoundException(NO_PROJECT_WITH_THIS_ID);
        }
        return project.get();
    }
}
