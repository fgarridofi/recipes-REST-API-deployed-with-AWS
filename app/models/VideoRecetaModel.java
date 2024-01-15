package models;

import io.ebean.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class VideoRecetaModel extends Model {

    @Id
    private Long id;

    private String videoURL;
    @OneToOne(mappedBy = "video")
    private RecetaModel parentReceta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public RecetaModel getParentReceta() {
        return parentReceta;
    }

    public void setParentReceta(RecetaModel parentReceta) {
        this.parentReceta = parentReceta;
    }
}

