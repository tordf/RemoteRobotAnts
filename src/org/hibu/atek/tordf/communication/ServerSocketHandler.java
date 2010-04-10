/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.communication;

import org.hibu.atek.tordf.building.AntBuilder;
import org.hibu.atek.tordf.resource.Resource;
import org.hibu.atek.tordf.resource.ResourceManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibu.atek.tordf.util.Utils;

/**
 *
 * @author Tord
 */
public class ServerSocketHandler implements Runnable
{

    public ServerSocketHandler(Socket socket, ResourceManager resourceManager) {
        this.socket = socket;
        setResourceManager(resourceManager);
    }

    protected Socket socket;

    /**
     * Get the value of socket
     *
     * @return the value of socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Set the value of socket
     *
     * @param socket new value of socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void run()
    {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            PrintWriter bw = new PrintWriter(os,true);


            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(socket.isConnected())
            {
                while(!br.ready())
                {
                    Utils.sleep(100);
                }
                String request = br.readLine();
                String id = br.readLine();
                Resource r = resourceManager.Get(id);

                if(request == null)continue;
                // don't get resource and request is different from aquire.

                //reserver 

                if(Protocol.REQUEST_RESOURCE.compareTo(request)==0)
                {
                        String time = br.readLine();
                        long lTime = Long.parseLong(time);
                        boolean bb = resourceManager.Reserve(lTime, id);
                        if(bb)
                            bw.println(Protocol.RESOURCE_AQUIRED);
                        else
                            bw.println(Protocol.RESOURCE_DENIED);
                }
                // don't have a resource.......
                else if(r == null)
                {
                    bw.println(Protocol.RESOURCE_DENIED);
                    continue;
                }
                // get source code.
                else if(Protocol.REQUEST_SOURCE.compareTo(request)==0)
                {
                    String s = r.GetSource();
                    if(s!=null)
                    {
                        bw.println(Protocol.REQUEST_SOURCE_SUCESS);
                        bw.println(s.length());
                        bw.println(s);                        
                    }
                    else
                    {
                        bw.println(Protocol.REQUEST_SOURCE_FAIL);
                    }
                }
                // must have aquire to get java file.
                else if(Protocol.BUILD_RESOURCE.compareTo(request)==0)
                {
                    if(r!=null)
                    {
                        AntBuilder b = r.getBuilder();
                        boolean result = b.Compile();
                        if(result)
                            bw.println(Protocol.BUILD_SUCESS);
                        else
                            bw.println(Protocol.BUILD_FAILED);
                    }
                    else
                    {
                         bw.println(Protocol.BUILD_FAILED);
                    }
                }
                else if(Protocol.DEPLOY_RESOURCE.compareTo(request)==0)
                {
                    AntBuilder b = r.getBuilder();
                    boolean result = b.Deploy();
                    if(result)
                        bw.println(Protocol.DEPLOY_SUCESS);
                    else
                        bw.println(Protocol.DEPLOY_FAILED);
                }
                else if(Protocol.RUN_RESOURCE.compareTo(request)==0)
                {
                    AntBuilder b = r.getBuilder();
                    boolean result = b.Run();
                    if(result)
                        bw.println(Protocol.RUN_SUCESS);
                    else
                        bw.println(Protocol.RUN_FAIL);
                }
                else if(Protocol.SAVE_RESOURCE.compareTo(request)==0)
                {
                    String size = br.readLine();
                    int iSize = Integer.parseInt(size);
                    char buffer[] = new char[iSize];
                    int read = br.read(buffer, 0, iSize);
                    if(read != iSize)
                        System.out.println("Protocol.SAVE_RESOURCE: content size missmatch\n\t expected: "+size+"\n\tgot"+read);
                    String content = new String(buffer);
                    boolean result = r.Save(content);
                    if(result)
                        bw.println(Protocol.SAVE_SUCESS);
                    else
                        bw.println(Protocol.SAVE_FAIL);
                    
                }
                else if(Protocol.RELEASE_RESOURCE.compareTo(request)==0)
                {
                    boolean result = resourceManager.Release(r, id);
                    if(result)
                        bw.println(Protocol.RELEASE_SUCESS);
                    else
                        bw.println(Protocol.RELEASE_FAIL);
                }
            }


        } catch (IOException ex) {
            Logger.getLogger(ServerSocketHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected ResourceManager resourceManager;

    /**
     * Get the value of resourceManager
     *
     * @return the value of resourceManager
     */
    public ResourceManager getResourceManager() {
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

}


