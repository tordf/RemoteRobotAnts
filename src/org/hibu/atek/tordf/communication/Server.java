/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.communication;

import org.hibu.atek.tordf.communication.ServerSocketHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibu.atek.tordf.resource.ResourceManager;

/**
 *
 * @author Tord
 */
public class Server implements Runnable
{

    protected ResourceManager resourceManager;

    /**
     * Get the value of resourceManager
     *
     * @return the value of resourceManager
     */
    public ResourceManager getResourceManager() {
        if(resourceManager == null)resourceManager = new ResourceManager();
        return resourceManager;
    }

    /**
     * Set the value of resourceManager
     *
     * @param resourceManager new value of resourceManager
     */
    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    boolean keepRunning = true;
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(Protocol.PORT);
            while(keepRunning)
            {
                Socket s = ss.accept();
                ServerSocketHandler sh =  new ServerSocketHandler(s,getResourceManager());
                new Thread(sh).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
