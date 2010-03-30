/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antmanager;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.BuildEvent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author Tord
 */
public class IlabLogger implements org.apache.tools.ant.BuildListener
{

    public void buildStarted(BuildEvent be) {


    }

    public void buildFinished(BuildEvent be) {
        int pri = be.getPriority();
        if(pri == Project.MSG_ERR)
        {
            String mes = be.getException().getMessage();
            System.out.println(mes);
            //error
        }

    }

    public void targetStarted(BuildEvent be) {

    }

    public void targetFinished(BuildEvent be) 
    {
        if(be.getPriority() == Project.MSG_ERR)
        {
            String mes = be.getException().getMessage();
            System.out.println(mes);
            //error
        }
    }

    public void taskStarted(BuildEvent be) {

    }

    public void taskFinished(BuildEvent be) {

    }

    public void messageLogged(BuildEvent be) 
    {
        if(be.getTask() == null)
            return;

        String name = be.getTask().getTaskName();
        if(name.compareTo("javac")==0)
        {
            System.out.println(be.getMessage());
        }
        if(be.getPriority() == Project.MSG_WARN)
        {
            Object o = be.getSource();
            
            System.out.println(be.getMessage());
        }

    }

}
