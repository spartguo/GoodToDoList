import dong.GW.list.Entity.Client;
import dong.GW.list.Entity.ClientExample;
import dong.GW.list.Mapper.ClientMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-biz.xml","classpath:spring-dao.xml"})
public class ClientServiceImplTest {

    @Autowired
    private ClientMapper clientMapper;

    @Test
    public void test() {
        Client cc = new Client();
        cc.setId(1);
        ClientExample ce = new ClientExample();
        clientMapper.selectByPrimaryKey(1);
//        System.out.println(cc.getNickname());
    }

}
