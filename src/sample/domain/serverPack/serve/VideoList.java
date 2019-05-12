package sample.domain.serverPack.serve;

public class VideoList {
    private String dvdNumber;
    private String title;
    private String category;

    public VideoList() {
    }

    public VideoList(String dvdNumber, String title, String category) {
        this.dvdNumber = dvdNumber;
        this.title = title;
        this.category = category;
    }

    public String getDvdNumber() {
        return dvdNumber;
    }

    public void setDvdNumber(String dvdNumber) {
        this.dvdNumber = dvdNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}