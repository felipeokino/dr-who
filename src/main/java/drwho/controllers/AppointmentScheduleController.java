package drwho.controllers;

import drwho.models.AppointmentSchedule;
import drwho.services.AppointmentScheduleServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import drwho.exception.DataFormatException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppointmentScheduleController extends AbstractRestHandler {

    @Autowired
    private AppointmentScheduleServices appointmentScheduleServices;

    //Create Appointment Schedule
    @RequestMapping(value ="/v1/appointmentSchedule/create", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentSchedule create(@RequestBody AppointmentSchedule appointmentSchedule, HttpServletRequest request, HttpServletResponse response) {
        AppointmentSchedule createdAppointmentSchedule;
        createdAppointmentSchedule = this.appointmentScheduleServices.createAppointmentSchedule(appointmentSchedule);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdAppointmentSchedule.getId()).toString());
        return createdAppointmentSchedule;
    }

    //Update Appointment Schedule
    @RequestMapping(value = "/v1/appointmentSchedule/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AppointmentSchedule updateAppointmentSchedule(Long id, @RequestBody AppointmentSchedule appointmentSchedule, HttpServletRequest request, HttpServletResponse response) {
        AppointmentSchedule checkSchedule = this.appointmentScheduleServices.getById(appointmentSchedule.getId());
        checkResourceFound(checkSchedule);
        if(checkSchedule.getId()!= appointmentSchedule.getId()) throw new DataFormatException("ID doesn't match!");
        AppointmentSchedule updatedAppointmentSchedule = this.appointmentScheduleServices.updateAppointmentSchedule(appointmentSchedule);
        return updatedAppointmentSchedule;
    }

    //retrieve all appointment schedules
    @RequestMapping(value="/v1/appointmentSchedule/retrieveAllAppointmentSchedules", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<AppointmentSchedule> getAllAppointmentSchedules(Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.appointmentScheduleServices.getAllAppointmentSchedules(page, size);
    }

    //retrieve appointment schedule by id
    @RequestMapping(value="/v1/appointmentSchedule/retrieveById", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public AppointmentSchedule getById(long id) {
        AppointmentSchedule appointmentSchedule = this.appointmentScheduleServices.getById(id);
        checkResourceFound(appointmentSchedule);
        return appointmentSchedule;
    }

    //retrieve appointment schedule by user
    @RequestMapping(value="/v1/appointmentSchedule/retrieveByUserId", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<AppointmentSchedule> getByUserId(Long id, Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.appointmentScheduleServices.getAppointmentSchedulesByClientId(id, page, size);
    }

    //retrieve appointment schedule by doctor
    @RequestMapping(value="/v1/appointmentSchedule/retrieveByDoctorId", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<AppointmentSchedule> getByDoctorId(Long id, Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.appointmentScheduleServices.getAppointmentSchedulesByDoctorId(id, page, size);
    }

    @RequestMapping(value="/v1/appointmentSchedule/retrieveByDateSchedule", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<AppointmentSchedule> getByDateSchedule(String date, Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = null;
        try {
            Date date1 = formatter.parse(date);
            date2 = date1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return this.appointmentScheduleServices.getAppointmentSchedulesByDateSchedule(date2, page, size);
    }

    @RequestMapping(value="/v1/appointmentSchedule/retrieveByClientIdAndDoctorId", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<AppointmentSchedule> getByClientIdAndDoctorId(Long clientId, Long doctorId, Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.appointmentScheduleServices.getAppointmentSchedulesByClientIdAndDoctorId(clientId, doctorId, page, size);
    }
}
