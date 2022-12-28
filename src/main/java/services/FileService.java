package services;

import dao.FileDAO;
import entities.File;

public class FileService {

    private FileDAO fileDAO;

    public FileService() {
        fileDAO = new FileDAO();
    }

    public File addFile(File file) {
        if(this.fileDAO.addFile(file)) return file;
        return null;
    }

    public File getFileByField(String field, String Value) {
        return this.fileDAO.getFileByField(field, Value);
    }


}
