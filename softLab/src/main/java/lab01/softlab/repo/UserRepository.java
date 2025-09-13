package lab01.softlab.repo;

import lab01.softlab.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository interface
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
