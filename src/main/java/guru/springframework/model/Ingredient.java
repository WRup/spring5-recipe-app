package guru.springframework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Ingredient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;
    @OneToOne
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;
}
