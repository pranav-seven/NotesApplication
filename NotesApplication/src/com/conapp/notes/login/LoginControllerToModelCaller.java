package com.conapp.notes.login;

import java.util.List;
import com.conapp.notes.dto.User;

public interface LoginControllerToModelCaller {
    boolean checkUsername(String username);
    void addUser(String name, String username, String password);
    List<User> getUsersList();
}
