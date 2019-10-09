package dong.GW.list.service.serviceimpl;

import dong.GW.list.Dao.Entity.Client;
import dong.GW.list.Dao.Entity.ClientExample;
import dong.GW.list.Dao.Mapper.ClientMapper;
import dong.GW.list.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    ClientMapper clientMapper;

    public Client login(String account, String password) throws Exception{
        ClientExample clientExample = new ClientExample();
        ClientExample.Criteria criteria =  clientExample.createCriteria();

        criteria.andAccountEqualTo(account);
        criteria.andPasswordEqualTo(password);
        List<Client> list = null;

        list = clientMapper.selectByExample(clientExample);
        if (list.size() == 0){
            throw new Exception("账号或密码错误！");
        }
        else {
            return list.get(0);
        }
    }

    public Client sign(String account, String password) throws Exception {
        ClientExample clientExample = new ClientExample();
        ClientExample.Criteria criteria =  clientExample.createCriteria();

        criteria.andAccountEqualTo(account);
        List<Client> list = null;

        list = clientMapper.selectByExample(clientExample);
        if (list.size() != 0){
            throw new Exception("账号已存在！");
        }
        else if (password.length()<8){
            throw new Exception("密码长度需要大于8位！");
        }
        else {
            Client client = new Client();
            client.setAccount(account);
            client.setPassword(password);
            clientMapper.insertSelective(client);
            list = clientMapper.selectByExample(clientExample);
            return list.get(0);
        }
    }
}
