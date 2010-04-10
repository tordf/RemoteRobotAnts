/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.mock;

import org.hibu.atek.tordf.io.SaveHandler;

/**
 *
 * @author Tord
 */
public class DummySaveHandler implements SaveHandler
{

    public boolean Save(String text) {
        System.out.println("DummySaveHandler: dummy Save");
        return true;
    }

}
