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
import com.klc.geziyerleri.data.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private final Context context;

    private final List<Place> placeList;

    private final ItemClickListener listener;

    public PlaceAdapter(Context context, List<Place> placeList, ItemClickListener listener) {
        this.context = context;
        this.placeList = placeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list_region, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceAdapter.ViewHolder holder, int position) {
        holder.bind(placeList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public interface ItemClickListener {
        void OnClick(Place place);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewRegionItemImage;
        private TextView textViewRegionItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRegionItemName = itemView.findViewById(R.id.textViewRegionItemName);
            imageViewRegionItemImage = itemView.findViewById(R.id.imageViewRegionItemImage);
        }

        public void bind(Place place, ItemClickListener listener) {
            textViewRegionItemName.setText(place.getName());
            textViewRegionItemName.setOnClickListener(v -> listener.OnClick(place));
            Glide.with(context).load(Constants.WebService.IMAGE_URL + place.getImageUrl()).into(imageViewRegionItemImage);
        }
    }
}
