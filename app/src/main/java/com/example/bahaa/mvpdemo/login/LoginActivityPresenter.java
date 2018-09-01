package com.example.bahaa.mvpdemo.login;

import android.support.annotation.NonNull;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    @NonNull
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;


    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    public LoginActivityPresenter(LoginActivityMVP.View view) {
        this.view = view;
    }


    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view = view;

    }

    @Override
    public void loginButtonClicked() {
        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {
                model.createUser(view.getFirstName(), view.getLastName());
                view.showUserSavedMessage();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user = model.getUser();

        if (user == null) {
            if (view != null) {
                view.showUserNotAvailable();
            }
        } else if (user != null) {
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }

    }
}
