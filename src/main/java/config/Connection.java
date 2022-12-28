package config;

import com.mongodb.client.*;

public class Connection {


    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    //private static String connectionString = System.getProperty("mongodb.uri");
    private static String connectionString = "mongodb+srv://admin:123456Hanafi@gestionhopital.4769qd0.mongodb.net/?retryWrites=true&w=majority";

    static {
        try {
            mongoClient = MongoClients.create(connectionString);
            String database = mongoClient.listDatabaseNames().first();
            System.out.println(database);
            mongoDatabase = mongoClient.getDatabase("Hospital");

        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public static MongoCollection getMongoCollection(String collectionName) {
        return mongoDatabase.getCollection(collectionName);
    }

}