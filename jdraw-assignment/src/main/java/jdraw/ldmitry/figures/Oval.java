package jdraw.ldmitry.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.ldmitry.handles.EHandle;
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
		java.awt.geom.Ellipse2D.Float oval = (java.awt.geom.Ellipse2D.Float) shape;
		double x = oval.x;
		double y = oval.y;
		double w = oval.width;
		double h = oval.height;
		List<FigureHandle> handleList = new LinkedList<FigureHandle>();
		NHandle nHandle = new NHandle(this, new Point((int) (x + (w/2)), (int) y));
		SHandle sHandle = new SHandle(this, new Point((int) (x + (w/2)), (int) (y + h)));
		EHandle eHandle = new EHandle(this, new Point((int) (x + w), (int) (y + (h/2))));		
		WHandle wHandle = new WHandle(this, new Point((int) x, (int) (y + (h/2))));
		
		handleList.add(nHandle);
		handleList.add(sHandle);
		handleList.add(eHandle);
		handleList.add(wHandle);
		
		addFigureListener(nHandle);
		addFigureListener(sHandle);
		addFigureListener(eHandle);
		addFigureListener(wHandle);
		
		return handleList;
	}

	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
