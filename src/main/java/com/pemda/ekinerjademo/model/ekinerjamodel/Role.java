package com.pemda.ekinerjademo.model.ekinerjamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bagus on 10/09/17.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "role")
    private List<AkunPegawai> akunPegawaiList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonIgnore
    public List<AkunPegawai> getAkunPegawaiList() {
        return akunPegawaiList;
    }

    public void setAkunPegawaiList(List<AkunPegawai> akunPegawaiList) {
        this.akunPegawaiList = akunPegawaiList;
    }
}
