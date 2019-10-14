package dong.GW.list.service.serviceimpl;

import dong.GW.list.Dao.Entity.Client;
import dong.GW.list.Dao.Entity.ClientExample;
import dong.GW.list.Dao.Mapper.ClientMapper;
import dong.GW.list.service.AdminService;
import org.junit.gen5.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ClientMapper clientMapper;

    /**
     * 管理员修改用户权限
     * @param inDto
     */

    public void editClientById(Client inDto) {

    }


    /**
     * 管理员获取用户列表
     * @param inDto
     * @return
     */
    public List<Client> getClientList(Client inDto) {
        //构造匹配条件
        ClientExample clientExample = new ClientExample();
        ClientExample.Criteria criteria = clientExample.createCriteria();
        if (StringUtils.isNotEmpty(inDto.getAccount())){
            criteria.andAccountLike(inDto.getAccount());
        }
        if (StringUtils.isNotEmpty(inDto.getNickname())){
            criteria.andNicknameLike(inDto.getNickname());
        }
        if (inDto.getId() != null){
            criteria.andIdEqualTo(inDto.getId());
        }
        if (inDto.getRoles() != null){
            criteria.andRolesEqualTo(inDto.getRoles());
        }

        return clientMapper.selectByExample(clientExample);
    }
}
