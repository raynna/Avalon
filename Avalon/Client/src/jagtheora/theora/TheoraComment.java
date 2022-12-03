/* TheoraComment - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package jagtheora.theora;

import jagtheora.misc.SimplePeer;

public class TheoraComment extends SimplePeer {
    public TheoraComment() {
	init();
	if (a())
	    throw new IllegalStateException();
    }

    private native void init();

    @Override
    protected native void clear();

    @Override
    protected native void k();

    @Override
    protected native void d();
}
