package com.evogames.api.objects;

public class EGConfig {
    private String defaultrank;

    public EGConfig(String defaultrank) {
        this.defaultrank = defaultrank;
    }

    public String getDefaultrank() {
        return defaultrank;
    }

    public void setDefaultrank(String defaultrank) {
        this.defaultrank = defaultrank;
    }
}
