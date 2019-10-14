package dong.GW.list.service;

import dong.GW.list.Dao.Entity.Client;

public interface ClientService {
    void editInfoByAccount(Client client) throws Exception;
    Client getInfo(Client client);
}
