package org.bszabat.mgrbackend.model;

public class AlbumDto {

    private Integer albumId;
    private String description;

    public AlbumDto() {
    }

    public AlbumDto(Integer albumId, String description) {
        this.albumId = albumId;
        this.description = description;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public String getDescription() {
        return description;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
