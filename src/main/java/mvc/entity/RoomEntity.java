package mvc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_name")
    private String room_name;

    @Column(name = "room_number")
    private int room_number;

    @Column(name = "position")
    private String position;

    @OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingDetailEntity> bookingDetailEntities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "category_id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "roomEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingCartItemEntity> bookingCartItemEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<BookingDetailEntity> getBookingDetailEntities() {
        return bookingDetailEntities;
    }

    public void setBookingDetailEntities(List<BookingDetailEntity> bookingDetailEntities) {
        this.bookingDetailEntities = bookingDetailEntities;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public List<BookingCartItemEntity> getBookingCartItemEntities() {
        return bookingCartItemEntities;
    }

    public void setBookingCartItemEntities(List<BookingCartItemEntity> bookingCartItemEntities) {
        this.bookingCartItemEntities = bookingCartItemEntities;
    }
}
