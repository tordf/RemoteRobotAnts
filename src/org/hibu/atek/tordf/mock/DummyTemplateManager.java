/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.mock;

import org.hibu.atek.tordf.resource.TemplateManager;
import java.io.File;

/**
 *
 * @author Tord
 */
public class DummyTemplateManager implements TemplateManager
{

    public File PopulateProject(String id) {
       System.out.println("DummyTemplateManager: dummy PopulateProject");
       return new File("C:\\");
    }

}
