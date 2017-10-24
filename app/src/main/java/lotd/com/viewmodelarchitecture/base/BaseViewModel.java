package lotd.com.viewmodelarchitecture.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

/**
 * Created by Dell on 10/9/2017.
 */

public class BaseViewModel<V extends MvpView, P extends Presenter<V>> extends AndroidViewModel {

    /**
     *  The ViewModel is automatically retained during configuration changes
     *  so the data it holds is immediately available to the next activity or
     *  fragment instance.
     *
     *  This will help us not to initialize the Presenter every time.
     * It will receive and return our presenter’s object.
     */


    private P presenter;

    public BaseViewModel(Application application) {
        super(application);
    }

    void setPresenter(P presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    P getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        presenter.onPresenterDestroy();
        presenter = null;
    }
}
