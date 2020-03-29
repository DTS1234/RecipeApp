package guru.springframework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    private String description;
    private BigDecimal amount;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe){
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = uom;
        this.recipe = recipe;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom){
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = uom;
    }

    public Ingredient(){}
}
