package drwho.services;

import drwho.models.Client;
import drwho.dao.ClientDao;
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
public class ClientServices {

    private static final  Logger log = LoggerFactory.getLogger(ClientServices.class);

    @Autowired
    private  ClientDao clientDao;

    @Qualifier("counterService")
    @Autowired
    CounterService counterService;

    @Qualifier("gaugeService")
    @Autowired
    GaugeService gaugeService;

    public ClientServices(){
    }

    public  Client createClient(Client client){
        return clientDao.save(client);
    }

    public  Client updateClient(Client client){
        return clientDao.save(client);
    }

    public Client getById(Long id){
        return clientDao.findOne(id);
    }

    public Client getByCpf(String cpf){
        return clientDao.findByCpf(cpf);
    }

    public Page<Client> getByEmail(String email, Integer page, Integer size) {
        Page pageOfUsersByEmail = clientDao.findByEmail(email, new PageRequest(page,size));
        return pageOfUsersByEmail;
    }

    public Page<Client> getAllClients(Integer page, Integer size) {
        Page pageOfClients = clientDao.findAll(new PageRequest(page,size));
        return pageOfClients;
    }


}
