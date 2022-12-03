/* VorbisComment - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package jagtheora.vorbis;

import jagtheora.misc.SimplePeer;

public class VorbisComment extends SimplePeer {
    public VorbisComment() {
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
