
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	@Query("select f from Event f where f.category.id = ?1")
	public Collection<Event> findEventByCategoryId(int categoryId);

	@Query("select e from Event e where e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1)")
	public Collection<Event> findEventsByFollower(int clienId);

	@Query("select e from Event e where e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1 and c.id=?2)")
	public Collection<Event> findEventsByFollowerAndClub(int clienId, int clubId);

}
