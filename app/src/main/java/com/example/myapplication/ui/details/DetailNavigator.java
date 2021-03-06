package com.example.myapplication.ui.details;

public interface DetailNavigator {

    void handleError(boolean finishCurrent);

    void openComposeMessageScreen();

    void endActivity();

    void setLoading(boolean show);
}
