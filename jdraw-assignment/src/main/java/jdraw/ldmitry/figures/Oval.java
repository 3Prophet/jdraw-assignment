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
 * Represents ovals in JDraw.
 * 
 * @author Dmitry Logvinovich
 *
 */
public class Oval extends AbstractAreaFigure {
	
	private LinkedList<FigureHandle> handleList; 

	/**
	 * Create a new oval of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the oval
	 * @param y the y-coordinate of the upper left corner of the oval
	 * @param w the oval's width
	 * @param h the oval's height
	 */
	public Oval(float x, float y, float w, float h) {
		shape = new java.awt.geom.Ellipse2D.Float(x, y, w, h);
		
		handleList = new LinkedList<FigureHandle>();
		
		handleList.add(new Handle(new NHandle(this)));
		handleList.add(new Handle(new SHandle(this)));
		handleList.add(new Handle(new WHandle(this)));
		handleList.add(new Handle(new EHandle(this)));
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
		return handleList;
	}

	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void swapHorizontal() {

		Handle wHandle = (Handle) handleList.get(2);
		Handle eHandle = (Handle) handleList.get(3);
	
		AbstractHandleState wState = wHandle.getState();
		AbstractHandleState eState = eHandle.getState();
		
		eHandle.setState(wState);
		wHandle.setState(eState);
	}

	@Override
	public void swapVertical() {
		
		Handle nHandle = (Handle) handleList.get(0);
		Handle sHandle = (Handle) handleList.get(1);

		
		AbstractHandleState nState = nHandle.getState();
		AbstractHandleState sState = sHandle.getState();
		
		nHandle.setState(sState);
		sHandle.setState(nState);
		
	}
}
