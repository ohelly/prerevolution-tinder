package ru.server.prerevolutiontinderserver.repos.api;

import org.springframework.data.repository.CrudRepository;
import ru.server.prerevolutiontinderserver.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByToken(Integer token);

	User findByProfileId(Long id);
}
