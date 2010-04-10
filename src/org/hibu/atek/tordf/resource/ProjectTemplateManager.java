/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.resource;

import org.hibu.atek.tordf.resource.TemplateManager;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibu.atek.tordf.util.DirectoryCopy;

/**
 *
 * @author Tord
 */
public class ProjectTemplateManager implements TemplateManager
{

        /**
     * Set the value of TemplateDir
     *
     * @param TemplateDir new value of TemplateDir
     */
    public void setTemplateDir(File TemplateDir) {
        this.TemplateDir = TemplateDir;
    }

        protected File TemplateDir;

    /**
     * Get the value of TemplateDir
     *
     * @return the value of TemplateDir
     */
    public File getTemplateDir() {
        return TemplateDir;
    }
    String DefaultProjectName = "Project";
    public File PopulateProject(String id)
    {
         File parent = getTemplateDir().getParentFile();
         File dir = null;
         try {
                dir = new File(parent, DefaultProjectName + id);
                boolean created = dir.createNewFile();
                if (!created) {
                    System.out.println("Directory not created");
                }

                DirectoryCopy.copyDirectory(getTemplateDir(), dir);
            } catch (IOException ex) {
                Logger.getLogger(ProjectTemplateManager.class.getName()).log(Level.SEVERE, null, ex);
            }
         return dir;
    }

}
