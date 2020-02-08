package com.example.backen.entity;

public class UserEntity {
    private int id;
    private String PhotoPath;
    private String name;
    private String id_card_number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        PhotoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", PhotoPath='" + PhotoPath + '\'' +
                ", name='" + name + '\'' +
                ", id_card_number='" + id_card_number + '\'' +
                '}';
    }
}
