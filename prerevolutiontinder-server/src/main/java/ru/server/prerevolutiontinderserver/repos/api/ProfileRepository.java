package ru.server.prerevolutiontinderserver.repos.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.server.prerevolutiontinderserver.entity.Profile;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

	@Query(value = "select * from PROFILE WHERE ID >= :id LIMIT 1", nativeQuery = true)
	Profile findProfileByIdLimitOne(@Param(value = "id") Long id);

	@Query(value = "select * from PROFILE WHERE ID >= :id AND SEX <> :sex LIMIT 1", nativeQuery = true)
	Profile findProfileByIdLimitOneForUser(@Param(value = "id") Long id, @Param(value = "sex") String sex);

	@Query(value = "SELECT P.ID, P.description, P.name, P.sex FROM Profile P, User U, Matches M WHERE P.id=U.PROFILE_ID AND U.id=M.ID_USER_FROM AND M.id_profile_to= :id AND M.CHOICE=1", nativeQuery = true)
	List<Profile> findMatchesProfile(@Param(value = "id") Long id);
}
