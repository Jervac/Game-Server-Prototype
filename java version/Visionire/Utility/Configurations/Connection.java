
package Visionire.Utility.Configurations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Connection {

    private Database database;
    private List<Protocol> protocol = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Database Database() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public List<Protocol> Protocol() {
        return protocol;
    }

    public void setProtocol(List<Protocol> protocol) {
        this.protocol = protocol;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        // new ToStringBuilder(this).append("database", database).append("protocol", protocol).append("additionalProperties", additionalProperties).toString();
        return "database";
    }

}
