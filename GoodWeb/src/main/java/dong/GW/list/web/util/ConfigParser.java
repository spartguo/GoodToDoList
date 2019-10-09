package dong.GW.list.web.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件类
 */
public class ConfigParser {

    private static Logger logger = Logger.getLogger(ConfigParser.class);

    private static Properties properties = new Properties();

    static {
        InputStream input = null;
        try {
            input = ConfigParser.class.getClassLoader().getResourceAsStream("config.properties");
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            logger.error("=============读取配置常量失败===============");
            e.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("=============关闭读取流失败=============");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据key获取对应配置文件中的值
     * @param key
     * @return
     */
    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}
