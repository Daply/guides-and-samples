package models;

import be.objectify.deadbolt.java.models.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_permission")
public class UserPermission extends Model implements Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_permission_id")
    private long permissionId;

    @Column(name="permission_value")
    private String permissionValue;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userPermissions")
    @JsonIgnore
    private List<User> users;

    @Override
    public String getValue() {
        return permissionValue;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
