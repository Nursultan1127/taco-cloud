package sia.tacocloud.controllers

import mu.KLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import sia.tacocloud.domain.Ingredient
import sia.tacocloud.domain.Order
import sia.tacocloud.domain.Taco
import javax.validation.Valid
import kotlin.streams.toList


@Controller
@RequestMapping("/design")
class DesignTacoController {

    @GetMapping
    fun showDesignForm(model: Model): String {
        val ingredients: List<Ingredient> = listOf(
            Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
            Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
            Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        )
        val types = Ingredient.Type.values()
        for (type in types) {
            model.addAttribute(
                type.toString().lowercase(),
                filterByType(ingredients, type)
            )
        }
        model.addAttribute("design", Taco())
        return "design"
    }

    @PostMapping
    fun processDesign(
        @Valid design: Taco,
        errors: Errors,
        @ModelAttribute order: Order,
    ): String {
        logger.info("Processing design: $design")

        if (errors.hasErrors()) {
            return "design"
        }
        return "redirect:/orders/current"
    }

    private fun filterByType(ingredients: List<Ingredient>, type: Ingredient.Type): List<Ingredient> =
        ingredients
            .stream()
            .filter { x: Ingredient -> x.type == type }
            .toList()

    private companion object : KLogging()
}