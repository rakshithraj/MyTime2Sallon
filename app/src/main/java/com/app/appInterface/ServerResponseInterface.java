package com.app.appInterface;

/**
 * Created by Rakshith on 8/20/2016.
 */
public interface ServerResponseInterface {

    public void onServerResponse(String result);
    public void onServerError(String result);
    public void onTokenExpired();
    public void setLoading(boolean status);
    public boolean isLoading();
}
