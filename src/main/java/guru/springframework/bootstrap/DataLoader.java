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
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("Simply Recipies");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        Set<Ingredient> ingredients = guacamoleIngredients();
        ingredients.forEach(x -> x.setRecipe(perfectGuacamole));
        perfectGuacamole.setIngredients(ingredients);

        perfectGuacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit." +
                " Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. " +
                "(Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the " +
                "guacamole and press down to cover it and to prevent air reaching it. " +
                "(The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
        );


        Notes notes = new Notes();
        notes.setRecipeNotes("Be careful handling chiles if using." +
                " Wash your hands thoroughly after handling and" +
                " do not touch your eyes or the area near your eyes with your hands for several hours.");
        notes.setRecipe(perfectGuacamole);

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
        radishes.setAmount(new BigDecimal(4));
        radishes.setDescription("Red radishes or jicama, to garnish");
        radishes.setUom(ounce.get());

        Ingredient tortilla = new Ingredient();
        tortilla.setAmount(new BigDecimal(25));
        tortilla.setDescription("Tortilla chips, to serve");
        tortilla.setUom(ounce.get());


        return Set.of(ripeAvocados, salt, freshLime, redOnion, serranoChiles, cilantro, blackPeeper, tomato, radishes, tortilla);
    }
}
