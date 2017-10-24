package lotd.com.viewmodelarchitecture.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Chatikyan on 22.05.2017.
 */

public abstract class BaseFragment<V extends MvpView, P extends BasePresenter<V>>
        extends Fragment implements MvpView {

    protected P presenter;

    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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


    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(presenter!= null){
            presenter.detachView();
        }

    }

    protected abstract P initPresenter();
}
