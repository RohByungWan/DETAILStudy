package com.example.detailstudy;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

        private Context context;
        private List<Uri> imageUris;

        // 생성자
    public ImageAdapter(Context context, List<Uri> imageUris) {
            this.context = context;
            this.imageUris = imageUris;
        }

        // ViewHolder 생성
        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // item_image.xml 레이아웃을 인플레이트하여 ViewHolder에 전달
            View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
            return new ImageViewHolder(view);
        }

        // 데이터 바인딩
        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            // Glide로 이미지를 로드하여 ImageView에 표시
            Glide.with(context)
                    .load(imageUris.get(position))
                    .override(250, 250) // 원하는 크기 (500x500px)로 조정
                    .centerCrop()  // 이미지 자르기
                    .into(holder.imageView);
        }

        // 아이템 개수 반환
        @Override
        public int getItemCount() {
            return imageUris.size();
        }

        // ViewHolder 클래스
        public static class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public ImageViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView); // item_image.xml에서 ImageView 찾기
            }
        }
    }
