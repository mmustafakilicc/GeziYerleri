package com.klc.geziyerleri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.klc.geziyerleri.R;
import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.Region;

import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.ViewHolder> {

    private final Context context;

    private final List<Region> regionList;

    private final RegionClickListener listener;

    public RegionAdapter(Context context, List<Region> regionList, RegionClickListener listener) {
        this.context = context;
        this.regionList = regionList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RegionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list_region, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionAdapter.ViewHolder holder, int position) {
        holder.bind(regionList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return regionList.size();
    }

    public interface RegionClickListener {
        void onClick(Region region);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewRegionItemImage;
        private TextView textViewRegionItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRegionItemName = itemView.findViewById(R.id.textViewRegionItemName);
            imageViewRegionItemImage = itemView.findViewById(R.id.imageViewRegionItemImage);
        }

        public void bind(Region region, RegionClickListener listener) {
            textViewRegionItemName.setText(region.getName());
            Glide.with(context).load(Constants.WebService.IMAGE_URL + region.getImageUrl()).into(imageViewRegionItemImage);

            textViewRegionItemName.setOnClickListener(v -> listener.onClick(region));
        }
    }
}
