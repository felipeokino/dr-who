package drwho.services;

import drwho.models.AppointmentSchedule;
import drwho.dao.AppointmentScheduleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

//import java.sql.Time;
import java.util.Date;

@Service
public class AppointmentScheduleServices {

    private static final  Logger log = LoggerFactory.getLogger(AppointmentScheduleServices.class);

    @Autowired
    private AppointmentScheduleDao appointmentScheduleDao;

    @Qualifier("counterService")
    @Autowired
    CounterService counterService;

    @Qualifier("gaugeService")
    @Autowired
    GaugeService gaugeService;

    public AppointmentScheduleServices() {
    }

    public AppointmentSchedule createAppointmentSchedule(AppointmentSchedule appointmentSchedule){
        return appointmentScheduleDao.save(appointmentSchedule);
    }

    public  AppointmentSchedule updateAppointmentSchedule(AppointmentSchedule appointmentBook){
        return appointmentScheduleDao.save(appointmentBook);
    }

    public AppointmentSchedule getById(Long id){
        return appointmentScheduleDao.findOne(id);
    }

    public Page<AppointmentSchedule> getAllAppointmentSchedules(Integer page, Integer size) {
        Page pageOfAppointmentSchedules = appointmentScheduleDao.findAll(new PageRequest(page,size));
        return pageOfAppointmentSchedules;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByClientId(Long id, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByClient = appointmentScheduleDao.findByClientId(id, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByClient;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByDoctorId(Long id, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByDoctor = appointmentScheduleDao.findByDoctorId(id, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByDoctor;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByDateSchedule(Date date, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByDateSchedule = appointmentScheduleDao.findByDateSchedule(date, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByDateSchedule;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByClientIdAndDoctorId(Long clientId, Long doctorId, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByClientIdAndDoctorId = appointmentScheduleDao.findByClientIdAndDoctorId(clientId, doctorId, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByClientIdAndDoctorId;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByClientIdAndDoctorIdAndDateSchedule(Long clientId, Long doctorId, Date date, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByClientIdAndDoctorIdAndDateSchedule = appointmentScheduleDao.findByClientIdAndDoctorIdAndDateSchedule(clientId, doctorId, date, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByClientIdAndDoctorIdAndDateSchedule;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByClientIdAndDateSchedule(Long clientId, Date date, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByClientIdAndDateSchedule = appointmentScheduleDao.findByClientIdAndDateSchedule(clientId, date, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByClientIdAndDateSchedule;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByDoctorIdAndDateSchedule(Long doctorId, Date date, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByDoctorIdAndDateSchedule = appointmentScheduleDao.findByDoctorIdAndDateSchedule(doctorId, date, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByDoctorIdAndDateSchedule;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByDoctorSpecialization(String specialization, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByDoctorSpecialization = appointmentScheduleDao.findByDoctor_Specialization(specialization, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByDoctorSpecialization;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByDoctorSpecializationAndDateSchedule(String specialization, Date date, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByDoctorSpecializationAndDateSchedule = appointmentScheduleDao.findByDoctor_SpecializationAndDateSchedule(specialization, date, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByDoctorSpecializationAndDateSchedule;
    }

    public Page<AppointmentSchedule> getAppointmentSchedulesByClientIdAndDoctorSpecializationAndDateSchedule(Long clientId, String specialization, Date date, Integer page, Integer size) {
        Page pageOfAppointmentSchedulesByClientIdAndDoctorSpecializationAndDateSchedule = appointmentScheduleDao.findByDoctor_SpecializationAndClient_IdAndDateSchedule(specialization, clientId, date, new PageRequest(page,size));
        return pageOfAppointmentSchedulesByClientIdAndDoctorSpecializationAndDateSchedule;
    }

    public AppointmentSchedule getByAllParamters(Long doctorId, Date date, Date startTimeScheduled) {
        return appointmentScheduleDao.findByDoctorIdAndDateScheduleAndStartTimeScheduled(doctorId, date, startTimeScheduled);
    }
}
