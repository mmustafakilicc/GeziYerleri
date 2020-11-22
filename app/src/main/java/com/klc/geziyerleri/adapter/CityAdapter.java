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
import com.klc.geziyerleri.data.City;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private final Context context;

    private final List<City> regionList;

    private final ItemClickListener listener;

    public CityAdapter(Context context, List<City> regionList, ItemClickListener listener) {
        this.context = context;
        this.regionList = regionList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list_region, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        holder.bind(regionList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return regionList.size();
    }

    public interface ItemClickListener {
        void OnClick(City city);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewRegionItemImage;
        private TextView textViewRegionItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRegionItemName = itemView.findViewById(R.id.textViewRegionItemName);
            imageViewRegionItemImage = itemView.findViewById(R.id.imageViewRegionItemImage);
        }

        public void bind(City city, ItemClickListener listener) {
            textViewRegionItemName.setText(city.getName());
            textViewRegionItemName.setOnClickListener(v -> listener.OnClick(city));
            Glide.with(context).load(Constants.WebService.IMAGE_URL + city.getImageUrl()).into(imageViewRegionItemImage);
        }
    }
}
