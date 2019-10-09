package dong.GW.list.web.Controller;

import dong.GW.list.Dao.Entity.Client;
import dong.GW.list.service.AdminService;
import dong.GW.list.web.Common.Response;
import dong.GW.list.web.Dto.ClientInDto;
import dong.GW.list.web.Dto.ClientOutDto;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 管理员获得用户信息
     * @param inDto
     * @param request
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/AdminOperate", method = RequestMethod.POST)
    public Response getClientInfo(@RequestBody ClientInDto inDto, HttpServletRequest request) throws Exception {
        //TODO
        //权限校验未做
        Client client = new Client();
        client.setAccount(inDto.getAccount());
        client.setNickname(inDto.getNickname());
        client.setId(inDto.getId());
        client.setRoles(inDto.getRoles());
        List<ClientOutDto> list = clientListToOutDtoList(adminService.getClientList(client));
        return new Response().success(list);
    }

    private List<ClientOutDto> clientListToOutDtoList(List<Client> clientList){
        List<ClientOutDto> outDtoList = new ArrayList<>();
        for (int i = 0;i < clientList.size();i++){
            ClientOutDto outDto = new ClientOutDto();
            outDto.setAccount(clientList.get(i).getAccount());
            outDto.setCreatetime(clientList.get(i).getCreatetime());
            outDto.setId(clientList.get(i).getId());
            outDto.setNickname(clientList.get(i).getNickname());
            outDto.setRoles(clientList.get(i).getRoles());
            outDtoList.add(outDto);
        }
        return outDtoList;
    }
}
