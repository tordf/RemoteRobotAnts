/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hibu.atek.tordf.source;

import org.hibu.atek.tordf.mock.DummySourceProvider;
import org.hibu.atek.tordf.SourceProvider;

/**
 *
 * @author Tord
 */
public class SourceFactory
{

    public static SourceProvider GetDefaultSource()
    {
        return new DummySourceProvider();
    }

}
