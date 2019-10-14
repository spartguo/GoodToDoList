package dong.GW.list.web.Global;

import dong.GW.list.web.Common.TokenInfo;

public interface TokenManager {

    String createToken(String account);

    boolean checkToken(String token);

    void deleteToken(String token);

    TokenInfo getAccountByToken(String token);
}
