@@ -0,0 +1,11 @@
package recipes.repository;

import org.springframework.data.repository.CrudRepository;
import recipes.entities.RecipesEntity;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<RecipesEntity, Long> {

    Optional<RecipesEntity> findFirstByName(String name);
}
