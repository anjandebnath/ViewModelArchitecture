package lotd.com.viewmodelarchitecture;

import android.os.Handler;

import lotd.com.viewmodelarchitecture.base.BasePresenter;

/**
 * Created by Dell on 10/12/2017.
 */

public class ViewModelActivityPresenter extends BasePresenter<ViewModelActivityMvpView> {

    private int count = 0;
    private int progressPercent = 0;
    private long progressTime;
    private boolean isProgressbarShowing;

    public boolean isProgressbarShowing() {
        return isProgressbarShowing;
    }

    public void setProgressbarShowing(boolean progressbarShowing) {
        isProgressbarShowing = progressbarShowing;
    }



    public void setCount() {
        this.count = getCount() + 1;
        getMvpView().displayClickCount(count);
    }

    public int getCount() {
        return count;
    }

    void setProgressPercent(){
      this.progressPercent = getProgressPercent() + 1;
    }

    int getProgressPercent(){
        return progressPercent;
    }



    void doSomethingAfterDelay(long delay){
        progressTime = delay;
        if (isViewAttached()) {
            getMvpView().showProgress();
            setProgressbarShowing(true);
        }

        updateProgress();
    }

    private void updateProgress() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isViewAttached()) {
                    int percent = getProgressPercent();
                    setProgressPercent();
                    getMvpView().updateProgressPercentage(percent);
                    updateProgress();
                }
            }
        },progressTime / 100);

    }
}
