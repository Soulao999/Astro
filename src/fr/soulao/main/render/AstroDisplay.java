package fr.soulao.main.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

public class AstroDisplay {

	public static void create(String title, int width, int height, boolean resizable) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.setResizable(resizable);
			Display.create();
			
			glEnable(GL_DEPTH_TEST);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public static void Update() {
		Display.update();
	}

	public static void ClearBuffers() {
		// Clear the screen and depth buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public static boolean isClosed() {
		return Display.isCloseRequested();
	}

	public static void dispose() {
		Display.destroy();
	}
}
