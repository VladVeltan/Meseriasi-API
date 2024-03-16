package meseriasiapi.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
    private LocalDateTime creationDate;
    @NonNull
    private Boolean status;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
