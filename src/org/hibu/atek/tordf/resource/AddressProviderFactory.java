/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.resource;

import org.hibu.atek.tordf.mock.DummyAddressProvider;

/**
 *
 * @author Tord
 */
public class AddressProviderFactory {

    public static AddressProvider getDefaultAddressProvider() {
        return new DummyAddressProvider();
    }

}
