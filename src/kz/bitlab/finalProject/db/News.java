package kz.bitlab.finalProject.db;

import com.mysql.cj.xdevapi.TableImpl;

import java.sql.Timestamp;

public class News {
    private Long id;
    private Users user;
    private Categories category;
    private String title;
    private String content;
    private Timestamp postDate;

    public News(){}

    public News(Long id, Users user, Categories category, String title, String content, Timestamp postDate) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
