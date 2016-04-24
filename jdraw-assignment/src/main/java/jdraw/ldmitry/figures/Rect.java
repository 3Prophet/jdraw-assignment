/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.ldmitry.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Rect extends AbstractAreaFigure {
	
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	public Rect(int x, int y, int w, int h) {
		shape = new java.awt.Rectangle(x, y, w, h);
	}

	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		java.awt.Rectangle rectangle = (java.awt.Rectangle) shape;
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, 
							 rectangle.width, rectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(rectangle.x, rectangle.y, 
							 rectangle.width, rectangle.height);
	}

	@Override
	public void move(int dx, int dy) {
		((java.awt.Rectangle) shape).setLocation((int) shape.getX() + dx,
				(int) shape.getY() + dy);
		notifyFigureListeners();
	}

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */	
	public List<FigureHandle> getHandles() {
		return null;
	}
	
	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Factory method
	 * @return new instance of Rect
	 */
	public static Figure getFigure(int x, int y, int w, int h) {
		Rect r = new Rect(x, y, w, h); 
		return r;
	}
}
