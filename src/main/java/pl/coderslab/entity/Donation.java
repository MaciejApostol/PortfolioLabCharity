package pl.coderslab.entity;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.validator.City;
import pl.coderslab.validator.Street;
import pl.coderslab.validator.ZipCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Min(value = 1)
    @NotNull
    private Integer quantity;

    @JoinColumn(nullable = false)
    @NotNull
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();

    @JoinColumn(nullable = false)
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Institution institution;

    @JoinColumn(nullable = false)
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Deleted deleted = Deleted.AVAILABLE;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @Street
    private String street;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @City
    private String city;

    @Column(nullable = false)
    @NotNull
    @NotBlank
    @ZipCode
    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @NotNull
    private LocalDate pickUpDate;

    @Column(nullable = false)
    @NotNull
    private LocalTime pickUpTime;

    private String pickUpComment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @UpdateTimestamp
    private LocalDate orderDate;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public void setDeleted(Deleted deleted) {
        this.deleted = deleted;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate OrderDate) {
        this.orderDate = OrderDate;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", categories=" + categories +
                ", institution=" + institution +
                ", user=" + user +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", pickUpComment='" + pickUpComment + '\'' +
                ", orderDate=" + orderDate +
                ", status=" + status +
                '}';
    }
}
