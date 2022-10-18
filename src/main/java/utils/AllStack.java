package utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

public class AllStack {
    public static Properties testProp;
    public static String testEnv;
    public static String Path="src/main/resources/TestConfig/####.properties";

    public static void setTestProp() {
        if (testProp != null) {
            return;
        }

        //   System.out.println(" allStack.getTestEnv() = "+ allStack.getTestEnv() );
        testProp = getSettings(Path.replace("####", getTestEnv()));
    }

    public static String getTestEnv() {
        String env="";

        try {
            String path = System.getProperty("user.dir");
            if (path.contains("jenkins")) {
                env = System.getProperty("environment","prod");
            } else {
                if (System.getProperty("environment").contains("local")) {
                    env =  "local";
                }
                else if (System.getProperty("environment").contains("dev")) {
                    env =  "dev";
                } else if (System.getProperty("environment").contains("prod")) {
                    env =  "prod";
                } else {
                    env =  "qa";
                }
            }
        } catch (Exception e) {
            env =  "prod";
        }
        return env;
    }

    public static String getTestConfigurations(String key) {

        //  System.out.println(" testProp... = "+ testProp);
        //  System.out.println(" testEnv... = "+ testEnv);
        //  System.out.println(" key... = "+ key);
        if (testProp == null || testEnv == null) {
            setTestProp();
        }
        return testProp.getProperty(key).replace(" ", "");
    }

    public static int getTestConfigurationsAsInt(String key) {
        if (testProp == null || testEnv == null) {
            setTestProp();
        }
        return Integer.parseInt(testProp.getProperty(key).replace(" ", ""));
    }

    public static Properties getSettings(String FileName) {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(FileName));
        } catch (Exception e) {
            System.out.println("Some error has occured while loading properties File " + FileName + " " + e);
        }
        return properties;
    }
}