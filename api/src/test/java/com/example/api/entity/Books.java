package com.example.api.entity;

import java.util.Date;
import java.util.Locale.Category;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private Date date;

    @Column(name = "Likes", nullable = false)
    private String content;

   

    @ManyToOne
    @JsonIgnoreProperties("books")
    @JoinColumn(name = "chapitre_id")
    private Category author;

    private Category chapitre;

    private int likes;

    public Category getChapitre() {
    	return this.getChapitre();
    }
    public void setChapitre(Category chapitre) {
    	this.chapitre = chapitre;
    }

    public int getLikes() {
    	return this.getLikes();
    }
    public void setLikes(int likes) {
    	this.likes = likes;
    }

    public String getContent() {
    	return this.content;
    }
    public void setContent(String content) {
    	this.content = content;
    }

    public String getTitle() {
    	return this.title;
    }
    public void setTitle(String titleString) {
    	this.title = title;
    }
	public int getId() {
		return 0;
    }
    public void setId(long id) {
    	this.id = id;
    }
}





   


    