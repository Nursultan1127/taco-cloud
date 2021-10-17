package sia.tacocloud.controllers

import mu.KLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import sia.tacocloud.configs.properties.OrderProps
import sia.tacocloud.domain.Order
import javax.validation.Valid


@Controller
@RequestMapping("/orders")
class OrderController(
    private val orderProps: OrderProps,
) {

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        model.addAttribute("order", Order())
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid order: Order, errors: Errors): String? {
        logger.info("Order submitted: $order")

        if (errors.hasErrors()) {
            return "orderForm"
        }

        return "redirect:/"
    }

    private companion object : KLogging()
}