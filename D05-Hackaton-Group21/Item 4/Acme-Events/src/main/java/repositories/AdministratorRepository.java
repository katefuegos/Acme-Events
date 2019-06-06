
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Club;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	@Query("select h from Administrator h where h.userAccount.id = ?1")
	Administrator findAdministratorByUserAccount(int userAccountId);

	@Query("select a from Actor a where a.userAccount.username=?1")
	Administrator findAdminByUsername(String username);

	//DASHBOARD C
	//C1 - avg,min,max,stddev of the number of clubs per manager.
	@Query("select avg(1.0 * (select count(e) from Club e where e.manager.id = b.id)),min(1.0 * (select count(e) from Club e where e.manager.id = b.id)),max(1.0 * (select count(e) from Club e where e.manager.id = b.id)),stddev(1.0 * (select count(e) from Club e where e.manager.id = b.id)) from Manager b")
	Object[] queryC1();

	//C2 - avg,min,max,stddev of the number of events per club.
	@Query("select avg(1.0 * (select count(e) from Event e where e.club.id = b.id)),min(1.0 * (select count(e) from Event e where e.club.id = b.id)),max(1.0 * (select count(e) from Event e where e.club.id = b.id)),stddev(1.0 * (select count(e) from Event e where e.club.id = b.id)) from Club b")
	Object[] queryC2();

	//C3 -  Avg,min,max,stddev of the price per event.
	@Query("select avg(e.price)*1.0,min(e.price)*1.0,max(e.price)*1.0,stddev(e.price)*1.0 from Event e")
	Object[] queryC3();

	//C4 - 
	@Query("select avg(e.participationsEvent.size)*1.0,min(e.participationsEvent.size)*1.0,max(e.participationsEvent.size)*1.0,stddev(e.participationsEvent.size)*1.0 from Event e")
	Object[] queryC4();

	//C5 - The ratio of planned events.
	@Query("select ((select count(e)*1.0 from Event e where e.draftMode=false and e.status = 'AVAILABLE')*1.0)/(1.0*count(e1)) from Event e1")
	Double queryC5();

	//C6 - The ratio of cancelled events.
	@Query("select ((select count(e)*1.0 from Event e where e.draftMode=false and e.status = 'CANCELLED')*1.0)/(1.0*count(e1)) from Event e1")
	Double queryC6();

	// C7 - The listing of clubs who have at least 10% or more events with cancelled status.
	@Query("select c from Club c where (((select count(e) from Event e where e.club.id = c.id and e.draftMode=false and e.status = 'CANCELLED')*1.0)/1.0*(select count(ev) from Event ev where ev.draftMode=false and ev.club.id = c.id))>=0.1")
	Collection<Club> queryC7();

}
