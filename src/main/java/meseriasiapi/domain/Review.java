package meseriasiapi.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "handyman_id", nullable = false)
    private User handyman;

    @Column(nullable = false)
    private double mark;

    @Column(length = 2000)
    private String message;
    @ManyToOne
    @JoinColumn(name="listing_id")
    private Listing listing;
    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
    private LocalDateTime createdAt;
    private String postTitle;
}
