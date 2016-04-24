package jdraw.ldmitry.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

/**
 * Represents ovals in JDraw.
 * 
 * @author Dmitry Logvinovich
 *
 */
public class Oval extends AbstractAreaFigure {

	/**
	 * Create a new oval of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the oval
	 * @param y the y-coordinate of the upper left corner of the oval
	 * @param w the oval's width
	 * @param h the oval's height
	 */
	public Oval(float x, float y, float w, float h) {
		shape = new java.awt.geom.Ellipse2D.Float(x, y, w, h);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) shape.getX(), (int) shape.getY(), 
							 (int) shape.getWidth(), (int) shape.getHeight());
		g.setColor(Color.BLACK);
		g.drawOval((int) shape.getX(), (int) shape.getY(), 
				 (int) shape.getWidth(), (int) shape.getHeight());	
	}

	@Override
	public void move(int dx, int dy) {
		((java.awt.geom.Ellipse2D.Float) shape).x += (float) dx;
		((java.awt.geom.Ellipse2D.Float) shape).y += (float) dy;
		notifyFigureListeners();
	}

	@Override
	public List<FigureHandle> getHandles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
