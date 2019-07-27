package fr.soulao.main.render;

import javax.swing.text.Position;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Quadric;
import org.lwjgl.util.glu.Sphere;

public class Planete extends Quadric {
	public static void drawSphere(float x, float y , float z, float size, int slices, int stacks, float lineWidth, int colour) {
		float red = (float) (colour >> 24 & 255) / 255.0F;
		float green = (float) (colour >> 16 & 255) / 255.0F;
		float blue = (float) (colour >> 8 & 255) / 255.0F;
		float alpha = (float) (colour & 255) / 255.0F;
		Sphere sphere = new Sphere();
		//enable();
		GL11.glColor4f(red, green, blue, alpha);
		GL11.glTranslated(x,y,z);
		GL11.glRotatef(600F, 1, 1, 1);
		GL11.glLineWidth(lineWidth);
		sphere.setDrawStyle(GLU.GLU_SILHOUETTE);
		sphere.draw(size, slices, stacks);
		//disable();
	}
}