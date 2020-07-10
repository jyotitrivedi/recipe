package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static guru.springframework.domain.Difficulty.EASY;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }



        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if (!pinchUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaspoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        //get category

        Optional<Category> indianCategoryOptional = categoryRepository.findByDescription("Indian");
        if (!indianCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category not found");

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if (!italianCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category Not Found");

        Category indianCategory = indianCategoryOptional.get();
        Category italianCategory = italianCategoryOptional.get();

        //onion pakoda

        Recipe pakodaRecipe = new Recipe();
        pakodaRecipe.setDescription("Classic Onion Pakoda");
        pakodaRecipe.setPrepTime(5);
        pakodaRecipe.setCookTime(25);
        pakodaRecipe.setServings(4);
        pakodaRecipe.setDifficulty(Difficulty.EASY);
        pakodaRecipe.setDirections("1 Slice the onions thinly and take them in a mixing bowl. Also, add " +
                "chopped green chillies." + "/n" +
                "2 If you do not have green chillies, then add red chilli powder. You can also add chopped coriander leaves if you want."
                + "/n" +
                "3 Add the spices – carom seeds, turmeric powder, asafoetida and salt." + "/n" +
                "4 Mix everything well. Cover and keep the onion, chillies and spice mixture aside for 15 to 20 minutes." + "/n" +
                "5 The onions would release water and then you can add water as required in the batter." + "/n" +
                "6 Add gram flour (besan). If you plan to add baking soda, then add at this step." + "/n" +
                "7 Add the required amount of water to make medium-thick batter." + "/n" +
                "8 Stir the whole mixture very well with a spoon or with your hands. The batter is ready to be fried." + "/n" +
                "Making Onion Pakoda" + "/n/n" +
                "1 In hot oil, add spoonfuls of the batter." + "/n" +
                "2 Depending on the size of the kadai or pan, you can add less or more. Just make sure you don’t over crowd the onion pakoda while frying." + "/n" +
                "3 When the pakora are a bit cooked, turn over with a slotted spoon and continue to fry." + "/n" +
                "4 You will have to turn them a few times for even frying." + "/n" +
                "5 Fry them till they look crisp and golden." + "/n" +
                "6 Remove with a slotted spoon and drain on kitchen paper towels for excess oil to be absorbed." + "/n" +
                "7 In the same oil fry slit green chilies." + "/n" +
                "8 Sprinkle some salt on the green chilies and mix well." + "/n" +
                "9 Serve onion pakoda With the fried green chilies or coriander chutney or tomato sauce.");

        Notes onionPakoraNotes = new Notes();
        onionPakoraNotes.setNotes("Texture:  For a softer pakoda, add some more water. For a crisp pakoda, slice onions thinly and evenly." + "/n" +
                "Baking Soda: You can choose to add baking soda or avoid it completely. We don’t like the soapy taste and flavor of baking soda, so I do not add it in the recipe. Though adding baking soda helps the pakoda to have a soft texture." + "/n" +
                "Adding hot oil in the batter: in the gram flour batter, you can also add 1 or 2 teaspoons of oil. This makes the onion pakoda crisp and it absorbs less oil while frying." + "/n" +
                "Additional ingredients: You can add a variety of herbs and spices in the batter – like crushed coriander seeds, red chilli powder, red chilli flakes, ginger-garlic paste, mint leaves, ground cumin powder, coriander leaves (cilantro). ");

        onionPakoraNotes.setRecipe(pakodaRecipe);
        pakodaRecipe.setNotes(onionPakoraNotes);

        pakodaRecipe.getIngredients().add(new Ingredient("medium to large sized onions", new BigDecimal(2), eachUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("gram flour (besan) or chickpea flour", new BigDecimal(1), cupsUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("red chilli powder", new BigDecimal(.5), teaspoonUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("chopped coriander leaves – optional", new BigDecimal(1), tableSpoonUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("garam masala powder – optional", new BigDecimal(.5), teaspoonUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("turmeric powder – optional", new BigDecimal(.25), teaspoonUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("carom seeds (ajwain)", new BigDecimal(1), teaspoonUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("asafoetida (hing)", new BigDecimal(1), pinchUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("baking soda – optional", new BigDecimal(1), pinchUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("oil as required – for shallow frying or deep frying", null, eachUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("water as required to make a medium thick batter", null, eachUom, pakodaRecipe));
        pakodaRecipe.getIngredients().add(new Ingredient("salt as required", null, eachUom, pakodaRecipe));

        pakodaRecipe.getCategories().add(indianCategory);

        recipes.add(pakodaRecipe);
        return recipes;
    }
    }
