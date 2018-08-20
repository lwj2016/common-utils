package com.lwj.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by lwj on 2018/7/10.
 * lwjfork@gmail.com
 */

public final class PropertiesUtil {


    /**
     * Gets properties by class loader.
     * Tips: Only support Class Path
     *
     * @param path the path
     * @return the properties by class loader
     * @throws IOException the io exception
     * @author Created by lwjfork on 2018/07/10 17:51
     */
    public static Properties getPropertiesByClassLoader(String path) throws IOException {
        Properties properties = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
        properties.load(in);
        return properties;
    }


    /**
     * Gets properties by class loader.
     * Tips: Only support Class Path
     *
     * @return the properties by class loader
     * @throws IOException the io exception
     * @author Created by lwjfork on 2018/07/10 17:51
     */
    public static Properties getProperties(InputStream in) throws IOException {
        Properties properties = new Properties();
        properties.load(in);
        return properties;
    }


    /**
     * Gets value by class loader.
     *
     * @param path the path
     * @param keys the keys
     * @return the value by class loader
     * @throws IOException the io exception
     * @author Created by liuwenjie on 2018/08/17 11:30
     */
    public static HashMap<String, String> getValueByClassLoader(String path, String... keys) throws IOException {
        Properties properties = getPropertiesByClassLoader(path);

        HashMap<String, String> data = new HashMap<>();
        for (String key : keys) {
            data.put(key, getValue(properties, key));
        }
        return data;
    }


    /**
     * Gets value.
     *
     * @param properties   the properties
     * @param key          the key
     * @param defaultValue the default value
     * @return the value
     * @author Created by liuwenjie on 2018/08/17 11:30
     */
    public static String getValue(Properties properties, String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Gets value.
     *
     * @param properties the properties
     * @param key        the key
     * @return the value
     * @author Created by liuwenjie on 2018/08/17 11:30
     */
    public static String getValue(Properties properties, String key) {
        return properties.getProperty(key, null);
    }

}
