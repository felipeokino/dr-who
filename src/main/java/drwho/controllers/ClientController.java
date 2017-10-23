package drwho.controllers;

import drwho.models.Client;
import drwho.services.ClientServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import drwho.exception.DataFormatException;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

@Controller
public class ClientController extends AbstractRestHandler {

    @Autowired
    private ClientServices clientServices;

    //CreateUser
    @RequestMapping(value ="/v1/client/create", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client, HttpServletRequest request, HttpServletResponse response) {
        Client createdClient;
        createdClient = this.clientServices.createClient(client);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdClient.getId()).toString());
        return createdClient;
    }

    //UpdateUser
    @RequestMapping(value = "/v1/client/update", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Client updateClient(Long id, @RequestBody Client client, HttpServletRequest request, HttpServletResponse response) {
        Client checkClient = this.clientServices.getById(client.getId());
        checkResourceFound(checkClient);
        if(checkClient.getId()!= client.getId()) throw new DataFormatException("ID doesn't match!");
        Client updatedClient = this.clientServices.updateClient(client);
        return updatedClient;
    }

    //retrieve user all Clients
    @RequestMapping(value="/v1/client/retrieveAllClients", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<Client> getAllClients(Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.clientServices.getAllClients(page, size);
    }

    //retrieve user by id
    @RequestMapping(value="/v1/client/retrieveById", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public Client getById(long id) {
        Client client = this.clientServices.getById(id);
        checkResourceFound(client);
        return client;
    }

    //retrieve user by cpf
    @RequestMapping(value="/v1/client/retrieveByCpf", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public Client getByCpf(String cpf) {
        Client client = this.clientServices.getByCpf(cpf);
        checkResourceFound(client);
        return client;
    }

    //retrieve user by email
    @RequestMapping(value="/v1/client/retrieveByEmail", method = RequestMethod.GET, produces = {"application/json"})
    public
    @ResponseBody
    Page<Client> getByEmail(String email, Integer page, Integer size, HttpServletRequest request, HttpServletResponse response) {
        return this.clientServices.getByEmail(email, page, size);
    }
}
