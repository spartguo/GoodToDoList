package dong.GW.list.web.Global;

public interface TokenManager {

    String createToken(String account);

    boolean checkToken(String token);

    void deleteToken(String token);
}