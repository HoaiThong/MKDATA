package com.example.mkdata.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tbl_company")
public class MyCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_store", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "name_store")
    private String nameStore;

    @Lob
    @Column(name = "type_store")
    private String typeStore;

    @Lob
    @Column(name = "name_owner")
    private String nameOwner;

    @Lob
    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "ggmap_link")
    private String ggmapLink;

    @Lob
    @Column(name = "facebook_link")
    private String facebookLink;

    @Lob
    @Column(name = "website_link")
    private String websiteLink;

    @Column(name = "phone", length = 30)
    private String phone;

    @Lob
    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "note")
    private String note;

    @ColumnDefault("current_timestamp()")
    @Column(name = "creat_at")
    private Instant creatAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "update_at")
    private Instant updateAt;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (this.phone == null) {
            this.phone = phone;
        } else this.phone = this.phone + "," + phone;
    }
}