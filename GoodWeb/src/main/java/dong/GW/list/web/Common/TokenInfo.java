package dong.GW.list.web.Common;

import java.util.Date;

/**
 * 用来存储账号的token有效期的类
 */
public class TokenInfo {

    /**
     * 账号
     */
    private String account;

    /**
     * 有效期限
     */
    private Date validDate;

    public TokenInfo(String account,Date validDate) {
        this.account = account;
        this.validDate = validDate;
    }

    public String getAccount() {
        return account;
    }

    public Date getValidDate() {
        return validDate;
    }
}
