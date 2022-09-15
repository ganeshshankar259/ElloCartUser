package com.ellocartuser.home.adapterandmodel;

public class SliderModel
{
        private String description;
        private String imageUrl;

    public SliderModel(String description, String imageUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
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

