/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 *
 * @author Tord
 */
public class ResourceManager extends TimerTask
{
    public void run()
    {
        if(usedResources.size() != 0)
        {
            Collection<Resource> res = usedResources.values();
            for(Resource r : res)
                if(r.hasExpired())
                {
                    releaseResource(r);
                }
        }
    }

    public void releaseResource(Resource r) {
        usedResources.remove(r.getOwner());
        r.setOwner(null);
        availableResources.add(r);
    }

    /**
     * Default 4
     */
    protected int numberOfResources = 4;

    /**
     * Get the value of numberOfResources
     *
     * @return the value of numberOfResources
     */
    public int getNumberOfResources() {
        return numberOfResources;
    }

    /**
     * Set the value of numberOfResources
     *
     * @param numberOfResources new value of numberOfResources
     */
    public void setNumberOfResources(int numberOfResources) {
        this.numberOfResources = numberOfResources;
    }



    public Resource Get(String owner)
    {
        if(usedResources.containsKey(owner))
            return usedResources.get(owner);
        return null;
    }

    public boolean Reserve(long time,String owner)
    {
        if(availableResources.size() == 0)return false;

        Resource r = availableResources.get(0);
        r.reserve(time);
        r.setOwner(owner);
        availableResources.remove(0);
        usedResources.put(owner, r);
        return true;
    }

    public boolean Release(Resource res,String owner)
    {
        boolean result = false;
         if(usedResources.containsKey(owner))
         {
            usedResources.remove(owner);
            availableResources.add(res);
            result = true;
         }
         return result;
    }



    ArrayList<Resource> resources = new ArrayList<Resource>();
    Map<String,Resource> usedResources = new HashMap<String, Resource>();
    ArrayList<Resource> availableResources = new ArrayList<Resource>();
   
    protected TemplateManager templateManager;

    /**
     * Get the value of templateManager
     *
     * @return the value of templateManager
     */
    public TemplateManager getTemplateManager() {
        if(templateManager == null)templateManager = TemplateManagerFactory.getDefaultTemplateManager();
        return templateManager;
    }

    /**
     * Set the value of templateManager
     *
     * @param templateManager new value of templateManager
     */
    public void setTemplateManager(TemplateManager templateManager) {
        this.templateManager = templateManager;
    }

    public void populate(int numberOfResources2)
    {
       
        for(int i = 0; i < numberOfResources2 ; i++)
        {
                File dir = getTemplateManager().PopulateProject(""+i);
                Resource r = new Resource(getAddressManager());
                resources.add(r);
                availableResources.add(r);

                r.setDir(dir);
        }
    }
    protected AddressProvider addressManager;

    /**
     * Get the value of addressManager
     *
     * @return the value of addressManager
     */
    public AddressProvider getAddressManager() {
        if(addressManager == null) addressManager = AddressProviderFactory.getDefaultAddressProvider();
        return addressManager;
    }

    /**
     * Set the value of addressManager
     *
     * @param addressManager new value of addressManager
     */
    public void setAddressManager(AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    boolean hasResource(String id)
    {
        return usedResources.containsKey(id);
    }


}
