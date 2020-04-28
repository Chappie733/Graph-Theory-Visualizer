package Utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static BufferedImage LoadImage(String path) {
		try {
			return ImageIO.read(Class.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String LoadFile(String path) {
		File file = new File(path);
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String curr_line;
			while ((curr_line = br.readLine()) != null) {
				sb.append(curr_line + "\n");
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find file: " + path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("An IOException has occured whilst loading file: " + path);
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public void WriteToFile(String path, String content) {
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
