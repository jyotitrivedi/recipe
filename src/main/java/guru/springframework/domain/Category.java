package guru.springframework.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String categoryName;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipe;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(Set<Recipe> recipe) {
        this.recipe = recipe;
    }
}
