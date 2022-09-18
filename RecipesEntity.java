@ -0,0 +1,33 @@
package recipes.entities;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RecipesEntity {

        @Id
        @GeneratedValue
        private long id;
        private String name;
        private String description;
        private LocalDateTime date;
        private String category;

        @ElementCollection
        private List<String> ingredients;

        @ElementCollection
        private List<String> directions;
}
