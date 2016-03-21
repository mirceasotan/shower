package com.sotan.mircea.shower;

import android.content.Context;
import android.content.res.Resources;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by mircea
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigurationManagerTest {
    @Mock
    Context context;
    @Mock
    Resources resources;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_nonNullContext_propertiesIsNotNull() {
        Mockito.when(context.getResources()).thenReturn(resources);
        InputStream anyInputStream = new ByteArrayInputStream("test data".getBytes());
        Mockito.when(resources.openRawResource(Mockito.anyInt())).thenReturn(anyInputStream);
        ConfigurationManager configurationManager = new ConfigurationManager(context);
        Assert.assertNotNull(configurationManager.getAppProperties());
    }

    @Test(expected=NullPointerException.class)
    public void test_nullContext_throwsNP(){
        ConfigurationManager configurationManager = new ConfigurationManager(null);
        configurationManager.getAppProperties();
    }

}
