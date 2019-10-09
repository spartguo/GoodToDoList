package dong.GW.list.service.serviceimpl;

import dong.GW.list.Dao.Entity.Client;
import dong.GW.list.Dao.Entity.ClientExample;
import dong.GW.list.Dao.Mapper.ClientMapper;
import dong.GW.list.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientMapper clientMapper;

    public void editinfo(Client inDto) throws Exception {
        Client client = new Client();
        ClientExample clientExample = new ClientExample();
        if (StringUtils.isEmpty(inDto.getNickname())){
            throw new Exception("修改不能为空！");
        }
    }

    public Client getInfo(Client inDto) {
        Client client = new Client();
        return clientMapper.selectByPrimaryKey(inDto.getId());
    }
}
