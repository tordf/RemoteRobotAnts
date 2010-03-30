/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antmanager;

import java.io.File;

/**
 *
 * @author Tord
 */
public class DefaultLogfileProvider implements LogFileProvider
{


    protected ProjectDirectoryProvider ProjectDirectoryProvider;

    public DefaultLogfileProvider(ProjectDirectoryProvider ProjectDirectoryProvider) {
        this.ProjectDirectoryProvider = ProjectDirectoryProvider;
    }


    /**
     * Get the value of ProjectDirectoryProvider
     *
     * @return the value of ProjectDirectoryProvider
     */
    public ProjectDirectoryProvider getProjectDirectoryProvider() {
        return ProjectDirectoryProvider;
    }

    /**
     * Set the value of ProjectDirectoryProvider
     *
     * @param ProjectDirectoryProvider new value of ProjectDirectoryProvider
     */
    public void setProjectDirectoryProvider(ProjectDirectoryProvider ProjectDirectoryProvider) {
        this.ProjectDirectoryProvider = ProjectDirectoryProvider;
    }

    public File GetLogfile() {
        return new File(ProjectDirectoryProvider.ProjectDirectory().getAbsolutePath()+"/log.xml");
    }

    public static LogFileProvider GetDefaultProvider()
    {
        return new DefaultLogfileProvider(DefaultProjectDirectoryProvider.GetDefaultProvider());
    }

}
