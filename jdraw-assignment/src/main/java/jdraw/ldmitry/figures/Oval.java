package jdraw.ldmitry.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
/**
 * Represents ovals in JDraw.
 * 
 * @author Dmitry Logvinovich
 *
 */
public class Oval implements Figure {

	private java.awt.geom.Ellipse2D.Float ellipse;
	
	private LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	/**
	 * Create a new oval of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the oval
	 * @param y the y-coordinate of the upper left corner of the oval
	 * @param w the oval's width
	 * @param h the oval's height
	 */
	public Oval(float x, float y, float w, float h) {
		ellipse = new java.awt.geom.Ellipse2D.Float(x, y, w, h);
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) ellipse.x, (int) ellipse.y, 
							 (int) ellipse.width, (int) ellipse.height);
		g.setColor(Color.BLACK);
		g.drawOval((int) ellipse.x, (int) ellipse.y, 
				 (int) ellipse.width, (int) ellipse.height);	
	}

	@Override
	public void move(int dx, int dy) {
		ellipse.x += (float) dx;
		ellipse.y += (float) dy;
		notifyFigureListeners();
	}

	@Override
	public boolean contains(int x, int y) {
		return ellipse.contains((double) x, (double) y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		ellipse.setFrameFromDiagonal(origin, corner);
		notifyFigureListeners();
		
	}

	@Override
	public Rectangle getBounds() {
		return ellipse.getBounds();
	}

	@Override
	public List<FigureHandle> getHandles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFigureListener(FigureListener listener) {
		if (listener != null) {
			listeners.add(listener);
		}
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}

	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void notifyFigureListeners() {
		for (FigureListener fl: listeners) {
			fl.figureChanged(new FigureEvent(this));
		}
	}

}
