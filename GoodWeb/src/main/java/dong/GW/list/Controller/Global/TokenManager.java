package dong.GW.list.Controller.Global;

public interface TokenManager {

    String createToken();

    boolean checkToken(String token);

    public void deleteToken(String token);
}
