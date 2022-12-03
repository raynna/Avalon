package com.rs.tools;

import java.io.IOException;

import org.displee.CacheLibrary;
import org.displee.cache.index.archive.Archive;
import org.displee.cache.index.archive.file.File;

public class SpritePacker {

	// 6525, 6526, 6527 specbar green
	// 6531, 6532, 6533 specbar gray
	// 5600, 5601, 5602 specbar background

	// 4134 login screen

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		int archive = 3028;// spriteId
		int secondArchive = 3028;
		boolean LOOP = false;
		CacheLibrary cache718 = new CacheLibrary("data/cache/");
		CacheLibrary cache667 = new CacheLibrary("data/cache639/");
		if (LOOP) {
			for (int i = archive; i <= secondArchive; i++) {
				cache718.getIndex(8).update();
				System.out.println("Updated index 8");
				Archive fromArchive = cache667.getIndex(8).getArchive(i);
				Archive toArchive = cache718.getIndex(8).getArchive(i);
				for (File a : fromArchive.getFiles()) {
					System.out.println(a);
					toArchive.addFile(a);
				}
				cache718.getIndex(8).update();
				System.out.println("Finished packing sprite: " + i + " from 667 cache: " + fromArchive.getId()
						+ " to 718 cache:" + toArchive.getId());
			}
		} else {
			cache718.getIndex(8).update();
			System.out.println("Updated index 8");
			Archive fromArchive = cache667.getIndex(8).getArchive(archive);
			Archive toArchive = cache718.getIndex(8).getArchive(secondArchive);
			for (File a : fromArchive.getFiles()) {
				System.out.println(a);
				toArchive.addFile(a);
			}
			cache718.getIndex(8).update();
			System.out.println("Finished packing sprite: " + archive + " from 667 cache: " + fromArchive.getId()
					+ " to 718 cache:" + toArchive.getId());
		}
	}
}
