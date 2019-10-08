package dong.GW.list.Controller;

import dong.GW.list.Entity.Client;
import dong.GW.list.service.Global.Response;
import dong.GW.list.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/client",method = RequestMethod.PUT)
    public Response editclientinfo(@RequestBody @Valid Client client){
        return new Response().success(client);
    }

}
