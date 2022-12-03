package com.rs.tools;

import java.io.IOException;

import org.displee.CacheLibrary;
import org.displee.cache.index.archive.Archive;
import org.displee.cache.index.archive.file.File;

public class InterfaceReplacer {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		int interfaceId = 320;//C:/Users/andre/Documents/GitHub/avalon/Avalon - Server/data/cache
		CacheLibrary cache1 = new CacheLibrary("C:/Users/andre/Documents/GitHub/avalon/Avalon - Server/data/cache/");
		CacheLibrary cache2 = new CacheLibrary("C:/Users/andre/Documents/GitHub/avalon/Avalon - Server/data/718cache/");
		cache1.getIndex(3).update();
		System.out.println("Updated index 3");

		Archive toRemove = cache1.getIndex(3).getArchive(interfaceId);
		System.out.println("Removed archive: " + interfaceId + " containing...");
		for (File files : toRemove.getFiles()) {
			if (files == null) {
				cache1.getIndex(3).removeArchive(interfaceId);
				System.out.println("Files were null, removing archive!");
				continue;
			}
			System.out.println(files);
			toRemove.removeFile(files.getId());
		}
		cache1.getIndex(3).update();



		Archive fromArchive = cache2.getIndex(3).getArchive(interfaceId);
		Archive toArchive = cache1.getIndex(3).getArchive(interfaceId);
		for (File a : fromArchive.getFiles()) {
			System.out.println(a);
			toArchive.addFile(a);
		}
		cache1.getIndex(3).update();
		System.out.println("Finished packing all components from:" + fromArchive.getId() + " to:" + toArchive.getId());
	}
}
