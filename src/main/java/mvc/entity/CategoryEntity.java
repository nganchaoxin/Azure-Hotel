package mvc.entity;

import mvc.enums.CategoryRoom;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category_name")
    @Enumerated(EnumType.STRING)
    private CategoryRoom category_name;

    @Column(name = "description")
    private String description;

    @Column(name = "max_occupancy")
    private int max_occupancy;

    @Column(name = "bed_info")
    private String bed_info;

    @Column(name = "square")
    private double square;

    @Column(name = "price")
    private double price;
    @OneToMany(mappedBy = "categoryEntity")
    private List<RoomEntity> roomEntities;

    @OneToMany(mappedBy = "categoryEntity")
    private List<ImageEntity> imageEntities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryRoom getCategory_name() {
        return category_name;
    }

    public void setCategory_name(CategoryRoom category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax_occupancy() {
        return max_occupancy;
    }

    public void setMax_occupancy(int max_occupancy) {
        this.max_occupancy = max_occupancy;
    }

    public String getBed_info() {
        return bed_info;
    }

    public void setBed_info(String bed_info) {
        this.bed_info = bed_info;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<RoomEntity> getRoomEntities() {
        return roomEntities;
    }

    public void setRoomEntities(List<RoomEntity> roomEntities) {
        this.roomEntities = roomEntities;
    }

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

}
