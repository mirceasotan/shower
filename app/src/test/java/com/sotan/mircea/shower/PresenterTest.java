package com.sotan.mircea.shower;

import com.sotan.mircea.shower.presenter.NavigationActivityPresenter;
import com.sotan.mircea.shower.presenter.PresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by mircea
 */
@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {
    @Mock
    NavigationActivityPresenter.View view;

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void test_bind_nonNullView_nonNullView() {
        PresenterImpl p = new PresenterImpl();
        p.bind(view);
        assertNotNull(p.getView());
    }

    @Test
    public void test_bind_nullView_nullView() {
        PresenterImpl p = new PresenterImpl();
        p.bind(null);
        assertNull(p.getView());
    }

    @Test
    public void test_unbind_nonNullView_nullView() {
        PresenterImpl p = new PresenterImpl();
        p.bind(view);
        p.unbind();
        assertNull(p.getView());
    }

    @Test
    public void test_unbind_nullView_nullView() {
        PresenterImpl p = new PresenterImpl();
        p.unbind();
        assertNull(p.getView());
    }

    @Test
    public void test_isBound_nonNullView_true() {
        PresenterImpl p = new PresenterImpl();
        p.bind(view);
        assertTrue(p.isBoundToView());
    }

    @Test
    public void test_isBound_nullView_false() {
        PresenterImpl p = new PresenterImpl();
        assertTrue(!p.isBoundToView());
    }
}
