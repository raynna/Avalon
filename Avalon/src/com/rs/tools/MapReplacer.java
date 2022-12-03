package com.rs.tools;

import java.io.IOException;

import org.displee.CacheLibrary;
import org.displee.cache.index.archive.Archive;
import org.displee.cache.index.archive.file.File;

public class MapReplacer {

	// 6525, 6526, 6527 specbar green
	// 6531, 6532, 6533 specbar gray
	// 5600, 5601, 5602 specbar background

	// 4134 login screen

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		CacheLibrary cache718 = new CacheLibrary("data/cache/");
		CacheLibrary cache667 = new CacheLibrary("data/cache639/");
		cache718.getIndex(5).update();
		System.out.println("Updated index 5");
		int regionX = 49;
		int regionY = 54;
		int archiveId = cache667.getIndex(5).getArchiveId("m" + regionX + "_" + regionY);
		Archive fromArchive = cache667.getIndex(5).getArchive(archiveId);
		Archive toArchive = cache718.getIndex(5).getArchive(archiveId);
		for (File a : fromArchive.getFiles()) {
			if (a == null) {
				System.out.println("failed file: " + archiveId);
				continue;
			}
			System.out.println("Archive: " + archiveId);
			System.out.println(a);
			toArchive.removeFile(a.getId());
			toArchive.addFile(a);
		}
		cache718.getIndex(5).update();
		System.out.println("Updated index 5");
		System.out.println("Finished packing map: 639 cache: " + fromArchive.getId()
				+ " to 718 cache:" + toArchive.getId());
	}

}
