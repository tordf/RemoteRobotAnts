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
public class DefaultProjectDirectoryProvider implements ProjectDirectoryProvider
{

    /**
     * Here the project will reside
     * @return
     */
    public File ProjectDirectory() {
        return new File("C:/Master/ProjectDirectory");
    }

    public static ProjectDirectoryProvider GetDefaultProvider()
    {
        return new DefaultProjectDirectoryProvider();
    }

}
