package com.sotan.mircea.shower;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

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
        ConfigurationManager configurationManager = getConfigurationManager();
        assertNotNull(configurationManager.getAppProperties());
    }

    @Test(expected = NullPointerException.class)
    public void test_nullContext_throwsNP() {
        ConfigurationManager configurationManager = new ConfigurationManager(null);
        configurationManager.getAppProperties();
    }

    @Test(expected = NullPointerException.class)
    public void test_nullKey_returnsNullValue() {
        ConfigurationManager configurationManager = getConfigurationManager();
        Properties p = new Properties();
        configurationManager.setAppProperties(p);
        assertNull(configurationManager.getValueForKey(null));
    }

    @Test
    public void test_nonExistentKey_returnsNullValue() {
        ConfigurationManager configurationManager = getConfigurationManager();
        Properties p = new Properties();
        configurationManager.setAppProperties(p);
        assertNull(configurationManager.getValueForKey("dummyKey"));
    }

    @Test
    public void test_existentKey_returnsNullValue() {
        ConfigurationManager configurationManager = getConfigurationManager();
        Properties p = new Properties();
        p.put("dummyKey", "dummyValue");
        configurationManager.setAppProperties(p);
        assertNotNull(configurationManager.getValueForKey("dummyKey"));
    }

    @NonNull
    private ConfigurationManager getConfigurationManager() {
        when(context.getResources()).thenReturn(resources);
        InputStream anyInputStream = new ByteArrayInputStream("test data".getBytes());
        when(resources.openRawResource(anyInt())).thenReturn(anyInputStream);
        return new ConfigurationManager(context);
    }
}
