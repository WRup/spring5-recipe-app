package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {




        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("How to Make Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("Simply Recipies");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setIngredients(guacamoleIngredients());


        Notes notes = new Notes();
        notes.setRecipeNotes("Be careful handling chiles if using." +
                " Wash your hands thoroughly after handling and" +
                " do not touch your eyes or the area near your eyes with your hands for several hours.");

        perfectGuacamole.setNotes(notes);

        Optional<Category> category = categoryRepository.findByDescription("American");

        perfectGuacamole.setCategories(Set.of(category.get()));


        recipeRepository.save(perfectGuacamole);
    }


    public Set<Ingredient> guacamoleIngredients() {

        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> ounce = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasure> dash = unitOfMeasureRepository.findByDescription("Dash");

        Ingredient ripeAvocados = new Ingredient();
        ripeAvocados.setAmount(new BigDecimal(2));
        ripeAvocados.setDescription("ripe avocados");
        ripeAvocados.setUom(ounce.get());

        Ingredient salt = new Ingredient();
        salt.setAmount(new BigDecimal(1 / 4));
        salt.setDescription("salt");
        salt.setUom(teaspoon.get());

        Ingredient freshLime = new Ingredient();
        freshLime.setAmount(new BigDecimal(1));
        freshLime.setDescription("fresh lime juice or lemon juice");
        freshLime.setUom(tablespoon.get());

        Ingredient redOnion = new Ingredient();
        redOnion.setAmount(new BigDecimal(2));
        redOnion.setDescription("minced red onion or thinly sliced green onion");
        redOnion.setUom(tablespoon.get());

        Ingredient serranoChiles = new Ingredient();
        serranoChiles.setAmount(new BigDecimal(2));
        serranoChiles.setDescription("serrano chiles, stems and seeds removed, minced");
        serranoChiles.setUom(ounce.get());

        Ingredient cilantro = new Ingredient();
        cilantro.setAmount(new BigDecimal(2));
        cilantro.setDescription("cilantro (leaves and tender stems), finely chopped");
        cilantro.setUom(tablespoon.get());

        Ingredient blackPeeper = new Ingredient();
        blackPeeper.setAmount(new BigDecimal(1));
        blackPeeper.setDescription("freshly grated black pepper");
        blackPeeper.setUom(dash.get());

        Ingredient tomato = new Ingredient();
        tomato.setAmount(new BigDecimal(1 / 2));
        tomato.setDescription("ripe tomato, seeds and pulp removed, chopped");
        tomato.setUom(ounce.get());

        Ingredient radishes = new Ingredient();
        radishes.setDescription("Red radishes or jicama, to garnish");

        Ingredient tortilla = new Ingredient();
        tortilla.setDescription("Tortilla chips, to serve");


        return Set.of(ripeAvocados, salt, freshLime, redOnion, serranoChiles, cilantro, blackPeeper, tomato, radishes, tortilla);
    }
}
