package co.meechan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	static StringBuilder output = new StringBuilder();

	public static void main(String[] args) {

		String targetDirectoryName = "/Users/Daniel/Programming Projects/Rick and Morty Soundboard App/Sounds/Final M4As - AAC HE v2/Sorted - Character";

		output.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n");
		output.append("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">" + "\n");
		output.append("<plist version=\"1.0\">" + "\n");
		output.append( "<array>" + "\n");

		listNestedFilesAndDirectories(targetDirectoryName, "");

		output.append("</array>" + "\n");
		output.append("</plist>" + "\n");

		String finalOutput = output.toString();

		try {
			Files.write(Paths.get(targetDirectoryName + "/trackData.plist"), finalOutput.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void listNestedFilesAndDirectories(String directoryName, String parentDirectoryName) {
		File directory = new File(directoryName);

		// Get all files from directory
		File[] fList = directory.listFiles();

		for (File file : fList) {

			if (file.isFile()) {
				if (file.getName().startsWith(".")) {
					// Don't do anything; it's a .DS_Store file

				} else {
					output.append("\t<dict>" + "\n");
					output.append("\t\t<key>category</key>" + "\n");
					output.append("\t\t<string>" + parentDirectoryName + "</string>" + "\n");
					output.append("\t\t<key>character</key>" + "\n");
					output.append("\t\t<string>" + directory.getName() + "</string>" + "\n");
					output.append("\t\t<key>sound</key>" + "\n");
					output.append("\t\t<string>" + file.getName().replace(".m4a", "") + "</string>" + "\n");
					output.append("\t</dict>" + "\n");

				}

			} else if (file.isDirectory()) {
				String myParentDirectoryName = parentDirectoryName;

				if (myParentDirectoryName.equals("")) {
					// Has no parent; is a top level folder
					myParentDirectoryName = file.getName();

				}

				listNestedFilesAndDirectories(file.getAbsolutePath(), myParentDirectoryName);

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
            <key>category</key>
            <string>TOP_LEVEL_FOLDER_NAME</string>
            <key>character</key>
            <string>PARENT_FOLDER_NAME</string>
            <key>sound</key>
            <string>SOUND_FILE_NAME</string>
        </dict>
    </array>
    </plist>

    */

}
