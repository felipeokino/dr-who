package drwho.services;

import drwho.models.AppointmentBook;
import drwho.dao.AppointmentBookDao;
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
public class AppointmentBookServices {

    private static final  Logger log = LoggerFactory.getLogger(AppointmentBookServices.class);

    @Autowired
    private AppointmentBookDao appointmentBookDao;

    @Qualifier("counterService")
    @Autowired
    CounterService counterService;

    @Qualifier("gaugeService")
    @Autowired
    GaugeService gaugeService;

    public AppointmentBookServices() {
    }

    public AppointmentBook createAppointmentBook(AppointmentBook appointmentBook){
        return appointmentBookDao.save(appointmentBook);
    }

    public  AppointmentBook updateAppointmentBook(AppointmentBook appointmentBook){
        return appointmentBookDao.save(appointmentBook);
    }

    public AppointmentBook getById(Long id){
        return appointmentBookDao.findOne(id);
    }

    public Page<AppointmentBook> getAllAppointmentBooks(Integer page, Integer size) {
        Page pageOfAppointmentBooks = appointmentBookDao.findAll(new PageRequest(page,size));
        return pageOfAppointmentBooks;
    }

}
