package drwho.dao;

import javax.transaction.Transactional;

import drwho.models.AppointmentBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 *
 * @author netgloo
 */
@Transactional
public interface AppointmentBookDao extends CrudRepository<AppointmentBook, Long>, PagingAndSortingRepository<AppointmentBook, Long>  {

    /**
     * Return the user having the passed email or null if no user is found.
     *
     * //@param email the user email.
     */
    Page findAll(Pageable pageable);
}