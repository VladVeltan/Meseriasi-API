package meseriasiapi.domain;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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
}
