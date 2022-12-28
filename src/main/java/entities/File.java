package entities;

import java.io.Serializable;
import java.util.Date;


public class File implements Serializable {

    private int fileId;
    private String fileMessage;
    private String describedMedic;
    private Date createdAt = new Date();


    public File() {}

    public File(int fileId, String fileMessage, String describedMedic) {
        this.fileId = fileId;
        this.fileMessage = fileMessage;
        this.describedMedic = describedMedic;
    }

    public int getFileId() {
        return fileId;
    }

    public String getFileMessage() {
        return fileMessage;
    }

    public void setFileMessage(String fileMessage) {
        this.fileMessage = fileMessage;
    }



    public String getDescribedMedic() {
        return describedMedic;
    }

    public void setDescribedMedic(String describedMedic) {
        this.describedMedic = describedMedic;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



}
