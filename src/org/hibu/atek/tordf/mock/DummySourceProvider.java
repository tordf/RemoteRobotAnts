/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.mock;

import org.hibu.atek.tordf.SourceProvider;

/**
 *
 * @author Tord
 */
public class DummySourceProvider implements SourceProvider{

    public String GetSource() {
        System.out.println("DummySourceProvider: dummy GetSource");
        return "Public Class TestClass{}";
    }

}
