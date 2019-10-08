package dong.GW.list.Controller.Global;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TokenManagerImpl implements TokenManager {

    private static Map<String, String> tokenMap = new ConcurrentHashMap<>();


    /**
     * 登录
     * @param username
     * @return
     */
    @Override
    public String createToken(String username) {
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, username);
        return token;
    }

    /**
     * 检查登录状态
     * @param token
     * @return
     */
    @Override
    public boolean checkToken(String token) {
        return !StringUtils.isEmpty(token) && tokenMap.containsKey(token);
    }


    /**
     * 退出登录
     * @param token
     */
    @Override
    public void deleteToken(String token) {
        tokenMap.remove(token);
    }
}
