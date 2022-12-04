package com.example.wallpaperapp.Adapter;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperapp.R;
import com.example.wallpaperapp.models.imageModel;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;




public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public static final int NUMBER_OF_ROWS_AUTO = -1;

    Context context;
    LayoutInflater layoutInflater;
    RecyclerViewItemClickListeners listener;
    List<imageModel> items;
    RecyclerView recyclerView;
    int numberOfRows;
    int rowHeightInPx = 0;
    boolean itemHeightCalculationCompleted = false;

    public ImageAdapter(Context context, List<imageModel> items, RecyclerViewItemClickListeners listener, RecyclerView rv, int rows) {
        super();
        this.context = context;
        this.items = items;
        this.listener = listener;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.recyclerView = rv;
        this.numberOfRows = rows;

        if (this.numberOfRows > 0) {
            this.recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (recyclerView.getMeasuredHeight() > 0) {
                        recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        setRowHeightInPx(recyclerView.getMeasuredHeight() / numberOfRows);
                        itemHeightCalculationCompleted = true;
                        notifyDataSetChanged();
                    }
                }
            });
        } else {
            itemHeightCalculationCompleted = true;
        }

    }

    public int getRowHeightInPx() {
        return rowHeightInPx;
    }

    public void setRowHeightInPx(int rowHeightInPx) {
        this.rowHeightInPx = rowHeightInPx;
    }

    @Override
    public int getItemCount() {
        if (this.items != null && this.itemHeightCalculationCompleted)
            return this.items.size();
        else
            return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View view = this.layoutInflater.inflate(R.layout.item, parent, false);
        if (getRowHeightInPx() > 0) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.height = getRowHeightInPx();
            layoutParams.width = MATCH_PARENT;
            view.setLayoutParams(layoutParams);
        }
        vh = new GeneralViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        imageModel page = items.get(position);

        CardView view = ((CardView) ((GeneralViewHolder) holder).getView());
        ((GeneralViewHolder) holder).getTitle().setText(page.getImage_name());



        ((GeneralViewHolder) holder).getView().setOnClickListener(this);
        ((GeneralViewHolder) holder).getView().setTag(position);
        Sprite wanderingCubes = new WanderingCubes();
        ((GeneralViewHolder) holder).getProgressBar().setIndeterminateDrawable(wanderingCubes);
        Picasso.get().load(page.getImage()).into(((GeneralViewHolder) holder).getImg(), new Callback() {
            @Override
            public void onSuccess() {
                ((GeneralViewHolder) holder).getProgressBar().setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        this.listener.onRecyclerViewItemClick(this.items, position);
    }

    public class GeneralViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView title;
        ImageView img;
        ProgressBar progressBar;

        public GeneralViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            progressBar = itemView.findViewById(R.id.progressBar1);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.latestImage);
        }


        public View getView() {
            return view;
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getImg() {
            return img;
        }

        public ProgressBar getProgressBar() {
            return progressBar;
        }
    }

    public interface RecyclerViewItemClickListeners {
        void onRecyclerViewItemClick(List<imageModel> items, int position);
    }
}
