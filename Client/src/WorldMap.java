public class WorldMap {

    public static MapKeys getMapKeys(Class243 idx, String archiveName, boolean member, int i) {
	try {
	    int i_0_ = idx.getArchiveId(archiveName);
	    if (-1 == i_0_)
		return new MapKeys(0);
	    int[] is = idx.getFileIds(i_0_);
	    MapKeys class296 = new MapKeys(is.length);
	    int i_1_ = 0;
	    int i_2_ = 0;
	    while (i_1_ < class296.anInt3169 * -1407078377) {
		RsByteBuffer stream = new RsByteBuffer(idx.getFile(i_0_, is[i_2_++]));
		int keyTile = stream.readInt((byte) 101);
		int keyId = stream.readUnsignedShort();
		int memberKey = stream.readUnsignedByte();
		if (member || memberKey != 1) {
		    class296.keyTiles[i_1_] = keyTile;
		    class296.keyIds[i_1_] = keyId;
		    i_1_++;
		} else
		    class296.anInt3169 -= 526813095;
	    }
	    return class296;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("jz.a(").append(')').toString());
	}
    }

    static MapDetails getMapDetails(Class243 class243, int archiveId, int fileId, int i_21_) {
	try {
	    RsByteBuffer stream = new RsByteBuffer(class243.getFile(archiveId, fileId));
	    MapDetails mapDetails = (new MapDetails(fileId, stream.readString(-1014876746), stream.readString(-346433981), stream.readInt((byte) -81), stream.readInt((byte) 27), stream.readUnsignedByte() == 1, stream.readUnsignedByte(), stream.readUnsignedByte()));
	    int length = stream.readUnsignedByte();
	    for (int i_23_ = 0; i_23_ < length; i_23_++)
		mapDetails.aClass453_9642.add(new MapRectangle(stream.readUnsignedByte(), stream.readUnsignedShort(), stream.readUnsignedShort(), stream.readUnsignedShort(), stream.readUnsignedShort(), stream.readUnsignedShort(), stream.readUnsignedShort(), stream.readUnsignedShort(), stream.readUnsignedShort()));
	    mapDetails.method3454(955144381);
	    return mapDetails;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ti.k(").append(')').toString());
	}
    }

}
