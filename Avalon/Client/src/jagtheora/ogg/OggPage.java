/* OggPage - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package jagtheora.ogg;

import jagtheora.misc.SimplePeer;

public class OggPage extends SimplePeer {
    public native long getGranulePos();

    public native int getSerialNumber();

    public native long getPageNumber();

    public native int getCompletedPackets();

    public native int getVersion();

    public native boolean isContinued();

    public native boolean isBOS();

    public native boolean isEOS();

    @Override
    protected native void clear();

    @Override
    protected native void k();

    @Override
    protected native void d();
}
