package com.nightbird.universalnumerology.NumerologyGrid;

import android.graphics.RectF;


public final class GridCell {

	private final Cell cells[];

	public GridCell() {

		cells = new Cell[9];
		for (int i = 0; i < 9; i++) {
			cells[i] = new Cell();
		}
	}

	public void setCell(int cellNumber, RectF rect) {
		cells[cellNumber].setRect(rect);
	}

	public RectF getCell(int cellNumber) {

		int cell = 0;

		switch (cellNumber) {
			case 0:
				cell = 0;
				break;
			case 1:
				cell = 3;
				break;
			case 2:
				cell = 6;
				break;
			case 3:
				cell = 1;
				break;
			case 4:
				cell = 4;
				break;
			case 5:
				cell = 7;
				break;
			case 6:
				cell = 2;
				break;
			case 7:
				cell = 5;
				break;
			case 8:
				cell = 8;
				break;

		}

		return cells[cell].getRect();
	}

	public RectF getCell(int cellNumber, CellType cellType) {

		switch (cellType) {
			case NumberCell: {
				final RectF rect = getCell(cellNumber);
				return new RectF(rect.left, rect.top, rect.right, rect.bottom - (rect.bottom - rect.top) * .2f);
			}
			case StatusCell: {
				final RectF rect = getCell(cellNumber);
				return new RectF(rect.left, rect.top + (rect.bottom - rect.top) * .8f, rect.right, rect.bottom);
				//return new RectF(rect.left, rect.top, rect.right, rect.bottom);
			}
			default:
				throw new IllegalArgumentException();
		}
	}

	public enum CellType {
		NumberCell, StatusCell
	}

	private class Cell {

		private RectF rect;

		Cell() {
			rect = new RectF();
		}

		public RectF getRect() {
			return rect;
		}

		public void setRect(RectF rect) {
			this.rect = new RectF(rect);
		}
	}
}
