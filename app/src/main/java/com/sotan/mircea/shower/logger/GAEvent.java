package com.sotan.mircea.shower.logger;

import android.support.annotation.NonNull;

/**
 * Created by mirceasotan
 */
public class GAEvent implements Event {
    private String screenName;
    private String category;
    private String action;
    private String label;
    private final short type;

    interface Type {
        byte SCREEN = 1;
        byte EVENT = 2;
    }

    public GAEvent(String screenName) {
        this.screenName = screenName;
        this.type = Type.SCREEN;
    }

    private GAEvent(String category, String action, String label, String screenName) {
        this.category = category;
        this.action = action;
        this.label = label;
        this.screenName = screenName;
        this.type = Type.EVENT;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getAction() {
        return action;
    }

    public String getLabel() {
        return label;
    }

    public static class GAEventBuilder {
        private String category;
        private String action;
        private String label;
        private String screenName;

        public GAEventBuilder() {
        }

        public GAEventBuilder withCategory(@NonNull String category) {
            this.category = category;
            return this;
        }


        public GAEventBuilder withAction(@NonNull String action) {
            this.action = action;
            return this;
        }


        public GAEventBuilder withLabel(@NonNull String label) {
            this.label = label;
            return this;
        }


        public GAEventBuilder withScreenName(@NonNull String screenName) {
            this.screenName = screenName;
            return this;
        }

        public GAEvent build() {
            return new GAEvent(category, action, label, screenName);
        }
    }
}
