package meseriasiapi.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "projects")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Project extends BaseEntity {
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @NonNull
    private Category category;
    @NonNull
    private LocalDateTime creationDate;
    @NonNull
    private Boolean status;
    @NonNull
    private String county;
    @NonNull
    private String city;
    @NonNull
    private LocalDate expectedDueDate;
    private String actionDuration;

}
