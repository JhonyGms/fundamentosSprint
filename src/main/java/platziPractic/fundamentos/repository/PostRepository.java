package platziPractic.fundamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platziPractic.fundamentos.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
