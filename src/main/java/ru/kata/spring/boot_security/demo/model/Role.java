package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String role;
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> user;
    public Role(String role){
        this.role = role;
    }
    public Role() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority(){
        return role;
    }
    public void setAuthority(String role){
        this.role=role;
    }

    @Override
    public String toString() {
        return this.role;
    }
    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(this == null)
            return false;
        if(getClass() != object.getClass())
            return false;
        Role other = (Role) object;
        if (id==null)
            if(other.id != null)
                return false;
        else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}