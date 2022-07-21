package com.database.database.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
//defining table
@Data
@AllArgsConstructor
@Entity
@Table(name = "tutorial")
@NoArgsConstructor
public class TutorialModel {
    @Id
    //for private key
    @GeneratedValue(strategy = GenerationType.AUTO)
    //for generating auto incremented id
    private long id;
    //giving column name
    @Column(name="title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "published")
    private boolean published;
    public TutorialModel(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    //making a default constructor
    //making getter and setter
    //shortcut alt+insert

}
