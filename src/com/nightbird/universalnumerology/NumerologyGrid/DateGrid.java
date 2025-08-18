package com.nightbird.universalnumerology.NumerologyGrid;

import android.content.Context;
import android.graphics.*;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.nightbird.universalnumerology.Calculation.DateTransformator;
import com.nightbird.universalnumerology.Data.TransformState;

import java.util.List;

public class DateGrid extends BaseGrid implements GestureDetector.OnGestureListener {

	private final OnTransformationRequestListener transformationListener;
	private final GestureDetectorCompat gestureDetector;

	private final float SCREEN_DENSITY = getContext().getResources().getDisplayMetrics().density;
	private final int SWIPE_THRESHOLD_VELOCITY = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
	private final int SWIPE_MIN_DISTANCE = (int) (100 * SCREEN_DENSITY + .5f);

	private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

	private TransformState[] transformStates;

	public DateGrid(Context context, AttributeSet attrs) {
		super(context, attrs);

		gestureDetector = new GestureDetectorCompat(context, this);
		transformationListener = (OnTransformationRequestListener) context;
	}

	public DateGrid(Context context) {
		super(context);

		gestureDetector = new GestureDetectorCompat(context, this);
		transformationListener = (OnTransformationRequestListener) context;
	}


	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		drawText(canvas);

	}

	private void drawDateText(Canvas canvas, float left, float top, float[] advance, int number, int fromPosition, int toPosition) {

		String str = Integer.toString(number);

		List<DateTransformator.TransformType> transformTypes = transformStates[number].getTranformTypeList();
		for (int i = fromPosition; i < toPosition; i++) {
			DateTransformator.TransformType type = transformTypes.get(i);
			if (type == null) {
				canvas.drawText(str, left, top, textPaint);
				left = left + advance[0];
			} else {
				switch (type) {
					case ONE_EIGHT_TWO:
						if (number == 1) {
							textPaint.setAlpha(85);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case ONE_EIGHT_FOUR:
						if (number == 1) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case EIGHT_ONE_TWO:
						if (number == 8) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case EIGHT_ONE_FOUR:
						if (number == 8 || number == 4) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case SIX_SEVEN_TWO:
						if (number == 6  || number == 2) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case SIX_SEVEN_FOUR:
						if (number == 6 || number == 4) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case SEVEN_SIX_TWO:
						if (number == 7  || number == 2) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case SEVEN_SIX_FOUR:
						if (number == 7  || number == 4) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case TWO_FOUR:
						if (number == 2) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case FOUR_TWO:
						if (number == 4) {
							textPaint.setAlpha(125);
						} else {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case FIVE_NINE:
						if (number == 9) {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case NINE_FIVE:
						if (number == 5) {
							textPaint.setColor(Color.CYAN);
						}
						break;
					case SIMULATION:
						textPaint.setColor(Color.GREEN);
						break;
				}

				canvas.drawText(str, left, top, textPaint);
				left = left + advance[0];

				textPaint.setColor(Color.argb(225, 235, 235, 235));
				textPaint.setAlpha(255);
			}
		}
	}

	private void drawText(Canvas canvas) {

		textPaint.setTypeface(Typeface.DEFAULT_BOLD);
		textPaint.setColor(Color.argb(225, 235, 235, 235));

		RectF bounds;
		Rect textRect = new Rect();

		for (int i = 0; i < 9; i++) {

			textPaint.setTextSize(20 * SCREEN_DENSITY);

			int l = transformStates[i + 1].getTranformTypeList().size();
			final String string = buildString(i + 1, l);

			if (string == null) {
				continue;
			}

			final int strLength = string.length();

			// Ha a szöveghossz kisebb mint 3, akkor az 1-esr?l mintavételezz 3-at, mert az a legnagyobb
			// ne az adott szövegr?l, mert nagyon látszik hogy különböz? lenne a méret. Legyen egyforma
			if (strLength < 3) {

				bounds = getGridCells().getCell(i, GridCell.CellType.NumberCell);

				final String nominalString = buildString(1 /*i+ 1*/, 3);

				float wt = textPaint.measureText(nominalString);

				while (wt < bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() + 20f);
					wt = textPaint.measureText(nominalString);
				}

				while (wt > bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() - .5f);
					wt = textPaint.measureText(nominalString);
				}

				textPaint.getTextBounds(nominalString, 0, strLength, textRect);
				wt = textPaint.measureText(string);

				final float posY = bounds.top + textRect.height() / 2 + bounds.height() / 2;
				final float posX = bounds.left + bounds.width() / 2 - wt / 2;

				float textWidth[] = new float[string.length()];
				textPaint.getTextWidths(string, textWidth);
				drawDateText(canvas, posX, posY, textWidth, i + 1, 0, string.length());

			} else if (strLength <= 5 && strLength >= 3) {

				bounds = getGridCells().getCell(i, GridCell.CellType.NumberCell);


				float wt = textPaint.measureText(string);

				while (wt < bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() + 20f);
					wt = textPaint.measureText(string);
				}

				while (wt > bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() - .5f);
					wt = textPaint.measureText(string);
				}

				textPaint.getTextBounds(string, 0, strLength, textRect);
				wt = textPaint.measureText(string);

				final float posY = bounds.top + textRect.height() / 2 + bounds.height() / 2;
				final float posX = bounds.left - wt / 2 + bounds.width() / 2;

				float textWidth[] = new float[string.length()];
				textPaint.getTextWidths(string, textWidth);
				drawDateText(canvas, posX, posY, textWidth, i + 1, 0, string.length());

			} else if (strLength > 5 /* && strLength <= 10*/) {

				bounds = getGridCells().getCell(i, GridCell.CellType.NumberCell);

				String line1 = string.substring(0, 5);
				String line2 = string.substring(5, strLength);

				float wt = textPaint.measureText(line1);

				while (wt < bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() + 20f);
					wt = textPaint.measureText(line1);
				}

				while (wt > bounds.width() - (int) (3 * SCREEN_DENSITY + .5f)) {
					textPaint.setTextSize(textPaint.getTextSize() - .5f);
					wt = textPaint.measureText(line1);
				}

				textPaint.getTextBounds(line1, 0, line1.length(), textRect);
				wt = textPaint.measureText(line1);

				final float posY1 = bounds.top + textRect.height() + bounds.height() / 4 - (int) (5 * SCREEN_DENSITY + .5f);
				final float posX1 = bounds.left - wt / 2 + bounds.width() / 2;

				float textWidth[] = new float[line1.length()];
				textPaint.getTextWidths(line1, textWidth);
				drawDateText(canvas, posX1, posY1, textWidth, i + 1, 0, 5);


				textPaint.getTextBounds(line2, 0, line2.length(), textRect);
				wt = textPaint.measureText(line2);

				final float posY2 = bounds.top + textRect.height() + bounds.height() / 2 + textRect.height() / 4;
				final float posX2 = bounds.left - wt / 2 + bounds.width() / 2;

				textWidth = new float[line2.length()];
				textPaint.getTextWidths(line2, textWidth);
				drawDateText(canvas, posX2, posY2, textWidth, i + 1, 5, 5 + line2.length());

			}

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		return gestureDetector.onTouchEvent(motionEvent);
	}

	@Override
	public boolean onDown(MotionEvent motionEvent) {
		return true;
		//return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void onShowPress(MotionEvent motionEvent) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean onSingleTapUp(MotionEvent motionEvent) {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void onLongPress(MotionEvent motionEvent) {

	}

	@Override
	public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {

		int pos = 0;

		if (motionEvent.getY() > motionEvent2.getY() && Math.abs(motionEvent.getY() - motionEvent2.getY()) > SWIPE_MIN_DISTANCE && Math.abs(v2) > SWIPE_THRESHOLD_VELOCITY) {

			for (int i = 0; i < 9; i++) {
				if (getGridCells().getCell(i).contains(motionEvent.getX(), motionEvent.getY())) {
					pos = i + 1;
				}
			}

			transformationListener.onTransformationRequest(pos, this.getId());

			return true;

		} else if (motionEvent.getY() < motionEvent2.getY() && motionEvent2.getY() - motionEvent.getY() > SWIPE_MIN_DISTANCE && Math.abs(v2) > SWIPE_THRESHOLD_VELOCITY) {

			return true;
		}

		return false;
	}

	public void setupMatrix(int[] matrix, TransformState[] transformStates) {
		super.setupMatrix(matrix);

		this.transformStates = transformStates;

		if (transformStates != null) {
			this.transformStates = TransformState.newInstance(transformStates);
		}
	}
}
