package az.ingress.kafkaproducer.repository;

import az.ingress.kafkaproducer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
