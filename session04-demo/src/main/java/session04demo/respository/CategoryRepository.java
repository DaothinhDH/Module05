package session04demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import session04demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
