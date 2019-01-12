
package com.rajinder.noticeboard.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PostListModel implements Serializable{

    @SerializedName("post_id")
    @Expose
    public Integer postId;
    @SerializedName("post_title")
    @Expose
    public String postTitle;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("latitude")
    @Expose
    public Double latitude;
    @SerializedName("longitude")
    @Expose
    public Double longitude;
    @SerializedName("post_image")
    @Expose
    public List<String> postImage;
    @SerializedName("likeStatus")
    @Expose
    public Integer likeStatus;
    @SerializedName("main_category")
    @Expose
    public Integer mainCategory;
    @SerializedName("sub_cat")
    @Expose
    public Integer subCat;
    @SerializedName("added_by")
    @Expose
    public Integer addedBy;
    @SerializedName("start_time")
    @Expose
    public String startTime;
    @SerializedName("end_time")
    @Expose
    public String endTime;
    @SerializedName("start_date")
    @Expose
    public String startDate;
    @SerializedName("end_date")
    @Expose
    public String endDate;
    @SerializedName("type_post")
    @Expose
    public String typePost;
    @SerializedName("price_vaule")
    @Expose
    public double priceVaule;
    @SerializedName("rating_value")
    @Expose
    public float ratingValue;
    @SerializedName("total_user")
    @Expose
    public Integer totalUser;
    @SerializedName("category_name")
    @Expose
    public String categoryName;
    @SerializedName("added_on")
    @Expose
    public String addedOn;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;
    @SerializedName("distance")
    @Expose
    public String distance;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<String> getPostImage() {
        return postImage;
    }

    public void setPostImage(List<String> postImage) {
        this.postImage = postImage;
    }

    public Integer getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(Integer mainCategory) {
        this.mainCategory = mainCategory;
    }

    public Integer getSubCat() {
        return subCat;
    }

    public void setSubCat(Integer subCat) {
        this.subCat = subCat;
    }

    public Integer getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTypePost() {
        return typePost;
    }

    public void setTypePost(String typePost) {
        this.typePost = typePost;
    }

    public double getPriceVaule() {
        return priceVaule;
    }

    public void setPriceVaule(Integer priceVaule) {
        this.priceVaule = priceVaule;
    }

    public float getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Integer getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
