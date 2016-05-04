/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.ldmitry.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.ldmitry.handles.AbstractHandleState;
import jdraw.ldmitry.handles.EHandle;
import jdraw.ldmitry.handles.Handle;
import jdraw.ldmitry.handles.NEHandle;
import jdraw.ldmitry.handles.NHandle;
import jdraw.ldmitry.handles.NWHandle;
import jdraw.ldmitry.handles.SEHandle;
import jdraw.ldmitry.handles.SHandle;
import jdraw.ldmitry.handles.SWHandle;
import jdraw.ldmitry.handles.WHandle;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler, Dmitry Logvinovich
 *
 */
public class Rect extends AbstractAreaFigure {
	
	private LinkedList<FigureHandle> handleList; 
	
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	public Rect(int x, int y, int w, int h) {
		shape = new java.awt.Rectangle(x, y, w, h);
		
		handleList = new LinkedList<FigureHandle>();
		
		handleList.add(new Handle(new NHandle(this)));
		handleList.add(new Handle(new SHandle(this)));
		handleList.add(new Handle(new WHandle(this)));
		handleList.add(new Handle(new EHandle(this)));
		handleList.add(new Handle(new NWHandle(this)));
		handleList.add(new Handle(new NEHandle(this)));
		handleList.add(new Handle(new SWHandle(this)));
		handleList.add(new Handle(new SEHandle(this)));	
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
		return handleList;
	}
	
	public void swapHorizontal() {
		
		Handle wHandle = (Handle) handleList.get(2);
		Handle eHandle = (Handle) handleList.get(3);
		Handle nwHandle = (Handle) handleList.get(4);
		Handle neHandle = (Handle) handleList.get(5);
		Handle swHandle = (Handle) handleList.get(6);
		Handle seHandle = (Handle) handleList.get(7);
		
		AbstractHandleState wState = wHandle.getState();
		AbstractHandleState eState = eHandle.getState();
		AbstractHandleState nwState = nwHandle.getState();
		AbstractHandleState neState = neHandle.getState();
		AbstractHandleState swState = swHandle.getState();
		AbstractHandleState seState = seHandle.getState();
		
		eHandle.setState(wState);
		wHandle.setState(eState);
		nwHandle.setState(neState);
		neHandle.setState(nwState);
		swHandle.setState(seState);
		seHandle.setState(swState);
		
	}
	
	public void swapVertical() {
		
		Handle nHandle = (Handle) handleList.get(0);
		Handle sHandle = (Handle) handleList.get(1);
		Handle nwHandle = (Handle) handleList.get(4);
		Handle neHandle = (Handle) handleList.get(5);
		Handle swHandle = (Handle) handleList.get(6);
		Handle seHandle = (Handle) handleList.get(7);
		
		AbstractHandleState nState = nHandle.getState();
		AbstractHandleState sState = sHandle.getState();
		AbstractHandleState nwState = nwHandle.getState();
		AbstractHandleState neState = neHandle.getState();
		AbstractHandleState swState = swHandle.getState();
		AbstractHandleState seState = seHandle.getState();
		
		nHandle.setState(sState);
		sHandle.setState(nState);
		nwHandle.setState(swState);
		neHandle.setState(seState);
		swHandle.setState(nwState);
		seHandle.setState(neState);

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
