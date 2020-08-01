
package Visionire;

import Visionire.Utility.Configurations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;

public class Configurations {

    private Double version;
    private String released;
    private Connection connection;
    private List<User> users = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double Version() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public String Released() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Connection Connection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<User> Users() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return format("Version: %s\n", version) +
                format("Released: %s\n", released) +
                format("Storage Database Address: %s\n", connection.Database()) +
                format("Supported Protocol(s): %s\n", connection.Protocol()) +
                format("User(s): %s\n", users);
    }

    //@Override
    //public String toString() {
    //    return ""; //new ToStringBuilder(this).append("version", version).append("released", released).append("connection", connection).append("users", users).append("additionalProperties", additionalProperties).toString();
    //}

}
