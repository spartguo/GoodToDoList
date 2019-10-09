package dong.GW.list.web.Global;

import dong.GW.list.web.Common.TokenInfo;
import dong.GW.list.web.util.ConfigParser;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TokenManagerImpl implements TokenManager {

    private final static long validRange = Long.parseLong(ConfigParser.getProperties("token.valid.time"));

    private static Map<String, TokenInfo> tokenMap = new ConcurrentHashMap<>(256);

    /**
     * 登录
     * @param account
     * @return
     */
    @Override
    public String createToken(String account) {
        String token = UUID.randomUUID().toString();
        long time = System.currentTimeMillis() + validRange;
        tokenMap.put(token, new TokenInfo(account,new Date(time)));
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
            TokenInfo tokenInfo = tokenMap.get(token);
            if (tokenInfo.getValidDate().after(new Date())) {
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
