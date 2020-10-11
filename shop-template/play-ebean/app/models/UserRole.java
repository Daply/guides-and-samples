package models;

import be.objectify.deadbolt.java.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_role")
public class UserRole extends Model implements Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_role_id")
    private long roleId;

    @Column(name="role")
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    @JsonIgnore
    private List<User> users;

    @Override
    public String getName() {
        return role;
    }
}
