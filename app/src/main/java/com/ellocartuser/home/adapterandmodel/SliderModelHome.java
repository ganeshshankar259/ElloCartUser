package com.ellocartuser.home.adapterandmodel;

import com.ellocartuser.apiservices.model.Banners;

import java.util.List;

public class SliderModelHome
{
        private String description;
        private String imageUrl;
List<Banners> banner;


    public SliderModelHome(String description, String imageUrl, List<Banners> banner) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.banner = banner;
    }

    public List<Banners> getBanner() {
        return banner;
    }

    public void setBanner(List<Banners> banner) {
        this.banner = banner;
    }

    public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

}
