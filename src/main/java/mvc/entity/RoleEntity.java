/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.entity;

import mvc.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @ManyToMany(mappedBy = "userRoles")
    private Set<AccountEntity> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<AccountEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<AccountEntity> users) {
        this.users = users;
    }

}
