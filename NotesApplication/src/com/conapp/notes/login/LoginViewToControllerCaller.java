package com.conapp.notes.login;

interface LoginViewToControllerCaller {
    void checkLoginDetailsOnline(char type, String username, String password);
    boolean isUsernameValid(String username);
    boolean isPasswordValid(String password);
    boolean checkUsername(String username);
    void addUser(String name, String username, String password);
    boolean checkNewPassword(String password, String rePassword);
}
