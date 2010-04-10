/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.mock;

import org.hibu.atek.tordf.resource.AddressProvider;

/**
 *
 * @author Tord
 */
public class DummyAddressProvider implements AddressProvider
{
    int address = 0;
    public String NextFree() {
        System.out.println("DummyAddressProvider: Providing NextFree dummy address");
        address++;
        return ""+address;
    }

}
