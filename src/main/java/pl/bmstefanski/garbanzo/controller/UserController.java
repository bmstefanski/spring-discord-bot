package pl.bmstefanski.garbanzo.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.bmstefanski.garbanzo.entity.impl.UserEntityImpl;
import pl.bmstefanski.garbanzo.repository.UserRepository;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/by-id/{id}")
  public ResponseEntity<UserEntityImpl> getUserById(@PathVariable long id) {
    return this.userRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/by-name/{name}")
  public ResponseEntity<UserEntityImpl> getUserByName(@PathVariable String name) {
    return this.userRepository.findByName(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/")
  public ResponseEntity<List<UserEntityImpl>> getAllUsers(@RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "100") int size) {

    if (page < 0 || size < 1) {
      return ResponseEntity.badRequest().build();
    }

    Pageable pageable = PageRequest.of(page, size);
    Page<UserEntityImpl> entities = this.userRepository.findAll(pageable);

    if (page > (entities.getTotalPages() - 1)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(entities.getContent());
  }


}
