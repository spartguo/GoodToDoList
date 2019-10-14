package dong.GW.list.service;

import dong.GW.list.Dao.Entity.Client;

import java.util.List;

public interface AdminService {
    void editClientById(Client inDto);

    List<Client> getClientList(Client inDto);


}
