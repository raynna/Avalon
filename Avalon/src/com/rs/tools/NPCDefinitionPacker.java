package com.rs.tools;

import java.io.IOException;

import org.displee.CacheLibrary;
import org.displee.cache.index.archive.Archive;
import org.displee.cache.index.archive.file.File;

public class NPCDefinitionPacker {

	private static int NPC_TO_PACK = 0;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		CacheLibrary library = new CacheLibrary("data/cache/");
		library.getIndex(18).update();
		Archive archive = library.getIndex(18).getArchive(NPC_TO_PACK >>> 134238215);
		File file = archive.getFile(NPC_TO_PACK & 0x7f);
		for (byte files : file.getData())
			System.out.println(files);
		System.out.println(file.getInfo());
		System.out.println(file.getData());
		library.getIndex(18).update();
		System.out.println("Finished");
	}
}
