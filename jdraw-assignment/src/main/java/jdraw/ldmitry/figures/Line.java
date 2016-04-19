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
 * Represents lines in JDraw.
 * 
 * @author Dmitry Logvinovich
 *
 */

public class Line implements Figure {
	/**
	 * Use java.awt.geom.Line2D to save/reuse code.
	 */
	private java.awt.geom.Line2D.Float line;
	
	private LinkedList<FigureListener> listeners = new LinkedList<FigureListener>();
	/**
	 * Create a new line of the given dimension.
	 * @param x1 the X-coordinate of the start point
	 * @param y1 the Y-coordinate of the start point
	 * @param x2 the X-coordinate of the end point
	 * @param y2 the Y-coordinate of the end point
	 */
	public Line(float x1, float y1, float x2, float y2) {
		line = new java.awt.geom.Line2D.Float(x1, y1, x2, y2);
	}
	/**
	 * Draw the line to the given graphics context.
	 * @param g graphics context to be used for the drawing
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine((int) line.getX1(), (int) line.getY1(),
				(int) line.getX2(), (int) line.getY2());	
	}
	
	/**
	 * Move line by a given increment and notify registered FigureListeners.
	 * @param dx increment along X-axis
	 * @param dy increment along Y-axis
	 */
	@Override
	public void move(int dx, int dy) {
		line.setLine(line.getX1() + (double) dx, line.getY1() + (double) dy,
					 line.getX2() + (double) dx, line.getY2() + (double) dy);
		notifyFigureListeners();
	}

	@Override
	public boolean contains(int x, int y) {
		return false;
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		double x1 = origin.getX();
		double y1 = origin.getY();
		double x2 = corner.getX();
		double y2 = corner.getY();
		
        if (x2 < x1) {
            double t = x1;
            x1 = x2;
            x2 = t;
        }
        if (y2 < y1) {
            double t = y1;
            y1 = y2;
            y2 = t;
        }
        line.setLine(x1, y1, x2, y2);
		notifyFigureListeners();
	}
	/**
	 * Returns bounding rectangle of the line.
	 * @return instance of java.awt.Rectangle
	 */
	@Override
	public Rectangle getBounds() {
		return line.getBounds();
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
		return null;
	}
	
	public void notifyFigureListeners() {
		// TODO Here is a code duplication for different figures. Implement Observable interface.
		for (FigureListener fl: listeners) {
			fl.figureChanged(new FigureEvent(this));
		}
	}
}
