package com.visionire.downtown.auth;

import com.visionire.downtown.loader.LoaderBase.UserLoaderListener;
import com.visionire.downtown.net.SocketConnectionImpl;

/**
 *
 * @author nickbabenko
 */
public interface AuthProcessor {
    public void login(UserLoaderListener listener, SocketConnectionImpl connection, String loginID, String password);
}