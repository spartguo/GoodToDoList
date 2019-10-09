package dong.GW.list.web.Controller;

import dong.GW.list.web.Dto.ClientInDto;
import dong.GW.list.web.Dto.ClientOutDto;
import dong.GW.list.Dao.Entity.Client;
import dong.GW.list.web.Common.Response;
import dong.GW.list.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    /**
     * 修改个人信息API
     *
     * @param inDto
     * @return
     */

    @RequestMapping(value = "/ClientInfo", method = RequestMethod.POST)
    public Response editClientInfo(@RequestBody ClientInDto inDto) throws Exception {
        Client c = new Client();
        c.setId(inDto.getId());
        c.setNickname(inDto.getNickname());
        clientService.editinfo(c);
        return new Response().success("修改成功！");
    }

    /**
     * 获得个人信息
     *
     * @param inDto
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/ClientInfo", method = RequestMethod.GET)
    public Response getClientInfo(@RequestBody ClientInDto inDto) throws Exception {
        Client c = new Client();
        c.setId(inDto.getId());
        return new Response().success(clientToOutDto(clientService.getInfo(c)));
    }

    private ClientOutDto clientToOutDto(Client client) {
        ClientOutDto outDto = new ClientOutDto();
        outDto.setAccount(client.getAccount());
        outDto.setCreatetime(client.getCreatetime());
        outDto.setId(client.getId());
        outDto.setNickname(client.getNickname());
        outDto.setRoles(client.getRoles());
        return outDto;
    }


}
