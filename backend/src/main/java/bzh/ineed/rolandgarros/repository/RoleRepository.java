package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.ERole;
import bzh.ineed.rolandgarros.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
