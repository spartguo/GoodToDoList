package dong.GW.list.service.serviceimpl;

import dong.GW.list.Dao.Entity.Client;
import dong.GW.list.Dao.Entity.ClientExample;
import dong.GW.list.Dao.Mapper.ClientMapper;
import dong.GW.list.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientMapper clientMapper;

    /**
     * 通过账号修改用户信息(主要针对用户)
     * @param inDto
     * @throws Exception
     */

    public void editInfoByAccount(Client inDto) throws Exception {
        Client client = new Client();
        ClientExample clientExample = new ClientExample();
        if (StringUtils.isEmpty(inDto.getNickname())){
            throw new Exception("修改不能为空！");
        }
        client.setNickname(inDto.getNickname());
        ClientExample.Criteria criteria = clientExample.createCriteria();
        criteria.andAccountEqualTo(inDto.getAccount());
        clientMapper.updateByExample(client,clientExample);
    }

    /**
     * 通过账号查找用户信息
     * @param inDto
     * @return
     */

    public Client getInfo(Client inDto) {
        ClientExample clientExample = new ClientExample();
        ClientExample.Criteria criteria = clientExample.createCriteria();
        criteria.andAccountEqualTo(inDto.getAccount());
        List<Client> list = clientMapper.selectByExample(clientExample);
        if (list.size() == 0){
            throw new  NullPointerException("无数据！");
        }
        return list.get(0);
    }
}
