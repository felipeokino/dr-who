package drwho.dao;

import javax.transaction.Transactional;

import drwho.models.Doctor;
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
public interface DoctorDao extends CrudRepository<Doctor, Long>, PagingAndSortingRepository<Doctor, Long>  {

    /**
     * Return the user having the passed email or null if no user is found.
     *
     * @param email the user email.
     */
    Doctor findByAppointmentBookId(Long id);
    Page findByEmail(String email, Pageable pageable);
    Doctor findByCpf(String cpf);
    Page findBySpecialization(String specialization, Pageable pageable);


    // Page findByEmail(String email, Pageable pageable);
    //Page findAll(Pageable pageable);
    //Client findByCpf(String cpf);



} // class UserDao
// class UserDao

