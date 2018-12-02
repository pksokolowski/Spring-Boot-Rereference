package com.github.pksokolowski.hello;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    private String name;

    public User() {
        name = "unknown";
    }

    public User(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) {
            return false;
        }
        User rhs = (User) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(id, rhs.id)
                .append(name, rhs.name)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(13, 39)
                .append(id)
                .append(name)
                .toHashCode();
    }
}
