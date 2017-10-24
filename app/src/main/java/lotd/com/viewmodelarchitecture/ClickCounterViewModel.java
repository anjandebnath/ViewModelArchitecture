package lotd.com.viewmodelarchitecture;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

/**
 * Created by Dell on 10/6/2017.
 */

public class ClickCounterViewModel extends AndroidViewModel {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public ClickCounterViewModel(Application application) {
        super(application);
    }
}
