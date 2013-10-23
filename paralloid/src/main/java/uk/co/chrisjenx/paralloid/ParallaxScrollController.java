package uk.co.chrisjenx.paralloid;

import android.view.View;
import android.widget.AbsListView;

import uk.co.chrisjenx.paralloid.utils.AbsListViewHelper;

/**
 * Created by chris on 02/10/2013
 * Project: Paralloid
 */
public class ParallaxScrollController<T extends View & Parallaxor> extends ParallaxController<T> implements AbsListView.OnScrollListener {

    public static <T extends View & Parallaxor> ParallaxScrollController wrap(T wrappedView) {
        return new ParallaxScrollController<T>(wrappedView);
    }

    protected ParallaxScrollController(T wrappedView) {
        super(wrappedView);
        init();
    }

    /**
     * Init this controller
     */
    private void init() {
        if (mWrapped == null)
            throw new IllegalArgumentException("The wrapped view cannot be null");

        if (mWrapped instanceof AbsListView) {
            mIgnoreOnScrollListener = true;
            ((AbsListView) mWrapped).setOnScrollListener(this);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    /**
     * Used internally by the AbsListView implementation, calling through to this is unnecessary, the controller
     * will happily set the OnScrollListener
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        final int offsetY = AbsListViewHelper.calculateOffset(view);
        onScrollChanged(getWrapped(), mWrapped.getScrollX(), offsetY, mLastScrollX, mLastScrollY);
    }

}
