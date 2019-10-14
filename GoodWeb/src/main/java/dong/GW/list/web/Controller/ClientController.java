package dong.GW.list.web.Controller;

import dong.GW.list.Dao.Entity.Client;
import dong.GW.list.service.ClientService;
import dong.GW.list.web.Common.Response;
import dong.GW.list.web.Common.TokenInfo;
import dong.GW.list.web.Dto.ClientInDto;
import dong.GW.list.web.Dto.ClientOutDto;
import dong.GW.list.web.Global.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    TokenManager tokenManager;
    /**
     * 修改个人信息API
     *
     * @param inDto
     * @return
     */

    @RequestMapping(value = "/ClientInfo", method = RequestMethod.POST)
    public Response editClientInfo(@RequestBody ClientInDto inDto,HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        TokenInfo tokenInfo = tokenManager.getAccountByToken(token);  //通过token得到account

        Client c = new Client();
        c.setAccount(tokenInfo.getAccount());
        c.setNickname(inDto.getNickname());
        clientService.editInfoByAccount(c);
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
    public Response getClientInfo(@RequestBody ClientInDto inDto, HttpServletRequest request) throws Exception {

        //通过token得到account
        String token = request.getHeader("token");
        TokenInfo tokenInfo = tokenManager.getAccountByToken(token);

        Client c = new Client();
        c.setAccount(tokenInfo.getAccount());
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
