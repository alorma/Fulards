package com.alorma.foulards.adapter;

import android.graphics.Color;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.foulards.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.Holder> {
  private List<Pair<String, String>> items;
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
    Pair<String, String> pair = items.get(position);
    holder.colorName.setText(pair.first);
    int color = Color.parseColor(pair.second);
    holder.colorImage.setBackgroundColor(color);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void add(Map<String, String> map) {
    items = new ArrayList<>();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      items.add(new Pair<>(entry.getKey(), entry.getValue()));
    }
    notifyDataSetChanged();
  }

  public class Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.colorImage) ImageView colorImage;
    @BindView(R.id.colorName) TextView colorName;

    public Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
