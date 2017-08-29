package co.meechan;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		String targetDirectoryName = "/Users/Daniel/Programming Projects/Rick and Morty Soundboard App/Sounds/Final M4As - AAC HE v2/Sorted - Character";

		System.out.println(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "\n" +
						"<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">"
						+ "\n" +
						"<plist version=\"1.0\">"
		);
		System.out.println("<array>");

		listNestedFilesAndDirectories(targetDirectoryName);

		System.out.println("</array>");
		System.out.println("</plist>");


	}

	public static void listNestedFilesAndDirectories(String directoryName) {
		File directory = new File(directoryName);

		// Get all files from directory
		File[] fList = directory.listFiles();

		for (File file : fList) {


			if (file.isFile()) {
				if (file.getName().startsWith(".")) {
					// Don't do anything; it's a .DS_Store file

				} else {
					System.out.println("\t<dict>");
					System.out.println("\t\t<key>character</key>");
					System.out.println("\t\t<string>" + directory.getName() + "</string>");
					System.out.println("\t\t<key>sound</key>");
					System.out.println("\t\t<string>" + file.getName().replace(".m4a", "") + "</string>");
					System.out.println("\t</dict>");

				}

			} else if (file.isDirectory()) {
				listNestedFilesAndDirectories(file.getAbsolutePath());

			}

		}
	}

    /*
    Target .plist file format:

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
    <plist version="1.0">
    <array>
        <dict>
            <key>sound</key>
            <string>SOUND_FILE_NAME</string>
            <key>character</key>
            <string>PARENT_FOLDER_NAME</string>
        </dict>
    </array>
    </plist>

    */

}
