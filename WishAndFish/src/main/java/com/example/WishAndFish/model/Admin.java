package com.example.WishAndFish.model;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    private boolean hasChangedPassword;

    public Admin() {
    }

    public Admin(String password, String email, String name, String surname, String phoneNumber) {
        super(password, email, name, surname, phoneNumber);
    }

    public Admin(User u) {
        super(u);
    }

    public boolean isHasChangedPassword() {
        return hasChangedPassword;
    }

    public void setHasChangedPassword(boolean hasChangedPassword) {
        this.hasChangedPassword = hasChangedPassword;
    }
}
