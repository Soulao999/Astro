package fr.soulao.main;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.vector.Vector3f;

import fr.soulao.main.render.*;

public class Main {

	public static final String title = "Astro";
	public static final int width = 800;
	public static final int height = 600;
	public static final boolean resizable = true;
	public static final float FRAME_CAP = 60;
	public static boolean running;
	public static final float SUN_RADIUS = 12f;
	final static fr.soulao.main.maths.Vector3f initVect = new fr.soulao.main.maths.Vector3f(0, 0, 0);
	static Camera cam;

	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath",
				new File("D:\\Benoit\\eclipse\\lwjgl-2.9.3\\native\\windows").getAbsolutePath());
		AstroDisplay.create(title, width, height, resizable);
		cam = new Camera(initVect);
		cam.setPespectiveProjection(70.0f, 0.1f, 1000.0f);
		start();

	}

	public static void start() {
		running = true;
		loop();
	}

	public static void exit() {
		AstroDisplay.dispose();
		System.exit(0);
	}

	public static void stop() {
		running = false;
	}

	public static void loop() {

		long lastTickTime = System.nanoTime();
		long lastRenderTime = System.nanoTime();

		double tickTime = 1_000_000_000.0 / 60.0;
		double renderTime = 1_000_000_000.0 / FRAME_CAP;

		int ticks = 0;
		int frames = 0;

		long timer = System.currentTimeMillis();

		while (running) {
			if (AstroDisplay.isClosed())
				stop();
			boolean rendered = false;
			if (System.nanoTime() - lastTickTime > tickTime) {
				update();
				lastTickTime += tickTime;
				ticks++;

			} else if (System.nanoTime() - lastRenderTime > renderTime) {
				render();
				AstroDisplay.Update();
				rendered = true;
				lastRenderTime += renderTime;
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(ticks + "ticks " + frames + "fps");
				ticks = 0;
				frames = 0;
			}

		}
		exit();
	}

	public static void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			Mouse.setGrabbed(false);
		if (Mouse.isButtonDown(0))
			Mouse.setGrabbed(true);
		if (!Mouse.isGrabbed())
			return;
		cam.input();
	}

	public static void render() {
		if (Display.wasResized()){
			glViewport(0,0, Display.getWidth(), Display.getHeight());
		}
		AstroDisplay.ClearBuffers();
		cam.getPespectiveProjection();
		cam.update();

		/*glBegin(GL_TRIANGLES);

		glColor3f(1, 1, 1);

		glVertex3f(0, 0, 0);
		glVertex3f(1, 0, -1);
		glVertex3f(1, 0, 0);

		glVertex3f(0, 0, -1);
		glVertex3f(1, 0, -1);
		glVertex3f(0, 0, 0);

		glVertex3f(0, 0, -1);
		glColor3f(1, 0, 0);
		glVertex3f(0.5f, 1, -0.5f);
		glColor3f(1, 1, 1);
		glVertex3f(1, 0, -1);

		glVertex3f(0, 0, -1);
		glVertex3f(0, 0, 0);
		glColor3f(0, 1, 0);
		glVertex3f(0.5f, 1, -0.5f);
		glColor3f(1, 1, 1);

		glColor3f(0, 0, 1);
		glVertex3f(0.5f, 1, -0.5f);
		glColor3f(1, 1, 1);
		glVertex3f(1, 0, 0);
		glVertex3f(1, 0, -1);

		glVertex3f(0, 0, 0);
		glVertex3f(1, 0, 0);
		glColor3f(1, 1, 0);
		glVertex3f(0.5f, 1, -0.5f);

		glEnd();*/
		
		glColor3f(1, 1, 0);
		Sphere s = new Sphere();
		s.draw(SUN_RADIUS, 5, 5);
	}
}
