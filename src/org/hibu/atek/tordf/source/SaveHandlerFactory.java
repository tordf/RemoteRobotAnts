/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.source;

import org.hibu.atek.tordf.mock.DummySaveHandler;
import org.hibu.atek.tordf.io.SaveHandler;

/**
 *
 * @author Tord
 */
public class SaveHandlerFactory {

    public static SaveHandler getDefaultSaveHandler() {
        return new DummySaveHandler();
    }

}
