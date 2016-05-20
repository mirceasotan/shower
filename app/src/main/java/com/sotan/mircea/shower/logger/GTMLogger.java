package com.sotan.mircea.shower.logger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tagmanager.TagManager;
import com.sotan.mircea.shower.BuildConfig;
import com.sotan.mircea.shower.R;
import com.sotan.mircea.shower.ShowerApp;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by mirceasotan
 */
@Named("GTMLogger")
@Singleton
public class GTMLogger implements Logger {
    private static final long TIMEOUT_FOR_CONTAINER_OPEN_MILLISECONDS = 2000;
    private static final String MY_EVENT_VAR = "myEvent";
    private static final String EVENT_CATEGORY_VAR = "eventCategory";
    private static final String EVENT_ACTION_VAR = "eventAction";
    private static final String EVENT_LABEL_VAR = "eventLabel";
    private static final String EVENT_SCREEN_NAME_VAR = "screenName";
    private static final String OPEN_SCREEN_EVENT = "openScreen";
    private static final String EVENT = "event";
    @NonNull
    private Context context;
    private boolean initialized;

    @Inject
    public GTMLogger(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        TagManager tagManager = TagManager.getInstance(context);

        if (BuildConfig.DEBUG) {
            tagManager.setVerboseLoggingEnabled(true);
        }

        // The onResult method will be called as soon as one of the following happens:
        // 1. a saved container is loaded
        // 2. if there is no saved container, a network container is loaded
        // 3. the 2-second timeout occurs
        PendingResult<ContainerHolder> pending =
                tagManager.loadContainerPreferNonDefault("GTM-KLQ6C4", R.raw.gtm_klq6c4);
        pending.setResultCallback(new ResultCallback<ContainerHolder>() {
            @Override
            public void onResult(@NonNull ContainerHolder containerHolder) {
                handleLoadContainerResult(containerHolder);
            }
        }, TIMEOUT_FOR_CONTAINER_OPEN_MILLISECONDS, TimeUnit.MILLISECONDS);
    }

    private void handleLoadContainerResult(@NonNull ContainerHolder containerHolder) {

        if (!containerHolder.getStatus().isSuccess()) {
            Log.e(ShowerApp.TAG, "GTM failure loading container");
        }

        initialized = true;
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void log(@NonNull Event event) {
        if (!isInitialized()) {
            Log.e(ShowerApp.TAG, "GTM not initialized!!!");
            return;
        }

        if (event instanceof GAEvent) {
            GAEvent gaEvent = (GAEvent) event;

            switch (gaEvent.getType()) {
                case GAEvent.Type.SCREEN:
                    logScreenEvent(gaEvent);
                    break;
                case GAEvent.Type.EVENT:
                    logEvent(gaEvent);
                    break;
            }
        } else {
            Log.e(ShowerApp.TAG, "Event cannot be converted to GA Event!!!");
        }
    }

    /**
     * @param event
     */
    private void logScreenEvent(@NonNull GAEvent event) {
        DataLayer dataLayer = TagManager.getInstance(context).getDataLayer();

        if (TextUtils.isEmpty(event.getScreenName())) {
            Log.e(ShowerApp.TAG, "Invalid GA Screen Parameter!!!");
            return;
        }

        dataLayer.pushEvent(OPEN_SCREEN_EVENT, DataLayer.mapOf(EVENT_SCREEN_NAME_VAR,
                event.getScreenName()));
    }

    /**
     * @param event
     */
    private void logEvent(@NonNull GAEvent event) {
        DataLayer dataLayer = TagManager.getInstance(context).getDataLayer();
        dataLayer.push(DataLayer.mapOf(EVENT, MY_EVENT_VAR,
                EVENT_CATEGORY_VAR, event.getCategory(),
                EVENT_ACTION_VAR, event.getAction(),
                EVENT_LABEL_VAR, event.getLabel(),
                EVENT_SCREEN_NAME_VAR, event.getScreenName()));
    }
}
