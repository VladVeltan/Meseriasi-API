package meseriasiapi.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "file_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FileData extends BaseEntity{
    @NonNull
    private String name;
    @NonNull
    private String type;
    @NonNull
    private String filePath;
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "listing_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Listing listing;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

}