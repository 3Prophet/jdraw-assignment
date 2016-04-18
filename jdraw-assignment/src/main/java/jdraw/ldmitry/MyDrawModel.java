/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.ldmitry;

import java.util.Collections;
import java.util.LinkedList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;
import jdraw.std.EmptyDrawCommandHandler;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author TODO add your name here
 *
 */
public class MyDrawModel implements DrawModel, FigureListener {
	
	private LinkedList<Figure> figures = new LinkedList<Figure>();
	
	private LinkedList<DrawModelListener> listeners = new LinkedList<DrawModelListener>();
	
	/**
	 * Adds figure to the list of figures. Registers itself as a FigureListener of a figure
	 * @param f figure to be added
	 */
	@Override
	public void addFigure(Figure f) {
		if (f != null) {
			f.addFigureListener(this);
			figures.add(f);
			notifyListeners(f, DrawModelEvent.Type.FIGURE_ADDED);
		}
	}
	
	/**
	 * @return Read-only copy of the list of figures
	 */
	@Override
	public Iterable<Figure> getFigures() {
		return Collections.unmodifiableList(figures); 
	}
	
	/**
	 * Removes a particular figure from the list of observed figures
	 * @param f figure to be removed
	 */
	@Override
	public void removeFigure(Figure f) {
		if (figures.contains(f)) {
			figures.remove(f);
			notifyListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
		}
	}
	
	/**
	 * Adds new listener to the list of listeners
	 * @param listener 
	 */
	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		if (listener != null) {
			listeners.add(listener);
		}
	}
	
	/**
	 * Removes a particular listener from the list of listeners
	 * @param listener to be removed
	 */
	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		// TODO to be implemented  
		System.out.println("StdDrawModel.setFigureIndex has to be implemented");
	}
	
	/**
	 * Removing all the observable figures from the figures list
	 */
	@Override
	public void removeAllFigures() {	
		int size = figures.size();
		while (size != 0) {
			Figure f = figures.pop();
			--size;
			notifyListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
		}
	}
	
	/**
	 * Handler for notification events from observable figures
	 * @param e
	 */
	@Override
	public void figureChanged(FigureEvent e) {
		if (e != null) {
			notifyListeners(e.getFigure(), 
					DrawModelEvent.Type.FIGURE_CHANGED);
		}
	}
	
	/**
	 * Notifies all subscribed DrawModelListeners on a model change
	 * @param e 
	 */
	public void notifyListeners(Figure f, 
			DrawModelEvent.Type type) {
		if (listeners.size() != 0) {
			DrawModelEvent dme = new DrawModelEvent(this, f, type);
			for (DrawModelListener l: listeners) {
				l.modelChanged(dme);				
			}
		}
	}
}
