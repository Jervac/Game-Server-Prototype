package Visionire.Framework.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;

/**
 *
 * @author nickbabenko
 */
public class UserObject {
    protected DBObject record;
    protected int objectID;
    protected int userID;
    protected String userName;
    protected String userType;

    public UserObject(DBObject record, int userID, String userName, String userType) {
        this.record = record;

        this.userID = userID;
        this.userName = userName;
        this.userType = userType;
    }

    /**
     * GET/SET METHODS
     */

    public ObjectId recordID() {
        return (ObjectId) record.get("_id");
    }

    public int userID() {
        return userID;
    }

    public void userID(int userID) {
        this.userID = userID;
    }

    public String userName() {
        return userName;
    }

    public void userName(String userName) {
        this.userName = userName;
    }

    public String userType() {
        return userType;
    }

    public UserType type() {
        if (userType().equals("unauthenticated")) return UserType.UNAUTHENTICATED;
	if (userType().equals("user")) return UserType.USER;
	if (userType().equals("editor")) return UserType.EDITOR;
	if (userType().equals("developer")) return UserType.DEVELOPER;
	if (userType().equals("admin")) return UserType.ADMIN;

	return UserType.UNAUTHENTICATED;
    }

    /**
     * TRANSFORM METHODS
     */

    public JsonObject toJsonObject() {
        JsonObject user = new JsonObject();
        JsonArray user_data = new JsonArray();

        user_data.add(new JsonPrimitive(userID));
        user_data.add(new JsonPrimitive(userName));
        user.add("user", user_data);

        return user;
    }

    public static enum UserType {
        UNAUTHENTICATED,
        USER,
        EDITOR,
        DEVELOPER,
        ADMIN;
    }

}
