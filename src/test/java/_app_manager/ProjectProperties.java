package _app_manager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {
    private final Properties properties;


    public ProjectProperties() throws IOException {
        this.properties = new Properties();
        callProperties();
    }

    public void callProperties() throws IOException {
        String target = System.getProperty("target", "prod");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }


    public String getBaseUrl() {
        return properties.getProperty("web.baseUrl");
    }
}
