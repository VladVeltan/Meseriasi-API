package meseriasiapi.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import meseriasiapi.domain.Project;
import meseriasiapi.dto.ProjectDto;
import meseriasiapi.mapper.ProjectMapper;
import meseriasiapi.service.ProjectService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<Project> projectList = projectService.getAllProjects();
        List<ProjectDto> projectDtoList = projectList.stream().map(projectMapper::toDto).toList();
        return new ResponseEntity<>(projectDtoList, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable UUID projectId) {
        try {
            Project project = projectService.findById(projectId);
            ProjectDto projectDto = projectMapper.toDto(project);
            return new ResponseEntity<>(projectDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping()
    public ResponseEntity<ProjectDto> createProject(@RequestBody @NonNull ProjectDto projectDto) {
        return new ResponseEntity<>(projectMapper.toDto(projectService.createProject(projectMapper.toEntity(projectDto))), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ProjectDto> updateProject(@RequestBody @NonNull ProjectDto newProjectDto) {
        return new ResponseEntity<>(projectMapper.toDto(projectService.updateProject(projectMapper.toEntity(newProjectDto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgram(@PathVariable UUID id) {
        return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.OK);
    }


    @GetMapping("/sort/{fieldToSortBy}")
    public ResponseEntity<List<ProjectDto>> getAllProjectsWithSorting(@PathVariable String fieldToSortBy) {
        List<Project> projectList = projectService.findAllProjectsWithSorting(fieldToSortBy);
        List<ProjectDto> projectDtoList = projectList.stream().map(projectMapper::toDto).toList();
        return new ResponseEntity<>(projectDtoList, HttpStatus.OK);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<ProjectDto>> getAllProjectsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Project> projectPage = projectService.findProjectsWithPagination(offset, pageSize);
        Page<ProjectDto> projectDtoPage = projectPage.map(projectMapper::toDto);
        return new ResponseEntity<>(projectDtoPage, HttpStatus.OK);
    }

    @GetMapping("/pagination/sort/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<ProjectDto>> getAllProjectsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Project> projectPage = projectService.findProjectsWithPaginationAndSorting(offset, pageSize, field);
        Page<ProjectDto> projectDtoPage = projectPage.map(projectMapper::toDto);
        return new ResponseEntity<>(projectDtoPage, HttpStatus.OK);
    }
    @GetMapping("/user/{userEmail}")
    public ResponseEntity<List<ProjectDto>> getProjectsByUserEmail(@PathVariable String userEmail) {
        List<Project> projectList = projectService.findProjectsByUserEmail(userEmail);
        List<ProjectDto> projectDtoList = projectList.stream().map(projectMapper::toDto).toList();
        return new ResponseEntity<>(projectDtoList, HttpStatus.OK);
    }
    @GetMapping("/count/count/count")
    public ResponseEntity<Long> countAllProjects() {
        long count = projectService.countAllProjects();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
