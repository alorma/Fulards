package com.alorma.foulards.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.AgrupamentItemViewModel;
import com.alorma.foulards.FulardType;
import com.alorma.foulards.R;
import com.alorma.foulards.ui.drawable.AlphaPatternDrawable;
import com.alorma.foulards.view.Fulard;
import com.alorma.foulards.view.FulardCustomization;
import java.util.ArrayList;
import java.util.List;

public class AgrupamentsAdapter extends RecyclerView.Adapter<AgrupamentsAdapter.Holder> {

  private static final int VIEW_TYPE_FULARD_1 = 1;
  private static final int VIEW_TYPE_FULARD_2 = 2;
  private static final int VIEW_TYPE_FULARD_3 = 3;
  private static final int VIEW_TYPE_FULARD_4 = 4;
  private static final int VIEW_TYPE_FULARD_5 = 5;
  private static final int VIEW_TYPE_FULARD_6 = 6;
  private static final int VIEW_TYPE_FULARD_7 = 7;
  private static final int VIEW_TYPE_FULARD_8 = 8;
  private static final int VIEW_TYPE_FULARD_9 = 9;
  private static final int VIEW_TYPE_FULARD_10 = 10;
  private static final int VIEW_TYPE_FULARD_11 = 11;
  private static final int VIEW_TYPE_FULARD_12 = 12;
  private static final int VIEW_TYPE_FULARD_13 = 13;
  private static final int VIEW_TYPE_FULARD_14 = 14;

  private List<AgrupamentItemViewModel> items;
  private LayoutInflater inflater;

  public AgrupamentsAdapter(LayoutInflater inflater) {
    this.inflater = inflater;
    this.items = new ArrayList<>();
  }

  @Override
  public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new Holder(inflater.inflate(getLayout(viewType), parent, false));
  }

  @LayoutRes
  private int getLayout(int viewType) {
    switch (viewType) {
      case VIEW_TYPE_FULARD_1:
        return R.layout.row_fulard_1;
      case VIEW_TYPE_FULARD_2:
        return R.layout.row_fulard_2;
      case VIEW_TYPE_FULARD_3:
        return R.layout.row_fulard_3;
      case VIEW_TYPE_FULARD_4:
        return R.layout.row_fulard_4;
      case VIEW_TYPE_FULARD_5:
        return R.layout.row_fulard_5;
      case VIEW_TYPE_FULARD_6:
        return R.layout.row_fulard_6;
      case VIEW_TYPE_FULARD_7:
        return R.layout.row_fulard_7;
      case VIEW_TYPE_FULARD_8:
        return R.layout.row_fulard_8;
      case VIEW_TYPE_FULARD_9:
        return R.layout.row_fulard_9;
      case VIEW_TYPE_FULARD_10:
        return R.layout.row_fulard_10;
      case VIEW_TYPE_FULARD_11:
        return R.layout.row_fulard_11;
      case VIEW_TYPE_FULARD_12:
        return R.layout.row_fulard_12;
      case VIEW_TYPE_FULARD_13:
        return R.layout.row_fulard_13;
      case VIEW_TYPE_FULARD_14:
        return R.layout.row_fulard_14;
      default:
        return R.layout.row_fualrd_empty;
    }
  }

  @Override
  public void onBindViewHolder(Holder holder, int position) {
    AgrupamentItemViewModel model = items.get(position);
    FulardCustomization fulardCustomization = model.getFulardCustomization();
    if (holder.fulard != null) {
      holder.fulard.fill(fulardCustomization);
    }
    if (holder.agrupamentName != null) {
      holder.agrupamentName.setText(model.getName());
    }
  }

  public void add(AgrupamentItemViewModel model) {
    this.items.add(model);
    notifyDataSetChanged();
  }

  @Override
  public int getItemViewType(int position) {
    FulardType fulardType = items.get(position).getFulardCustomization().getType();
    switch (fulardType) {
      case fulard_1:
        return VIEW_TYPE_FULARD_1;
      case fulard_2:
        return VIEW_TYPE_FULARD_2;
      case fulard_3:
        return VIEW_TYPE_FULARD_3;
      case fulard_4:
        return VIEW_TYPE_FULARD_4;
      case fulard_5:
        return VIEW_TYPE_FULARD_5;
      case fulard_6:
        return VIEW_TYPE_FULARD_6;
      case fulard_7:
        return VIEW_TYPE_FULARD_7;
      case fulard_8:
        return VIEW_TYPE_FULARD_8;
      case fulard_9:
        return VIEW_TYPE_FULARD_9;
      case fulard_10:
        return VIEW_TYPE_FULARD_10;
      case fulard_11:
        return VIEW_TYPE_FULARD_11;
      case fulard_12:
        return VIEW_TYPE_FULARD_12;
      case fulard_13:
        return VIEW_TYPE_FULARD_13;
      case fulard_14:
        return VIEW_TYPE_FULARD_14;
    }
    return super.getItemViewType(position);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public class Holder extends RecyclerView.ViewHolder {
    @Nullable @BindView(R.id.fulard) Fulard fulard;
    @Nullable @BindView(R.id.agrupamentName) TextView agrupamentName;

    public Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      int dimen = fulard.getResources().getDimensionPixelOffset(R.dimen.alpha_rectangle_dimen);
      fulard.setBackground(new AlphaPatternDrawable(dimen));
    }
  }
}
