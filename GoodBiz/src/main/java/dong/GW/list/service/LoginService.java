package dong.GW.list.Controller.Global;

import dong.GW.list.Dao.Entity.Client;

public interface LoginService {

    public Client login(String account, String password);
}
