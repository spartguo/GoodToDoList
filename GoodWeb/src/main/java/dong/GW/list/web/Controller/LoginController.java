package dong.GW.list.web.Controller;

import dong.GW.list.web.Dto.LoginInDto;
import dong.GW.list.web.Dto.LoginOutDto;
import dong.GW.list.service.LoginService;
import dong.GW.list.web.Common.Response;
import dong.GW.list.web.Global.TokenManager;
import dong.GW.list.Dao.Entity.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final static Logger logger = Logger.getLogger(TokenManager.class);

    @Autowired
    TokenManager tokenManager;

    @Autowired
    LoginService loginService;

    /**
     * 登录类API，
     * @param inDto
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public Response login(@RequestBody LoginInDto inDto)throws Exception{

        Client client = loginService.login(inDto.getAccount(),inDto.getPassword());

        if(client == null){
            throw new Exception("账号或密码错误！");
        }

        if (StringUtils.isEmpty(inDto.getAccount())) {
            logger.error("============账号为空=============");
            throw new NullPointerException("账号为空");
        }

        //设置登录状态
        String token = tokenManager.createToken(client.getAccount());
        LoginOutDto loginOutDto = clientToOutDto(client);
        loginOutDto.setToken(token);

        return new Response().success(loginOutDto);
    }

    private LoginOutDto clientToOutDto(Client client){
        LoginOutDto loginOutDto = new LoginOutDto();
        loginOutDto.setAccount(client.getAccount());
        loginOutDto.setCreatetime(client.getCreatetime());
        loginOutDto.setId(client.getId());
        loginOutDto.setNickname(client.getNickname());
        loginOutDto.setRoles(client.getRoles());
        return loginOutDto;
    }

    @RequestMapping(value = "/Sign",method = RequestMethod.POST)
    public Response sign(@RequestBody LoginInDto inDto) throws Exception{
        Client client = loginService.sign(inDto.getAccount(),inDto.getPassword());
        return new Response().success("注册成功！");
    }

}
