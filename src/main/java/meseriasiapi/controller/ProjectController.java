package meseriasiapi.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Project;
import meseriasiapi.dto.ProjectDto;
import meseriasiapi.mapper.ProjectMapper;
import meseriasiapi.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
        List<Project> projectList=projectService.getAllProjects();
        List<ProjectDto> projectDtoList=projectList.stream().map(projectMapper::toDto).toList();
        return new ResponseEntity<>(projectDtoList, HttpStatus.OK);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable UUID projectId) {

        try {
            Project project = projectService.findById(projectId);
            ProjectDto projectDto = projectMapper.toDto(project);
            return new ResponseEntity<>(projectDto, HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping()
    public ResponseEntity<ProjectDto> createProject(@RequestBody @NonNull ProjectDto projectDto) {
        return new ResponseEntity<>(projectMapper.toDto(projectService.createProject(projectMapper.toEntity(projectDto))), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<ProjectDto> updateProject(@RequestBody @NonNull ProjectDto newProjectDto){
        return new ResponseEntity<>(projectMapper.toDto(projectService.updateProject(projectMapper.toEntity(newProjectDto))),HttpStatus.OK);
    }
}
