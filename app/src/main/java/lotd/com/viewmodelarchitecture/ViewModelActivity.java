package lotd.com.viewmodelarchitecture;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lotd.com.viewmodelarchitecture.base.BaseActivity;

/**
 * Created by Dell on 10/6/2017.
 */

public class ViewModelActivity extends BaseActivity<ViewModelActivityMvpView, ViewModelActivityPresenter> implements ViewModelActivityMvpView {

    @BindView(R.id.click_count_text)
    protected TextView clickCountText;
    @BindView(R.id.progressBar)
    protected ProgressBar progressBar;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        displayClickCount(presenter.getCount());

        progressDialog = createProgressDialog(this);

        //this will handle orientation change case
        if(presenter.isProgressbarShowing()){
            presenter.doSomethingAfterDelay(15_000L);
        }

    }

    private ProgressDialog createProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setProgress(0);
        return progressDialog;
    }

    @Override
    protected ViewModelActivityPresenter initPresenter() {
        return new ViewModelActivityPresenter();
    }

    @OnClick(R.id.counterBtn)
    public void incrementClickCount(View button) {
         presenter.setCount();
    }

    @OnClick(R.id.progressButton)
    public void progressBarPercentageCount(View button) {
        presenter.doSomethingAfterDelay(15_000L);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void updateProgressPercentage(int percent) {
        progressDialog.setProgress(percent);
    }

    @Override
    public void displayClickCount(int clickCount) {
        clickCountText.setText(String.valueOf(clickCount));
    }
}
