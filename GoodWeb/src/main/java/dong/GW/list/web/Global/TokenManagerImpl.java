package dong.GW.list.web.Global;

import dong.GW.list.web.Common.TokenInfo;
import dong.GW.list.web.util.ConfigParser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TokenManagerImpl implements TokenManager {

    private final static Logger logger = Logger.getLogger(TokenManager.class);

    private final static long validRange = Long.parseLong(ConfigParser.getProperties("token.valid.time"));

    private static Map<String, TokenInfo> tokenMap = new ConcurrentHashMap<>(256);

    private static Map<String, String> accMap = new ConcurrentHashMap<>(256);

    /**
     * 登录
     *
     * @param account
     * @return
     */
    @Override
    public String createToken(String account) {
        String token;
        if (StringUtils.isEmpty(account)) {
            logger.error("============账号为空=============");
            throw new NullPointerException("账号为空");
        }
        // 将已有的账号信息删掉，如果以登陆
        if (accMap.containsKey(account)) {
            token = accMap.get(account);
            tokenMap.remove(token);
            accMap.remove(account);
        }

        // 生成新的token
        token = UUID.randomUUID().toString();
        long time = System.currentTimeMillis() + validRange;
        tokenMap.put(token, new TokenInfo(account, new Date(time)));
        accMap.put(account,token);

        return token;
    }

    /**
     * 检查登录状态
     *
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
     *
     * @param token
     */
    @Override
    public void deleteToken(String token) {
        TokenInfo tokenInfo = tokenMap.remove(token);
        accMap.remove(tokenInfo.getAccount());
    }
}
