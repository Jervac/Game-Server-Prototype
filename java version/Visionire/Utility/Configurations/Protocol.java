
package Visionire.Utility.Configurations;

import java.util.HashMap;
import java.util.Map;


public class Protocol {

    private Integer tcp;
    private Integer udp;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer Tcp() {
        return tcp;
    }

    public void setTcp(Integer tcp) {
        this.tcp = tcp;
    }

    public Integer Udp() {
        return udp;
    }

    public void setUdp(Integer udp) {
        this.udp = udp;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        //new ToStringBuilder(this).append("tcp", tcp).append("udp", udp).append("additionalProperties", additionalProperties).toString();
        return String.format("tcp: '%s', udp: '%d'", tcp, udp);
    }

}
