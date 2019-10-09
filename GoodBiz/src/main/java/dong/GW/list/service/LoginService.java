package dong.GW.list.service;

import dong.GW.list.Dao.Entity.Client;

public interface LoginService {

     Client login(String account, String password)throws Exception;
     Client sign(String account,String password)throws Exception;
}
