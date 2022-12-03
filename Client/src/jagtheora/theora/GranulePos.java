/* GranulePos - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package jagtheora.theora;

import jagtheora.misc.SimplePeer;

public class GranulePos extends SimplePeer {
    public long position;

    private static native void init();

    @Override
    protected native void clear();

    static {
	init();
    }

    @Override
    protected native void k();

    @Override
    protected native void d();
}
