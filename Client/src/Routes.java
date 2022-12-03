
public class Routes {

    static final void findGroundItemRoute(int dstX, int dstY) {
	try {
	    if (client.gametype != Class411.game_stellardawn) {
		//if (!findRoute(dstX, dstY, false, Routes.createRectOverlapStrategy(dstX, dstY, 1, 1))) disable this = better
		findRoute(dstX, dstY, false, Routes.createEntityStrategy(dstX, dstY, 1, 1, 0));
	    } else if (!findRoute(dstX, dstY, false, Routes.createEntityStrategy(dstX, dstY, 1, 1, 0)))
		findRoute(dstX, dstY, false, Routes.createRectOverlapStrategy(dstX, dstY, 1, 1));
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("pp.jv(").append(')').toString());
	}
    }

    static final void findObjectRoute(int dstX, int dstY, long objdata) {
	try {
	    // original code
	    int objtype = (int) objdata >> 14 & 0x1f;
	    int objrot = (int) objdata >> 20 & 0x3;
	    int objid = (int) (objdata >>> 32) & 0x7fffffff;
	    GameObjectType typedef = ((GameObjectType) Class422_Sub20.method5701(ExactStrategy.method4108(114624527), objtype, (byte) 2));
	    PathStrategy strategy;
	    if (GameObjectType.T10 == typedef || GameObjectType.T11 == typedef || GameObjectType.T22 == typedef) {
		ObjectDefinitions definitions = client.aClass283_8716.method2641(-1208362615).getObjectDefinitions(objid);
		int sizeX;
		int sizeY;
		if (objrot == 0 || objrot == 2) {
		    sizeX = definitions.sizeX * -1125834887;
		    sizeY = definitions.sizeY * -565161399;
		} else {
		    sizeX = definitions.sizeY * -565161399;
		    sizeY = definitions.sizeX * -1125834887;
		}
		strategy = Routes.createObjectCloseStrategy(dstX, dstY, sizeX, sizeY, GameObjectType.T0, 0);
	    } else if (Class82_Sub9.isWall(-1976050083 * typedef.type, (byte) 28))
		strategy = Routes.createObjectCloseStrategy(dstX, dstY, 0, 0, typedef, objrot);
	    else
		strategy = Routes.createObjectExactStrategy(dstX, dstY, 0, 0, typedef, objrot);
	    Routes.findRoute(dstX, dstY, true, strategy);


	    /*
	    int type = (int) objdata >> 14 & 0x1f;
	    int rotation = (int) objdata >> 20 & 0x3;
	    int id = (int) (objdata >>> 32) & 0x7fffffff;
	    GameObjectType typedef = ((GameObjectType) Class422_Sub20.method5701(ExactStrategy.method4108(114624527), type, (byte) 2));
	    ObjectDefinitions definition = client.aClass283_8716.method2641(-1208362615).getObjectDefinitions(id);
	    
	    
	    System.out.println("id:" + id + ", type:" + type + ", rotation:" + rotation);
	    
	    
	    int cflag_rot = definition.test_cflag;
	    System.out.println("cflag of route:"+ cflag_rot + " (" + ((cflag_rot & 0x1) != 0 ? "0x1," : "") + ((cflag_rot & 0x2) != 0 ? "0x2," : "") + ((cflag_rot & 0x4) != 0 ? "0x4," : "") + ((cflag_rot & 0x8) != 0 ? "0x8" : "") + ")");
	    if (rotation != 0) {
	    // rotate the flag (expermental)
	    cflag_rot = ((cflag_rot << rotation) & 0xF) + (cflag_rot >> (4 - rotation));
	    System.out.println("Rotated cflag of route:"+ cflag_rot + " (" + ((cflag_rot & 0x1) != 0 ? "0x1," : "") + ((cflag_rot & 0x2) != 0 ? "0x2," : "") + ((cflag_rot & 0x4) != 0 ? "0x4," : "") + ((cflag_rot & 0x8) != 0 ? "0x8" : "") + ")");
	    }
	    if ((cflag_rot & ~(0x1 | 0x2 | 0x4 | 0x8)) != 0)
	    System.out.println("Invalid cflag??");
	    
	    PathStrategy strategy;   
	    if (GameObjectType.T10 == typedef || GameObjectType.T11 == typedef || GameObjectType.T22 == typedef) {     	
	    int sizeX;
	    int sizeY;
	    if (rotation == 0|| rotation == 2) {
	        sizeX = definition.sizeX * -1125834887;
	        sizeY = definition.sizeY * -565161399;
	    } else {
	        sizeX = definition.sizeY * -565161399;
	        sizeY = definition.sizeX * -1125834887;
	    }
	    strategy = Routes.createEntityStrategy(dstX, dstY, sizeX, sizeY, cflag_rot);
	    } else if (Class82_Sub9.isWall(-1976050083 * typedef.type, (byte) 28)) {
	    strategy = Routes.createObjectCloseStrategy(dstX, dstY, 0, 0, typedef, rotation);
	    }
	    else
	    strategy = Routes.createObjectExactStrategy(dstX, dstY, 0, 0, typedef, rotation);
	    Routes.findRoute(dstX, dstY, true, strategy);
	    
	    // TODO check if walls are done in proper way*/

	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("du.jd(").append(')').toString());
	}
    }

    static final boolean findRoute(int dstX, int dstY, boolean findAlternative, PathStrategy strategy) {
	try {
	    int srcX = (Class287.myPlayer.scenePositionXQueue[0]);
	    int srcY = (Class287.myPlayer.scenePositionYQueue[0]);
	    if (srcX < 0 || srcX >= client.aClass283_8716.method2629(-2029828730) || srcY < 0 || srcY >= client.aClass283_8716.method2630(911412275))
		return false;
	    if (dstX < 0 || dstX >= client.aClass283_8716.method2629(-2140756422) || dstY < 0 || dstY >= client.aClass283_8716.method2630(1432313507))
		return false;
	    int steps = client.lastPathStepsCount = (Class298_Sub37.calculateRoute(srcX, srcY, Class287.myPlayer.getSize(), strategy, (client.aClass283_8716.getSceneClipDataPlane(Class287.myPlayer.plane)), findAlternative, client.pathBufferX, client.pathBufferY));
	    if (steps < 1)
		return false;
	    // minimap flag update
	    Class3.anInt62 = client.pathBufferX[steps - 1] * -1129029761;
	    Class3.anInt54 = client.pathBufferY[steps - 1] * -1835291189;
	    Class3.aBoolean63 = false;
	    Class319.method3904(-2054792212); // fire onFlagUpdate event
	    // -------------------
	    return true;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("yt.jz(").append(')').toString());
	}
    }

    public static PathStrategy createObjectCloseStrategy(int dstX, int dstY, int sizeX, int sizeY, GameObjectType type, int rotation) {
	try {
	    Class315.objcheckstrategyclosest.toX = -760677635 * dstX;
	    Class315.objcheckstrategyclosest.toY = 167105303 * dstY;
	    Class315.objcheckstrategyclosest.sizeX = -1544157451 * sizeX;
	    Class315.objcheckstrategyclosest.sizeY = -1468199503 * sizeY;
	    Class315.objcheckstrategyclosest.aClass424_7712 = type;
	    Class315.objcheckstrategyclosest.anInt7711 = rotation * 393356885;
	    return Class315.objcheckstrategyclosest;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("acp.i(").append(')').toString());
	}
    }

    public static PathStrategy createObjectExactStrategy(int dstX, int dstY, int sizeX, int sizeY, GameObjectType type, int rotation) {
	try {
	    Class315.objcheckstrategyexact.toX = dstX * -760677635;
	    Class315.objcheckstrategyexact.toY = 167105303 * dstY;
	    Class315.objcheckstrategyexact.sizeX = sizeX * -1544157451;
	    Class315.objcheckstrategyexact.sizeY = -1468199503 * sizeY;
	    Class315.objcheckstrategyexact.aClass424_7713 = type;
	    Class315.objcheckstrategyexact.anInt7714 = -2142070477 * rotation;
	    return Class315.objcheckstrategyexact;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("if.k(").append(')').toString());
	}
    }

    public static PathStrategy createRectOverlapStrategy(int dstX, int dstY, int sizeX, int sizeY) {
	try {
	    Class315.rectoverlapstrategy.toX = dstX * -760677635;
	    Class315.rectoverlapstrategy.toY = dstY * 167105303;
	    Class315.rectoverlapstrategy.sizeX = -1544157451 * sizeX;
	    Class315.rectoverlapstrategy.sizeY = sizeY * -1468199503;
	    return Class315.rectoverlapstrategy;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("ls.f(").append(')').toString());
	}
    }

    public static PathStrategy createEntityStrategy(int dstX, int dstY, int sizeX, int sizeY, int blockflag) {
	try {
	    Class315.entitystrategy.toX = dstX * -760677635;
	    Class315.entitystrategy.toY = dstY * 167105303;
	    Class315.entitystrategy.sizeX = sizeX * -1544157451;
	    Class315.entitystrategy.sizeY = sizeY * -1468199503;
	    Class315.entitystrategy.anInt7715 = 89792661 * blockflag;
	    return Class315.entitystrategy;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("no.b(").append(')').toString());
	}
    }

    public static PathStrategy createExactStrategy(int dstX, int dstY) {
	try {
	    Class315.exactdestinationstrategy.toX = dstX * -760677635;
	    Class315.exactdestinationstrategy.toY = dstY * 167105303;
	    Class315.exactdestinationstrategy.sizeX = -1544157451; // 1
	    Class315.exactdestinationstrategy.sizeY = -1468199503; // 1
	    return Class315.exactdestinationstrategy;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("op.a(").append(')').toString());
	}
    }

    public static PathStrategy createLobbyNpcsPathStrategy(int dstX, int dstY, int sizeX, int sizeY, int itsactualyblockflagnub) {
	try {
	    Class315.lobbynpcsstrategy.toX = dstX * -760677635;
	    Class315.lobbynpcsstrategy.toY = dstY * 167105303;
	    Class315.lobbynpcsstrategy.sizeX = sizeX * -1544157451;
	    Class315.lobbynpcsstrategy.sizeY = -1468199503 * sizeY;
	    Class315.lobbynpcsstrategy.dkkstopcallingfieldstorandomnamesplskay = -1073204575 * itsactualyblockflagnub;
	    return Class315.lobbynpcsstrategy;
	}
	catch (RuntimeException runtimeexception) {
	    throw Class346.method4175(runtimeexception, new StringBuilder().append("iu.p(").append(')').toString());
	}
    }

    public static void sendLastWalkPathAsStdwalk() {
	if (!Loader.useRoute)
	    return;
	int stepsCount = client.lastPathStepsCount;
	if (stepsCount > 0) { // dont need to waste bw by sending empty packets
	    Class298_Sub36 packet = Class18.method359(OutcommingPacket.WALKING_PACKET, client.aClass25_8711.aClass449_330, (byte) 51);
	    packet.out.writeByte(5 + stepsCount * 2);
	    Class341 class341 = client.aClass283_8716.method2628(681479919);
	    packet.out.writeShort128(class341.gameSceneBaseX * -1760580017);
	    packet.out.write128Byte(Class151.method1644(-545107710) ? 1 : 0, (byte) 1);
	    packet.out.writeShort128(class341.gameSceneBaseY * 283514611);
	    for (int c = stepsCount - 1; c >= 0; c--) {
		packet.out.writeByte(Class285.routeFinderXArray[c]);
		packet.out.writeByte(Class285.routeFinderYArray[c]);
	    }
	    client.aClass25_8711.method390(packet, (byte) -115);
	}
    }

    public static void sendLastWalkPathAsMinimapWalk() {
	if (!Loader.useRoute)
	    return;
	int stepsCount = client.lastPathStepsCount;
	if (stepsCount > 0) { // dont need to waste bw by sending empty packets
	    Class298_Sub36 packet = Class18.method359(OutcommingPacket.MINI_WALKING_PACKET, client.aClass25_8711.aClass449_330, (byte) 28);
	    packet.out.writeByte(5 + stepsCount * 2);
	    Class341 class341 = client.aClass283_8716.method2628(681479919);
	    packet.out.writeShort128(class341.gameSceneBaseX * -1760580017);
	    packet.out.write128Byte(Class151.method1644(-545107710) ? 1 : 0, (byte) 1);
	    packet.out.writeShort128(class341.gameSceneBaseY * 283514611);
	    for (int c = stepsCount - 1; c >= 0; c--) {
		packet.out.writeByte(Class285.routeFinderXArray[c]);
		packet.out.writeByte(Class285.routeFinderYArray[c]);
	    }
	    client.aClass25_8711.method390(packet, (byte) -115);
	}
    }

    public static void sendPlainStdWalk(int dstX, int dstY) {
	if (Loader.useRoute)
	    return;
	Class298_Sub36 packet = Class18.method359(OutcommingPacket.WALKING_PACKET, client.aClass25_8711.aClass449_330, (byte) 51);
	packet.out.writeByte(7);
	Class341 class341 = client.aClass283_8716.method2628(681479919);
	packet.out.writeShort128(class341.gameSceneBaseX * -1760580017);
	packet.out.write128Byte(Class151.method1644(-545107710) ? 1 : 0, (byte) 1);
	packet.out.writeShort128(class341.gameSceneBaseY * 283514611);
	packet.out.writeByte(dstX);
	packet.out.writeByte(dstY);
	client.aClass25_8711.method390(packet, (byte) -115);
    }

    public static void sendPlainMinimapWalk(int dstX, int dstY) {
	if (Loader.useRoute)
	    return;
	Class298_Sub36 packet = Class18.method359(OutcommingPacket.MINI_WALKING_PACKET, client.aClass25_8711.aClass449_330, (byte) 28);
	packet.out.writeByte(7);
	Class341 class341 = client.aClass283_8716.method2628(681479919);
	packet.out.writeShort128(class341.gameSceneBaseX * -1760580017);
	packet.out.write128Byte(Class151.method1644(-545107710) ? 1 : 0, (byte) 1);
	packet.out.writeShort128(class341.gameSceneBaseY * 283514611);
	packet.out.writeByte(dstX);
	packet.out.writeByte(dstY);
	client.aClass25_8711.method390(packet, (byte) -115);
    }

}
