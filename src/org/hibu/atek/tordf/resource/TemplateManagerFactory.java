/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.resource;

import org.hibu.atek.tordf.mock.DummyTemplateManager;

/**
 *
 * @author Tord
 */
public class TemplateManagerFactory
{

    public static TemplateManager getDefaultTemplateManager() {
        return new DummyTemplateManager();
    }

}
