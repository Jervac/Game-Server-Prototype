
package Visionire.Utility.Configurations;

import java.util.HashMap;
import java.util.Map;


public class Database {

    private String url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String Url() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        //new ToStringBuilder(this).append("url", url).append("additionalProperties", additionalProperties).toString();
        return String.format("'%s'", url);
    }

}
