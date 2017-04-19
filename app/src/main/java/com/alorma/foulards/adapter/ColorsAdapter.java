package com.alorma.foulards.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.FulardColor;
import com.alorma.foulards.R;
import java.util.ArrayList;
import java.util.List;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.Holder> {
  private Callback callback;
  private List<FulardColor> items;
  private LayoutInflater inflater;

  public ColorsAdapter(LayoutInflater inflater) {
    this.inflater = inflater;
  }

  @Override
  public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new Holder(inflater.inflate(R.layout.row_color_selector, parent, false));
  }

  @Override
  public void onBindViewHolder(Holder holder, int position) {
    FulardColor color = items.get(position);
    holder.colorName.setText(color.getHumanName());
    holder.colorImage.setBackgroundColor(color.getColorInt());
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void add(List<FulardColor> items) {
    this.items = new ArrayList<>(items);
    notifyDataSetChanged();
  }

  public Callback getCallback() {
    return callback != null ? callback : new Callback.Null();
  }

  public void setCallback(Callback callback) {
    this.callback = callback;
  }

  public class Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.colorImage) ImageView colorImage;
    @BindView(R.id.colorName) TextView colorName;

    public Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      itemView.setOnClickListener(v -> {
        FulardColor color = items.get(getAdapterPosition());
        getCallback().onColorSelected(color);
      });
    }
  }

  public interface Callback {
    void onColorSelected(FulardColor color);

    class Null implements Callback {
      @Override
      public void onColorSelected(FulardColor color) {

      }
    }
  }
}
