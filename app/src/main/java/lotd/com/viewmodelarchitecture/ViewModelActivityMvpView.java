package lotd.com.viewmodelarchitecture;

import lotd.com.viewmodelarchitecture.base.MvpView;

/**
 * Created by Dell on 10/12/2017.
 */

public interface ViewModelActivityMvpView extends MvpView {

    void showProgress();

    void hideProgress();

    void updateProgressPercentage(int percent);

    void displayClickCount(int clickCount);
}
