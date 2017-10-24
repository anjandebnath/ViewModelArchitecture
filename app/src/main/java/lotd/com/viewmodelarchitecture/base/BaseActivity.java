package lotd.com.viewmodelarchitecture.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

/**
 * Abstract activity that every other Activity in this application must implement.
 */
public abstract class BaseActivity<V extends MvpView, P extends BasePresenter<V>>
        extends AppCompatActivity implements MvpView {

    protected P presenter;

    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseViewModel<V, P> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        boolean isPresenterCreated = false;
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(initPresenter());
            isPresenterCreated = true;
        }
        presenter = viewModel.getPresenter();
        presenter.attachView((V) this);

        if (isPresenterCreated)
            presenter.onPresenterCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    protected abstract P initPresenter();

}
