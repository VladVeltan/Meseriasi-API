package meseriasiapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name="listings")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Listing extends BaseEntity{
    private String title;
    private String description;
    @Enumerated(value= EnumType.STRING)
    private Category category;
    private String county;
    private String city;
    private String media;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
