package dong.GW.list.Controller.Global;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TokenManagerImpl implements TokenManager {

    private static Map<String, Date> tokenMap = new ConcurrentHashMap<>(256);

    /**
     * 登录
     * @return
     */
    @Override
    public String createToken() {
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, new Date());
        return token;
    }

    /**
     * 检查登录状态
     * @param token
     * @return
     */
    @Override
    public boolean checkToken(String token) {
        if (!StringUtils.isEmpty(token) && tokenMap.containsKey(token)) {
            Date validDate = tokenMap.get(token);
            if (validDate.after(new Date())) {
                return true;
            }
            this.deleteToken(token);
        }
        return false;
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
