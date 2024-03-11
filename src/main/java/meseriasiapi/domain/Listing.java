package meseriasiapi.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Table(name = "listings")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Listing extends BaseEntity {
    @NonNull
    private String title;
    @NonNull
    private String description;
    @Enumerated(value = EnumType.STRING)
    @NonNull
    private Category category;
    @NonNull
    private String county;
    @NonNull
    private String city;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
