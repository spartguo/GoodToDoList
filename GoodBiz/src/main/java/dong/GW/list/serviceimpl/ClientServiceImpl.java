package dong.GW.list.serviceimpl;

import dong.GW.list.Entity.Client;
import dong.GW.list.Entity.ClientExample;
import dong.GW.list.Mapper.ClientMapper;
import dong.GW.list.service.ClientService;
import javafx.scene.shape.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-biz.xml"})
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientMapper clientMapper;

    @Test
    public void test() {
        Client cc = new Client();
        cc.setId(2);
        ClientExample ce = new ClientExample();
        clientMapper.selectByPrimaryKey(cc.getId());
//        System.out.println(cc.getNickname());
    }

    public void editinfo(Client client) {

    }

}
