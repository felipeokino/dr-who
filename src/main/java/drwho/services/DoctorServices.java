package drwho.services;

import drwho.models.Doctor;
import drwho.dao.DoctorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DoctorServices {

    private static final  Logger log = LoggerFactory.getLogger(DoctorServices.class);

    @Autowired
    private  DoctorDao doctorDao;

    @Qualifier("counterService")
    @Autowired
    CounterService counterService;

    @Qualifier("gaugeService")
    @Autowired
    GaugeService gaugeService;

    public DoctorServices() {
    }

    public  Doctor createDoctor(Doctor doctor){
        return doctorDao.save(doctor);
    }

    public  Doctor updateDoctor(Doctor doctor){
        return doctorDao.save(doctor);
    }

    public Doctor getById(Long id){
        return doctorDao.findOne(id);
    }

    public Doctor getByAppointmentBookId(Long id){
        return doctorDao.findByAppointmentBookId(id);
    }

    public Doctor getByCpf(String cpf){
        return doctorDao.findByCpf(cpf);
    }

    public Page<Doctor> getBySpecialization(String specialization, Integer page, Integer size) {
        Page pageOfDoctorsBySpecialization = doctorDao.findBySpecialization(specialization, new PageRequest(page,size));
        return pageOfDoctorsBySpecialization;
    }

    public Page<Doctor> getByEmail(String email, Integer page, Integer size) {
        Page pageOfUsersByEmail = doctorDao.findByEmail(email, new PageRequest(page,size));
        return pageOfUsersByEmail;
    }

    public Page<Doctor> getAllDoctors(Integer page, Integer size) {
        Page pageOfDoctors = doctorDao.findAll(new PageRequest(page,size));
        return pageOfDoctors;
    }

}
