/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author entel
 */
@Entity
@Table(name = "app_user")
@NamedQueries({
    @NamedQuery(name = "AppUser.findByEmail",
                query = "SELECT u FROM AppUser u WHERE u.email = :email")
})
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(unique = true)
    private String email;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String password;

    public AppUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
