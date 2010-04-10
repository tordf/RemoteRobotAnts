/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibu.atek.tordf.source;

import org.hibu.atek.tordf.resource.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibu.atek.tordf.SourceProvider;
import org.hibu.atek.tordf.io.SaveHandler;

/**
 *
 * @author Tord
 */
public class JavaFileSourceProvider implements SourceProvider, SaveHandler {

    protected File javaFile;

    public File getJavaFile() {
        if (javaFile == null) {
            //javaFile = new File(dir, "\\src\\run.java");
        }
        return javaFile;
    }

    public void setJavaFile(File javaFile) {
        this.javaFile = javaFile;
    }

    public String GetSource() {
        FileReader fr = null;
        String res = "";
        try {
            fr = new FileReader(getJavaFile());
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null) {
                res += s;
                s = br.readLine();
            }


        } catch (IOException ex) {
            Logger.getLogger(JavaFileSourceProvider.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ie) {
                Logger.getLogger(JavaFileSourceProvider.class.getName()).log(Level.SEVERE, null, ie);
            }
        }
        return res;
    }

    public boolean Save(String content) {
        FileWriter fw = null;
        boolean result = true;
        try {
            File f = getJavaFile();
            fw = new FileWriter(f);
            fw.write(content);
        } catch (IOException ex) {
            result = false;
            Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
