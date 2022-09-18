@@ -0,0 +1,70 @@
package recipes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.dto.RecipesDto;
import recipes.service.RecipesService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RecipesController {

    private final RecipesService recipesService;

    @PostMapping("/api/recipe/new")
    public Map<String, Long> saveRecipe(@Valid @RequestBody RecipesDto recipesDto) {
        Long id = recipesService.save(recipesDto);
        return Map.of("id", id);
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity<RecipesDto> getRecipes(@PathVariable Long id) {
        RecipesDto recipesDto = recipesService.get(id);
        if (recipesDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return  ResponseEntity.ok(recipesDto);
        }
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<Void> removeRecipe(@PathVariable Long id) {
        if(recipesService.remove(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<Void> updateRecipeById(@PathVariable Long id, @Valid @RequestBody RecipesDto recipesDto) {
        if(recipesService.update(id, recipesDto)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/recipe/search")
    public ResponseEntity<List<RecipesDto>> search(@RequestParam(required = false, name = "category") String category,
                                                   @RequestParam(required = false, name = "name") String name) {

        if ((category == null && name == null) || (category != null && name != null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (category != null) {
            return ResponseEntity.ok(recipesService.searchByCategory(category));
        } else {
            return ResponseEntity.ok(recipesService.searchByName(name));
        }
    }
}
