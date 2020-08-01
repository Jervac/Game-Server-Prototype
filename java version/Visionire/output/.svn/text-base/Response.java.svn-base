package com.visionire.downtown.output;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 *
 * @author nickbabenko
 */
public class Response {
    protected String type = null;
    protected Object data = null;
    protected String handle = "result";

    public Response(ResponseType type) {
        set_type(type);
    }

    public Response(Object data) {
        this.data = data;
    }

    public Response(String custom_handle, Object data) {
        this.handle = custom_handle;
        this.data = data;
    }

    public Response(ResponseType type, Object data) {
        this.data = data;
        set_type(type);
    }

    private void set_type(ResponseType type) {
        if (type == ResponseType.OK) this.type = "ok";
        if (type == ResponseType.ERROR) this.type = "error";
    }

    public String toJSON() {
        JsonObject json = new JsonObject();

        if (data == null) json.addProperty(handle, type);

        else {
            if (data.getClass().getName().equals("java.lang.String")) {
                data = new JsonPrimitive((String)data);
            }

            else if (data.getClass().getName().equals("java.lang.Integer")) {
                data = new JsonPrimitive((Integer)data);
            }

            if (data.getClass().getName().equals("com.google.gson.JsonArray") || data.getClass().getName().equals("com.google.gson.JsonObject") || data.getClass().getName().equals("com.google.gson.JsonPrimitive")) {
                
                if (type == null) json.add(handle, (JsonElement) data);
                else {
                    JsonObject _data = new JsonObject();
                    _data.add(type, (JsonElement) data);
                    json.add(handle, _data);
                }

            }
        }

        return new Gson().toJson(json);

    }

    public enum ResponseType {
        
        OK, ERROR, NOVALUE;
        
        public static ResponseType toType(String string) {
            
            try {
                return valueOf(string);
            }
            
            catch (Exception e) {
                return NOVALUE;
            }

	}
    }
}