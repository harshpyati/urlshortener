package com.harsh.urlshortener.utils;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Utils {
    private static final String PROPS_FILE = "application.properties";

    public static Properties getApplicationProperties() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream stream = loader.getResourceAsStream(PROPS_FILE)){
            props.load(stream);
        }catch(Exception ex){
            System.out.println("failed to load props file " + PROPS_FILE);
        }
        return props;
    }

    public static boolean isNotNullOrEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
