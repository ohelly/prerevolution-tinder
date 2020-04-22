package ru.server.prerevolutiontinderserver.repos.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.server.prerevolutiontinderserver.entity.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

	@Query(value = "SELECT * FROM MATCHES WHERE ID_USER_FROM = :iduser and ID_PROFILE_TO = :id", nativeQuery = true)
	Match findByIdProfileToMatch(@Param(value = "iduser") Long iduser, @Param(value = "id") Long id);

	@Query(value = "SELECT CHOICE from MATCHES where ID_USER_FROM= :iduser and ID_PROFILE_TO= :idprofile AND CHOICE= 1", nativeQuery = true)
	String findMatch(@Param(value = "iduser") Long iduser, @Param(value = "idprofile") Long idprofile);
}
