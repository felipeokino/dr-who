package drwho.controllers;

import drwho.exception.ForbiddenException;
import drwho.models.Doctor;
import drwho.services.DoctorServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import drwho.exception.DataFormatException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

@Controller
public class DoctorController extends AbstractRestHandler {

    @Autowired
    private DoctorServices doctorServices;

    //Create doctor register
    @RequestMapping(value ="/v1/doctor/create", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor create(@RequestBody Doctor doctor, HttpServletRequest request, HttpServletResponse response) {
        Doctor createdDoctor;
        Doctor doctorVerified = this.doctorServices.getByAppointmentBookId(doctor.getAppointmentBook().getId());
        if(doctorVerified != null){
            throw new ForbiddenException("Cannot do it dude");
        } else {
            createdDoctor = this.doctorServices.createDoctor(doctor);
        }
        response.setHeader("Location", request.getRequestURL().append("/").append(createdDoctor.getId()).toString());
        return createdDoctor;
    }

    //Update Doctor
    @RequestMapping(value = "/v1/doctor/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Doctor updateDoctor(Long id, @RequestBody Doctor doctor, HttpServletRequest request, HttpServletResponse response) {
        Doctor checkDoctor = this.doctorServices.getById(doctor.getId());
        checkResourceFound(checkDoctor);
        if(checkDoctor.getId()!= doctor.getId()) throw new DataFormatException("ID doesn't match!");
        Doctor updatedDoctor = this.doctorServices.updateDoctor(doctor);
        return updatedDoctor;
    }

    //retrieve user all Doctors
    @RequestMapping(value="/v1/doctor/retrieveAllDoctors", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<Doctor> getAllDoctors(Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.doctorServices.getAllDoctors(page, size);
    }

    //Retrieve a specific doctor
    @RequestMapping(value="/v1/doctor/retrieveById", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public Doctor getById(long id) {
        Doctor doctor = this.doctorServices.getById(id);
        checkResourceFound(doctor);
        return doctor;
    }

    //Retrieve a doctor by his cpf
    @RequestMapping(value="/v1/doctor/retrieveByCpf", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public Doctor getByCpf(String cpf) {
        Doctor doctor = this.doctorServices.getByCpf(cpf);
        checkResourceFound(doctor);
        return doctor;
    }

    //retrieve user by email
    @RequestMapping(value="/v1/doctor/retrieveByEmail", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<Doctor> getByEmail(String email, Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.doctorServices.getByEmail(email, page, size);
    }

    //retrieve user by specialization
    @RequestMapping(value="/v1/doctor/retrieveBySpecialization", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<Doctor> getBySpecialization(String specialization, Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.doctorServices.getBySpecialization(specialization, page, size);
    }

    //Retrieve a doctor by his appointment book id
    @RequestMapping(value="/v1/doctor/retrieveByAppointmentBookId", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public Doctor getByAppointmentBookId(long id) {
        Doctor doctor = this.doctorServices.getByAppointmentBookId(id);
        checkResourceFound(doctor);
        return doctor;
    }
}
