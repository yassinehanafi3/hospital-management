package dao;

import config.ConnectionMongoDB;
import entities.File;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class FileDAO {

    private final MongoCollection mongoCollection = ConnectionMongoDB.getMongoCollection("Files");


    public File findById(long fileId) {
        return (File) this.mongoCollection.find(new Document("fileId", fileId)).first();
    }

    public List<File> findPationFilesByDoctor(String doctorCni, String pationCni) {
        Document query = new Document();
        query.append("doctorCni", doctorCni);
        query.append("pationCni", pationCni);
        List<File> files = new ArrayList<>();
        FindIterable filesDB = this.mongoCollection.find(query);
        for(Object file : filesDB) {
            files.add((File) file);
        }
        return files;
    }

    public List<File> findAllFilesByDoctor(String cni) {
        List<File> files = new ArrayList<>();
        FindIterable filesDB = this.mongoCollection.find(new Document("doctorCni", cni));
        for(Object file : filesDB) {
            files.add((File) file);
        }
        return files;
    }


    private Document CreateFile(File file) {
        Document doc = new Document();
        doc.append("fileId", file.getFileId());
        doc.append("fileMessage", file.getFileMessage());
        doc.append("describedMedic", file.getDescribedMedic());
        doc.append("createdAt", file.getCreatedAt());
        return doc;
    }

    public boolean addFile(File file) {
        this.mongoCollection.insertOne(this.CreateFile(file));
        return true;
    }

    public File getFileByField(String field, String value) {
        return (File) this.mongoCollection.find(new Document(field, value)).first();
    }

}
