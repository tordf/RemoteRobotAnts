/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.resource;

import org.hibu.atek.tordf.building.AntBuilder;
import org.hibu.atek.tordf.source.SourceFactory;
import org.hibu.atek.tordf.source.SaveHandlerFactory;
import org.hibu.atek.tordf.building.BuilderFactory;
import java.io.File;
import org.hibu.atek.tordf.SourceProvider;
import org.hibu.atek.tordf.io.SaveHandler;

/**
 *
 * @author Tord
 */
public class Resource implements SourceProvider,SaveHandler
{

    SourceProvider source = null;
    SaveHandler saveHandler = null;

    public String GetSource()
    {
        if(source == null)source = SourceFactory.GetDefaultSource();
        return source.GetSource();
    }
    private String Owner;

    public Resource(AddressProvider addressManager) {
        this.addressProvider = addressManager;
    }

    protected File dir;

    /**
     * Get the value of dir
     *
     * @return the value of dir
     */
    public File getDir() {
        return dir;
    }

    /**
     * Set the value of dir
     *
     * @param dir new value of dir
     */
    public void setDir(File dir) {
        this.dir = dir;

    }
    protected AntBuilder builder;

    /**
     * Get the value of builder
     *
     * @return the value of builder
     */
    public AntBuilder getBuilder() {
        if(dir == null) return null;
        if(builder == null) builder = BuilderFactory.GetDefaultBuilder(dir,addressProvider.NextFree());
        return builder;
    }

    /**
     * Set the value of builder
     *
     * @param builder new value of builder
     */
    public void setBuilder(AntBuilder builder) {
        this.builder = builder;
    }
    protected AddressProvider addressProvider;

    /**
     * Get the value of addressManager
     *
     * @return the value of addressManager
     */
    public AddressProvider getAddressManager() {
        if(addressProvider == null) addressProvider = AddressProviderFactory.getDefaultAddressProvider();
        return addressProvider;
    }

    /**
     * Set the value of addressManager
     *
     * @param addressManager new value of addressManager
     */
    public void setAddressManager(AddressProvider addressManager) {
        this.addressProvider = addressManager;
    }

    /**
     * @return the Owner
     */
    public String getOwner() {
        return Owner;
    }

    /**
     * @param Owner the Owner to set
     */
    public void setOwner(String Owner) {
        this.Owner = Owner;
    }

    public boolean Save(String content) {
        if(saveHandler == null)saveHandler = SaveHandlerFactory.getDefaultSaveHandler();
        return saveHandler.Save(content);
    }

    long expirationTime;
    public void reserve(long lTime) {
       long now = System.currentTimeMillis();
       expirationTime = now +lTime;
    }

   public boolean hasExpired()
    {
        long now = System.currentTimeMillis();
        return now > expirationTime;
    }



}
