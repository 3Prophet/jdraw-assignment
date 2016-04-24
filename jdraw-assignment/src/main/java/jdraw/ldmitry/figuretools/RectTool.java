/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.ldmitry.figuretools;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;
import jdraw.ldmitry.figures.Rect;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler, Dmitry Logvinovich
 */
public class RectTool extends AbstractFigureTool {
  
	/**
	 * Create a new rectangle tool for the given context.
	 * @param context a context to use this tool in.
	 */
	public RectTool(DrawContext context) {
		super(context);
	}

	@Override
	public Figure getNewFigure() {
		return new Rect((int) getAnchor().getX(), (int) getAnchor().getY(), 0, 0);
	}

	/**
	 * Factory method for the RectTool.
	 * @param context context that registers an OvalTool
	 * @return
	 */
	public static DrawTool getDrawTool(DrawContext context) {
		RectTool otool = new RectTool(context);
		otool.setName("Rectangle");
		return otool;
	}
}
