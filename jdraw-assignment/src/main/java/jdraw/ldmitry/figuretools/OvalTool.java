package jdraw.ldmitry.figuretools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.ldmitry.figures.Oval;

public class OvalTool implements DrawTool {
	/** 
	 * the image resource path. 
	 */
	private static final String IMAGES = "/images/";
	/**
	 * The context we use for drawing.
	 */
	private DrawContext context;

	/**
	 * The context's view. This variable can be used as a shortcut, i.e.
	 * instead of calling context.getView().
	 */
	private DrawView view;

	/**
	 * Temporary variable. During oval creation (during a
	 * mouse down - mouse drag - mouse up cycle) this variable refers
	 * to the new oval that is inserted.
	 */
	private Oval newOval = null;

	/**
	 * Temporary variable.
	 * During oval creation this variable refers to the point the
	 * mouse was first pressed.
	 */
	private Point anchor = null;
	/**
	 * Create a new oval tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public OvalTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}

	@Override
	public void activate() {
		this.context.showStatusText("Oval Mode");
	}

	@Override
	public void deactivate() {
		this.context.showStatusText("");	
	}

	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newOval != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newOval = new Oval(x, y, 0, 0);
		view.getModel().addFigure(newOval);	
	}

	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		newOval.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newOval.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);	
	}

	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		newOval = null;
		anchor = null;
		this.context.showStatusText("Oval Mode");	
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + "oval.png"));
	}

	@Override
	public String getName() {
		return "Oval";
	}
}
