package drwho.controllers;

import drwho.models.AppointmentBook;
import drwho.services.AppointmentBookServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import drwho.exception.DataFormatException;

@Controller
public class AppointmentBookController extends AbstractRestHandler {

    @Autowired
    private AppointmentBookServices appointmentBookServices;

    //Create Appointment Book
    @RequestMapping(value ="/v1/appointmentBook/create", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentBook create(@RequestBody AppointmentBook appointmentBook, HttpServletRequest request, HttpServletResponse response) {
        AppointmentBook createdAppointmentBook;
        System.out.print(appointmentBook);
        createdAppointmentBook = this.appointmentBookServices.createAppointmentBook(appointmentBook);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdAppointmentBook.getId()).toString());
        return createdAppointmentBook;
    }

    //Update Appointment Book
    @RequestMapping(value = "/v1/appointmentBook/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AppointmentBook updateAppointmentBook(Long id, @RequestBody AppointmentBook appointmentBook, HttpServletRequest request, HttpServletResponse response) {
        AppointmentBook checkAppointment = this.appointmentBookServices.getById(appointmentBook.getId());
        checkResourceFound(checkAppointment);
        if(checkAppointment.getId()!= appointmentBook.getId()) throw new DataFormatException("ID doesn't match!");
        AppointmentBook updatedAppointmentBook = this.appointmentBookServices.updateAppointmentBook(appointmentBook);
        return updatedAppointmentBook;
    }

    //retrieve user all Appointment Books
    @RequestMapping(value="/v1/appointmentBook/retrieveAllAppointmentBooks", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<AppointmentBook> getAllAppointmentBooks(Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.appointmentBookServices.getAllAppointmentBooks(page, size);
    }

    //retrieve user by id
    @RequestMapping(value="/v1/appointmentBook/retrieveById", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public AppointmentBook getById(long id) {
        AppointmentBook appointmentBook = this.appointmentBookServices.getById(id);
        checkResourceFound(appointmentBook);
        return appointmentBook;
    }

}
