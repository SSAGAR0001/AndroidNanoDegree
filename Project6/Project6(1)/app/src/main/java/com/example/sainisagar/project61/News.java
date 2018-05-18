package com.example.sainisagar.project61;

/**
 * Created by Saini Sagar on 2017-12-21.
 */
import java.util.Date;

public class News {
    private String Title;
    private String Desc;
    private String Category;
    private Date PublishDate;
    private String Author;
    private String Link;
    public News(String title, String desc, String category, Date publish, String author, String link) {
        this.Title = title;
        this.Desc = desc;
        this.Category = category;
        this.PublishDate = publish;
        this.Author = author;
        this.Link = link;
    }
    public String getTitle() {
        return this.Title;
    }
    public String getDesc() {
        return this.Desc;
    }
    public String getCategory() {
        return this.Category;
    }
    public Date getPublishDate() {
        return this.PublishDate;
    }
    public String getAuthor() {
        return this.Author;
    }
    public String getLink() {
        return this.Link;
    }
}
