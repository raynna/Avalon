package com.rs.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class Iterate {
	
	
	/**
	 * Iterate over an array of classes.
	 * 
	 * @param path The path to the directory.
	 * @param iterate What to do with the class.
	 */
	@SuppressWarnings("unchecked")
	public static final <T> void classes(String path, Consumer<T> iterate){
		boolean DEBUG = true;
		try {
			final File[] files = getFiles(path);
			if (files == null) {
			if (DEBUG)
				Logger.log("","Failed class iterate - No files exist.");
				return;// here u had return this is correct oh didnt see == null, only saw debug boolean lol
			}
			for (File f : files) {
				if (f == null || !f.getName().endsWith("class")) {
					if (DEBUG)
						Logger.log("","Failed class iterate stage 0.");
					continue;
				}
				final String packagePath = f.getPath().replace("\\", ".").replace("bin/", "");
				final Class<?> c = Class.forName(packagePath.substring(0, packagePath.length() - 6).replace('/', '.').replace("bin.", ""));
				if (c == null || c.isAnonymousClass()) {
					if (DEBUG)
						Logger.log("","Failed class iterate stage 1.");
					continue;
				}
				Object o;
				try {
					o = c.getDeclaredConstructor().newInstance(); //ffs
				} catch (Exception e)  {
					if (DEBUG)
						Logger.log("","Failed class iterate stage 2.");
//					logger.log(e, null);
					continue;
				}
				if (o == null) {
					if (DEBUG)
						Logger.log("","Failed class iterate stage 3.");
					continue;
				}
				iterate.accept((T) o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Get all files presiding in a directory.
	 * 
	 * @param path The path to the directory.
	 * @return the files.
	 */
	public static final File[] getFiles(String path) {
		final File[] files = new File(path).listFiles();
		if (files != null && files.length > 0) {
			final List<File> fileList = new ArrayList<File>();
			for (File f : files) {
				if (f == null)
					continue;
				if (f.isDirectory()) {
					final File[] sfiles = getFiles(f.getPath());
					if (sfiles != null && sfiles.length > 0)
						for (File sf : sfiles)
							fileList.add(sf);
				} else
					fileList.add(f);
			}
			return fileList.toArray(new File[fileList.size()]);
		}
		return null;
	}
	
}
