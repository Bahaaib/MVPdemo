package com.example.bahaa.mvpdemo.root;

import com.example.bahaa.mvpdemo.login.LoginActivity;
import com.example.bahaa.mvpdemo.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);
}
