package org.dzq.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtil {
	//工具类最好将构造方法私有化
	private GameUtil() {
	}
	public static Image getImage(String path) {
		BufferedImage bufferedImage=null;
		try {
			URL url=GameUtil.class.getClassLoader().getResource(path);
			bufferedImage=ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}
}
