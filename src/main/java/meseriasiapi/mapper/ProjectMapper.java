package meseriasiapi.mapper;

import lombok.AllArgsConstructor;
import meseriasiapi.domain.Project;
import meseriasiapi.dto.ProjectDto;
import meseriasiapi.service.UserService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjectMapper {
    private final UserService userService;

    public ProjectDto toDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .user_id(project.getUser().getId())
                .build();
    }

    public Project toEntity(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .title(projectDto.getTitle())
                .description(projectDto.getDescription())
                .user(userService.findById(projectDto.getUser_id()))
                .build();

    }
}
