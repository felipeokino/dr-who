package drwho.dao;

import javax.transaction.Transactional;

import drwho.models.AppointmentSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

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
public interface AppointmentScheduleDao extends CrudRepository<AppointmentSchedule, Long>, PagingAndSortingRepository<AppointmentSchedule, Long>  {

    /**
     * Return the user having the passed email or null if no user is found.
     *
     //* @param email the user email.
     */
    Page findAll(Pageable pageable);
    Page findByClientId(Long id, Pageable pageable);
    Page findByDoctorId(Long id, Pageable pageable);
    Page findByDateSchedule(Date date, Pageable pageable);
    Page findByClientIdAndDoctorId(Long clientId, Long doctorId, Pageable pageable);
    Page findByClientIdAndDoctorIdAndDateSchedule(Long clientId, Long doctorId, Date date, Pageable pageable);
    Page findByClientIdAndDateSchedule(Long id, Date date, Pageable pageable);
    Page findByDoctorIdAndDateSchedule(Long id, Date date, Pageable pageable);
    Page findByDoctor_Specialization(String specialization, Pageable pageable);
    Page findByDoctor_SpecializationAndDateSchedule(String specialization, Date date, Pageable pageable);
    Page findByDoctor_SpecializationAndClient_IdAndDateSchedule(String specialization, Long id, Date date, Pageable pageable);






}
