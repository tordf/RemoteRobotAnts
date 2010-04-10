/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.mock;

import org.hibu.atek.tordf.building.AntBuilder;

/**
 *
 * @author Tord
 */
public class DummyBuilder implements AntBuilder
{

    public boolean Run() {
        System.out.println("DummyBuilder: dummy Run");
        return true;
    }

    public boolean Deploy() {
        System.out.println("DummyBuilder: dummy Deploy");
       return true;
    }

    public boolean Compile() {
        System.out.println("DummyBuilder: dummy Compile");
        return true;
    }

}
