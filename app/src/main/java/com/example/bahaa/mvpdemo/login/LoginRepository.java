package com.example.bahaa.mvpdemo.login;

public interface LoginRepository {

    User getUser();
    void saveUser(User user);
}
