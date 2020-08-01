
package Visionire.Utility.Configurations;

import java.util.HashMap;
import java.util.Map;


public class User {

    private String user;
    private String password;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String User() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String Password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        //return new ToStringBuilder(this).append("user", user).append("password", password).append("additionalProperties", additionalProperties).toString();
        return String.format("user: '%s', password: '%s'", user, password);//, getAdditionalProperties().toString());
    }

}