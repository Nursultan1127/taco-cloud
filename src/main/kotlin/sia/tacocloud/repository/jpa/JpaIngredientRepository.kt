package sia.tacocloud.repository.jpa

import org.springframework.data.repository.CrudRepository
import sia.tacocloud.domain.Ingredient

interface JpaIngredientRepository : CrudRepository<Ingredient, String>