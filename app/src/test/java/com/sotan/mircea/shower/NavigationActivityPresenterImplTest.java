package com.sotan.mircea.shower;

import com.sotan.mircea.shower.presenter.NavigationActivityPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by mircea
 */
@RunWith(MockitoJUnitRunner.class)
public class NavigationActivityPresenterImplTest {
    @Mock
    ConfigurationManager configurationManager;
   // @Mock
 //   NavigationActivityPresenter.View view;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_1() {
        NavigationActivityPresenterImpl presenter = new NavigationActivityPresenterImpl(configurationManager);
        when(configurationManager.getValueForKey(ConfigConstants.CLIENT_ID)).thenReturn("dummyClientId");
        when(configurationManager.getValueForKey(ConfigConstants.REDIRECT_URI)).thenReturn("dummyCallback");
      //  presenter.bind(view);
        presenter.onHandleSignInMenuItemClick();
     //   verify(view).openLoginActivity(any(AuthenticationRequest.class));
    }

    @Test
    public void test_buildAuthenticationRequest_nonNullParams_nonNullRequest() {
        when(configurationManager.getValueForKey(ConfigConstants.CLIENT_ID)).thenReturn("dummyClientId");
        when(configurationManager.getValueForKey(ConfigConstants.REDIRECT_URI)).thenReturn("dummyCallback");
        NavigationActivityPresenterImpl presenter = new NavigationActivityPresenterImpl(configurationManager);
        assertNotNull(presenter.buildAuthenticationRequest());
    }

    @Test
    public void test_buildAuthenticationRequest_nullClientId_nullRequest() {
        when(configurationManager.getValueForKey(ConfigConstants.CLIENT_ID)).thenReturn(null);
        when(configurationManager.getValueForKey(ConfigConstants.REDIRECT_URI)).thenReturn("dummyCallback");
        NavigationActivityPresenterImpl presenter = new NavigationActivityPresenterImpl(configurationManager);
        assertNull(presenter.buildAuthenticationRequest());
    }

    @Test
    public void test_buildAuthenticationRequest_nullRedirectUri_nullRequest() {
        when(configurationManager.getValueForKey(ConfigConstants.CLIENT_ID)).thenReturn("dummyClientId");
        when(configurationManager.getValueForKey(ConfigConstants.REDIRECT_URI)).thenReturn(null);
        NavigationActivityPresenterImpl presenter = new NavigationActivityPresenterImpl(configurationManager);
        assertNull(presenter.buildAuthenticationRequest());
    }

    @Test
    public void test_buildAuthenticationRequest_nullParams_nullRequest() {
        when(configurationManager.getValueForKey(ConfigConstants.CLIENT_ID)).thenReturn(null);
        when(configurationManager.getValueForKey(ConfigConstants.REDIRECT_URI)).thenReturn(null);
        NavigationActivityPresenterImpl presenter = new NavigationActivityPresenterImpl(configurationManager);
        assertNull(presenter.buildAuthenticationRequest());
    }
}
