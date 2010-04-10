/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibu.atek.tordf.resource;

import java.util.ArrayList;

/**
 *
 * @author Tord
 */
public class AddressManager implements AddressProvider {

    int numberOfAddresses = 4;
    ArrayList<String> Addesses = new ArrayList<String>();
    int addressesServed = 0;
    public void Add(String addr) {
        Addesses.add(addr);
    }

    public String NextFree()
    {
        String s = Addesses.get(addressesServed);
        addressesServed++;
        return s;
    }
}
