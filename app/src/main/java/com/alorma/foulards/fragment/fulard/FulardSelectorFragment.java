package com.alorma.foulards.fragment.fulard;

import android.support.v4.app.Fragment;
import android.view.View;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.view.Fulard;

public class FulardSelectorFragment extends Fragment {

  private Callback callback;

  public Callback getCallback() {
    return callback != null ? callback : new Callback.Null();
  }

  public void setCallback(Callback callback) {
    this.callback = callback;
  }

  public interface Callback {
    void onFulardSelected(FulardType fulardType);

    class Null implements Callback {
      @Override
      public void onFulardSelected(FulardType fulardType) {

      }
    }
  }

  protected class FulardClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
      if (v instanceof Fulard) {
        FulardType fulardType = ((Fulard) v).getFulardType();
        getCallback().onFulardSelected(fulardType);
      }
    }
  }
}
