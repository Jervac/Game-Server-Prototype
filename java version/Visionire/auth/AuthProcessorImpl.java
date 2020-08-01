package com.visionire.downtown.auth;

import akka.actor.TypedActor;

import com.visionire.downtown.loader.LoaderBase.UserLoaderListener;
import com.visionire.downtown.loader.UserLoader;
import com.visionire.downtown.loader.UserLoaderImpl;
import com.visionire.downtown.net.SocketConnectionImpl;
import com.visionire.downtown.util.Log;

/**
 *
 * @author nickbabenko
 */
public class AuthProcessorImpl extends TypedActor implements AuthProcessor {
    @Override
    public void login(UserLoaderListener listener, SocketConnectionImpl connection, String loginID, String password) {
        Log.out(Log.DEBUG, "Processing login for" + loginID, this);
        
        UserLoader loader = (UserLoader) TypedActor.newInstance(UserLoader.class, UserLoaderImpl.class, 1000);

        loader.addSingleEventListener(listener);
	loader.fromLoginIDAndPassword(loginID, password, connection);
    }
}