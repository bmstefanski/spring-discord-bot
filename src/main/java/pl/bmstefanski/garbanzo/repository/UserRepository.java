package pl.bmstefanski.garbanzo.repository;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.bmstefanski.garbanzo.entity.impl.UserEntityImpl;

public interface UserRepository extends PagingAndSortingRepository<UserEntityImpl, Long> {

  Optional<UserEntityImpl> findByName(String name);

}
