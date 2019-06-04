
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Event;
import domain.Opinion;
import domain.ParticipationEvent;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	@Query("select f from Event f where f.category.id = ?1")
	Collection<Event> findEventByCategoryId(int categoryId);

	@Query("select e from Event e where e.draftMode=false and e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1)")
	Collection<Event> findEventsByFollower(int clienId);

	@Query("select e from Event e where e.draftMode=false and e.status <> 'CANCELLED' and e.momentEnd>=current_date and e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1)")
	Collection<Event> findAvailableEventsByFollower(int clienId);

	@Query("select e from Event e where e.draftMode=false and e.status <> 'CANCELLED' and e.momentEnd<current_date and e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1)")
	Collection<Event> findFinishedEventsByFollower(int clienId);

	@Query("select e from Event e where e.draftMode=false and e.status = 'CANCELLED' and e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1)")
	Collection<Event> findCancelledEventsByFollower(int clienId);

	@Query("select e from Event e where e.draftMode=false and e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1 and c.id=?2)")
	Collection<Event> findEventsByFollowerAndClub(int clienId, int clubId);

	@Query("select e from Event e where e.draftMode=false and e.status <> 'CANCELLED' and e.momentEnd>=current_date and e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1 and c.id=?2)")
	Collection<Event> findAvailableEventsByFollowerAndClub(int clienId, int clubId);

	@Query("select e from Event e where e.draftMode=false and e.status <> 'CANCELLED' and e.momentEnd<current_date and e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1 and c.id=?2)")
	Collection<Event> findFinishedEventsByFollowerAndClub(int clienId, int clubId);

	@Query("select e from Event e where e.draftMode=false and e.status = 'CANCELLED' and e.club.id in (select c.id from Club c join c.follows f where f.client.id = ?1 and c.id=?2)")
	Collection<Event> findCancelledEventsByFollowerAndClub(int clienId, int clubId);

	@Query("select f from Event f where f.draftMode=false and (f.ticker like %:keyword%  or f.title like %:keyword% or f.description like %:keyword% or f.club.name like %:keyword% or (f.category = (select c from Category c join c.title k where (key(k) like %:lang%) and (k like %:keyword%)))) and (f.momentStart BETWEEN :deadlineMin and :deadlineMax) and (f.price BETWEEN :minPrice and :maxPrice)")
	Collection<domain.Event> searchEvent(@Param("keyword") String keyword, @Param("lang") String lang, @Param("deadlineMin") Date deadlineMin, @Param("deadlineMax") Date deadlineMax, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

	@Query("select f from Event f where ?1 member of f.participationsEvent")
	Event findByParticipationForm(ParticipationEvent participation);

	@Query("select e from Event e join e.participationsEvent p where p.client.id = ?1")
	Collection<Event> findByParticipation(int clientId);

	@Query("select e from Event e join e.participationsEvent p where p.client.id = ?1 and e.status <> 'CANCELLED' and e.momentEnd<current_date")
	Collection<Event> findByParticipationAndFinalize(int clientId);

	@Query("select e from Event e join e.opinions o where o.client.id = ?1")
	Collection<Event> findByOpinion(int clientId);

	@Query("select f from Event f where ?1 member of f.opinions")
	Event findByOpinionForm(Opinion opinion);

	@Query("select e from Event e where e.club.id = ?1")
	Collection<Event> findEventsByClub(int clubId);

	@Query("select e from Event e where e.club.id = ?1 and e.draftMode = false order by e.momentEnd desc")
	Collection<Event> findFinalModelAndClub(int clubId);

	@Query("select e from Event e where e.draftMode = false order by e.momentEnd desc")
	Collection<Event> findFinalModel();

	@Query("select f from Event f where f.club.manager.id = ?1")
	Collection<Event> findByManager(int managerId);

	@Query("select f from Event f where f.club.manager.id = ?1 and f.draftMode=true")
	Collection<Event> findByManagerAndDraft(int managerId);

	@Query("select f from Event f where f.club.manager.id = ?1 and f.draftMode=false")
	Collection<Event> findByManagerAndFinal(int managerId);

}
