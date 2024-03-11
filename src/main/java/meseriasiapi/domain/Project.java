package meseriasiapi.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name="projects")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Project extends BaseEntity{
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @NonNull
    @ManyToOne
    @JoinColumn(name="media_id")
    private Media media;
    @NonNull
    private Category category;

}
