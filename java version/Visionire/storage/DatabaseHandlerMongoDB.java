package Visionire.storage;

//import akka.actor.TypedActor;
import Visionire.Config;
import Visionire.Utility.Log;
import com.google.gson.JsonObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author nickbabenko
 */
public class DatabaseHandlerMongoDB {
    protected Mongo mongo;
    protected DB db;
    protected String hostname = "localhost";
    protected int port = 27017;
    protected String database = "";

    public DatabaseHandlerMongoDB(String ref) {
        JsonObject config = Config.get("database." + ref);

        if (!config.get("hostname").isJsonNull()) {
            hostname = config.get("hostname").getAsString();
        }

        if (!config.get("port").isJsonNull()) {
            port = config.get("port").getAsInt();
        }

        if (!config.get("database").isJsonNull()) {
            database = config.get("database").getAsString();
        }
    }

    public DatabaseHandlerMongoDB connect() {
        try {
            mongo = new Mongo(hostname, port);
        }
        catch (MongoException e) {
            Log.out(Log.ERROR, "Unable to find MongoDB Host", this);
            Log.out(Log.ERROR, "MongoDB Connection error: " + e.getMessage(), this);
        }

        db = mongo.getDB(database);

        // @TODO: Authentication:

        /*if (!config.get("authentication").isJsonNull()) {
            JsonObject authentication = config.get("authentication").getAsJsonObject();

            if (!authentication.get("username").isJsonNull() && !authentication.get("password").isJsonNull()) {
                if (db.authenticate(authentication.get("username").getAsString(), authentication.get("password").getAsString()))
            }
        }*/

        Log.out(Log.INFO, "Database is now connected.", this);
        return this;
    }

    protected DBCollection getCollection(String name) {
        return db.getCollection(name);
    }

    public void close() { }

    public long count(String collection) {
        return count(collection, new HashMap<String, Object>());
    }

    public long count(String collection, HashMap<String, Object> query) {
        DBCollection _collection = getCollection(collection);

        return _collection.getCount(string_object_to_dbobject(query));
    }

    public Iterator<DBObject> find(String collection) {
        return find(collection, new HashMap<String, Object>(), new ArrayList<String>(), 0, 0, 0);
    }

    public Iterator<DBObject> find(String collection, HashMap<String, Object> query) {
        return find(collection, query, new ArrayList<String>(), 0, 0, 0);
    }

    public Iterator<DBObject> find(String collection, HashMap<String, Object> query, ArrayList<String> fields) {
        return find(collection, query, fields, 0, 0, 0);
    }

    public Iterator<DBObject> find(String collection, HashMap<String, Object> query, ArrayList<String> fields, int skip, int limit, int options) {
        DBCollection _collection = this.getCollection(collection);

        BasicDBObject _query = string_object_to_dbobject(query);
        BasicDBObject _fields = new BasicDBObject();
        Iterator<String> fields_iterator = fields.iterator();

        while (fields_iterator.hasNext()) {
            _fields.put(fields_iterator.next(), 1);
        }

        return _collection.find(_query, _fields, skip, limit, options);
    }

    public DBObject find_one(String collection) {
        return find_one(collection, new HashMap<String, Object>(), new ArrayList<String>());
    }

    public DBObject find_one(String collection, HashMap<String, Object> query) {
        return find_one(collection, query, new ArrayList<String>());
    }

    public DBObject find_one(String collection, HashMap<String, Object> query, ArrayList<String> fields) {
        DBCollection _collection = this.getCollection(collection);

        BasicDBObject _query = string_object_to_dbobject(query);
        BasicDBObject _fields = new BasicDBObject();

        Iterator<String> fields_iterator = fields.iterator();

        while (fields_iterator.hasNext()) {
            _fields.put(fields_iterator.next(), 1);
        }

        return _collection.findOne(_query, _fields);
    }

    public ObjectId insert(String collection, HashMap<String, Object> data) {
        DBCollection _collection = this.getCollection(collection);
        BasicDBObject _data = string_object_to_dbobject(data);

        _collection.insert(_data);

        return (ObjectId) _data.get("_id");
    }

    public void drop(String collection) {
		DBCollection _collection = this.getCollection(collection);

        _collection.drop();
    }

    public boolean remove(String collection, HashMap<String, Object> query) {
        DBCollection _collection = this.getCollection(collection);
        BasicDBObject _query = string_object_to_dbobject(query);

        _collection.remove(_query);
        return true;
    }

    protected BasicDBObject string_object_to_dbobject(HashMap<String, Object> data) {
        BasicDBObject dbobject = new BasicDBObject();
        Iterator<String> query_iterator = data.keySet().iterator();

        while (query_iterator.hasNext()) {
            String key = query_iterator.next();
            String type = data.get(key).getClass().getName();

            if(type.equals("java.util.HashMap")) dbobject.put(key, string_object_to_dbobject((HashMap<String, Object>) data.get(key)));
            else dbobject.put(key, data.get(key));
        }

        return dbobject;

    }
}