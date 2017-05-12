package org.tomi.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private Boolean valid = false;
    private List<User> users;

    public Result() {}

    public Result(List<User> users) {
        this.users = users;
        if (users==null || users.size()==0) {
            this.users = null;
            valid = true;
        }
    }

    public Boolean getValid() {
        return valid;
    }
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
