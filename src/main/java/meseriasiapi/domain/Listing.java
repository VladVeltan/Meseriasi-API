package meseriasiapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private String category;
    private String county;
    private String city;
    private String media;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
