/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.source;

import java.io.File;
import org.hibu.atek.tordf.SourceProvider;

/**
 *
 * @author Tord
 */
public class SourceFactory
{

    public static SourceProvider GetDefaultSource(File projectDir)
    {
        File f = new File(projectDir, "\\src\\Run.java");
        JavaFileSourceProvider jfsP = new JavaFileSourceProvider();
        jfsP.setJavaFile(f);
        return jfsP;
    }

}
