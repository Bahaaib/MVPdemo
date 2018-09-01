package com.example.bahaa.mvpdemo;

import com.example.bahaa.mvpdemo.login.LoginActivityMVP;
import com.example.bahaa.mvpdemo.login.LoginActivityPresenter;
import com.example.bahaa.mvpdemo.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterTests {

    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockLoginView;
    LoginActivityPresenter presenter;
    User user;

    @Before
    public void setup(){
        mockLoginModel = mock(LoginActivityMVP.Model.class);

        user = new User("Bahaa", "Ibrahim");

        when(mockLoginModel.getUser()).thenReturn(user);

        mockLoginView = mock(LoginActivityMVP.View.class);

        presenter = new LoginActivityPresenter(mockLoginModel);

        presenter.setView(mockLoginView);
    }

    @Test
    public void noInteractionWithView(){
        presenter.getCurrentUser();
        verifyZeroInteractions(mockLoginView);
    }

    @Test
    public void loadFromRepoWhenValidUserIsPresent(){
        when(mockLoginModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockLoginView, times(1)).setFirstName("Bahaa");
        verify(mockLoginView, times(1)).setLastName("Ibrahim");
        verify(mockLoginView, never()).showUserNotAvailable();
    }


    @Test
    public void showErrorMsgWhenUserIsNull(){
        when(mockLoginModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //verify model interactions
        verify(mockLoginModel, times(1)).getUser();

        //verify view interactions
        verify(mockLoginView, never()).setFirstName("Bahaa");
        verify(mockLoginView, never()).setLastName("Ibrahim");
        verify(mockLoginView, times(1)).showUserNotAvailable();
    }

}
