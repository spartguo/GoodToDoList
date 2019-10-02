package dong.GW.list;

import dong.GW.list.Dao.ClientMapper;
import dong.GW.list.Entity.Client;
import dong.GW.list.Entity.ClientExample;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl {

    @Autowired
    ClientMapper clientMapper;

    @Test
    public void hh() {
        Client cc = new Client();
        ClientExample ce = new ClientExample();
        clientMapper.selectByExample(ce);
//        System.out.println(cc.getNickname());
    }
}
