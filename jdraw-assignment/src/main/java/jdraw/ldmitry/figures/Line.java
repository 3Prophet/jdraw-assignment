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
import jdraw.ldmitry.handles.EHandle;
import jdraw.ldmitry.handles.NEHandle;
import jdraw.ldmitry.handles.NHandle;
import jdraw.ldmitry.handles.NWHandle;
import jdraw.ldmitry.handles.SEHandle;
import jdraw.ldmitry.handles.SHandle;
import jdraw.ldmitry.handles.SWHandle;
import jdraw.ldmitry.handles.WHandle;

/**
 * Represents lines in JDraw.
 * 
 * @author Dmitry Logvinovich
 *
 */

public class Line extends AbstractFigure {
	/**
	 * Use java.awt.geom.Line2D to save/reuse code.
	 */
	private java.awt.geom.Line2D.Float line;
	
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
		double x1 = line.getX1();
		double y1 = line.getY1();
		double x2 = line.getX2();
		double y2 = line.getY2();
		//if C lies between A and B: ||AB| - |AC| - |BC|| <= |tolerance limit|
		double aB = Math.pow(Math.pow(x2-x1, 2.0)  + Math.pow(y2-y1, 2.0), 0.5);
		double bC = Math.pow(Math.pow(x2- (double) x, 2.0) + 
					Math.pow(y2 - (double) y, 2.0), 0.5);
		double aC = Math.pow(Math.pow(x1 - (double) x, 2.0) +
					Math.pow(y1 - (double) y, 2.0), 0.5);	
		double diffSquare = Math.abs(aB - bC - aC);
		return diffSquare <= 2;
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		double x1 = origin.getX();
		double y1 = origin.getY();
		double x2 = corner.getX();
		double y2 = corner.getY();
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
		
		List<FigureHandle> handleList = new LinkedList<FigureHandle>();
	
		double x1 = line.getX1();
		double y1 = line.getY1();
		double x2 = line.getX2();
		double y2 = line.getY2();
		
		Point p1 = new Point((int) x1, (int) y1);
		Point p2 = new Point((int) x2, (int) y2);
		
		FigureListener fh1;
		FigureListener fh2;
		
		if (x1 < x2) {
			if (y1 < y2) {
				fh1 = new NWHandle(this, p1);
				fh2 = new SEHandle(this, p2);
			} else if (y1 > y2) {
				fh1 = new SWHandle(this, p1);
				fh2 = new NEHandle(this, p2);
			} else {
				fh1 = new WHandle(this, p1);
				fh2 = new EHandle(this, p2);
			}
		} else if (x1 > x2) {
			if (y1 < y2) {
				fh1 = new NEHandle(this, p1);
				fh2 = new SWHandle(this, p2);
			} else if (y1 < y2) {
				fh1 = new SEHandle(this, p1); 
				fh2 = new NWHandle(this, p2);
			} else {
				fh1 = new EHandle(this, p1);
				fh2 = new WHandle(this, p2);
			}
		} else {
			if (y1 < y2) {
				fh1 = new NHandle(this, p1);
				fh2 = new SHandle(this, p2);
			} else {
				fh1  = new SHandle(this, p1);
				fh2 = new NHandle(this, p2);
			}
		}
		addFigureListener(fh1);
		addFigureListener(fh2);
		handleList.add((FigureHandle) fh1);
		handleList.add((FigureHandle) fh2);
		
		return handleList;
	}

	@Override
	public Figure clone() {
		return null;
	}
	
	public static Figure getFigure(float x1, float y1, float x2, float y2) {
		return new Line(x1, y1, x2, y2);
	}
}
