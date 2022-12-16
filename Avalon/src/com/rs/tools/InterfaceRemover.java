package com.rs.tools;

import java.io.IOException;

import org.displee.CacheLibrary;
import org.displee.cache.index.archive.Archive;
import org.displee.cache.index.archive.file.File;

public class InterfaceRemover {

	private static int INTERFACE_TO_REMOVE = 320;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		CacheLibrary library = new CacheLibrary("data/cache/");
		Archive toRemove = library.getIndex(3).getArchive(INTERFACE_TO_REMOVE);
		System.out.println("Removed archive: " + INTERFACE_TO_REMOVE + " containing...");
		for (File files : toRemove.getFiles()) {
			if (files == null) {
				library.getIndex(3).removeArchive(INTERFACE_TO_REMOVE);
				System.out.println("Files were null, removing archive!");
				continue;
			}
			System.out.println(files);
			toRemove.removeFile(files.getId());
		}
		library.getIndex(3).update();
		System.out.println("Finished");
	}
}
