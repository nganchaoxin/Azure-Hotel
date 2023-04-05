package mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rating_and_review")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "content")
    private String content;

    @Column(name = "rating_point")
    private double rating_point;

    @Column(name = "review_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date review_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating_point() {
        return rating_point;
    }

    public void setRating_point(double rating_point) {
        this.rating_point = rating_point;
    }

    public Date getReview_date() {
        return review_date;
    }

    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }
}
