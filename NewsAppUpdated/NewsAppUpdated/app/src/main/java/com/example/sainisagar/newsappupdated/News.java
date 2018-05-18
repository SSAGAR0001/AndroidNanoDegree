package com.example.sainisagar.newsappupdated;

/**
 * Created by Saini Sagar on 2017-12-26.
 */
import java.util.Date;
public class News {
    private String Title;
    private String Desc;
    private String Category;
    private Date PublishDate;
    private String author;
    private String Link;
    public News(String title, String desc, String category, String author, Date publish, String link) {
        this.Title = title;
        this.Desc = desc;
        this.Category = category;
        this.author = author;
        this.PublishDate = publish;
        this.Link = link;
    }
    public String getTitle() {
        return this.Title;
    }
    public String getAuthor() { return this.author; };
    public String getDesc() {
        return this.Desc;
    }
    public String getCategory() {
        return this.Category;
    }
    public Date getPublishDate() {
        return this.PublishDate;
    }
    public String getLink() {
        return this.Link;
    }
}
