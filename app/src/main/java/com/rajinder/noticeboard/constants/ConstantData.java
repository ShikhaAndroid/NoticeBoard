package com.rajinder.noticeboard.constants;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.models.CategoryModel;
import com.rajinder.noticeboard.models.DemoModel;
import com.rajinder.noticeboard.models.NotificationModel;
import java.util.ArrayList;

/**
 * Created by rajinder on 4/21/2018.
 */

public class ConstantData {

    public static ArrayList<CategoryModel> getcategorydate() {
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels.add(new CategoryModel("1", R.drawable.ic_demosub, "FOR SALE", "1", true));
        categoryModels.add(new CategoryModel("2", R.drawable.ic_demosub, "ANNOUMCEMENT", "1", true));
        categoryModels.add(new CategoryModel("3", R.drawable.ic_demosub, "EVENT", "1", true));
        categoryModels.add(new CategoryModel("4", R.drawable.ic_demosub, "TRAFFIC ALERT", "1", true));
        categoryModels.add(new CategoryModel("5", R.drawable.ic_demosub, "LOOKING FOR", "1", true));
        categoryModels.add(new CategoryModel("6", R.drawable.ic_demosub, "RENTAL", "1", true));
        categoryModels.add(new CategoryModel("8", R.drawable.ic_demosub, "REVIEWS", "1", true));
        categoryModels.add(new CategoryModel("7", R.drawable.ic_demosub, "RECOMMENDATIONS", "1", false));
        categoryModels.add(new CategoryModel("9", R.drawable.ic_demosub, "NEWS", "1", true));
        categoryModels.add(new CategoryModel("10", R.drawable.ic_demosub, "CITIIZEN REPORTER", "1", true));
        categoryModels.add(new CategoryModel("11", R.drawable.ic_demosub, "COMMUNITY SERVICES", "1", false));
        categoryModels.add(new CategoryModel("12", R.drawable.ic_demosub, "HEALTH CAPSULE", "1", false));
        categoryModels.add(new CategoryModel("13", R.drawable.ic_demosub, "SCIENCE & KNOWLEDGE", "1", false));
        categoryModels.add(new CategoryModel("14", R.drawable.ic_demosub, "ARTICLE", "1", false));
        categoryModels.add(new CategoryModel("15", R.drawable.ic_demosub, "PROMOTION", "1", false));
        categoryModels.add(new CategoryModel("16", R.drawable.ic_demosub, "JOBS", "1", false));
        categoryModels.add(new CategoryModel("17", R.drawable.ic_demosub, "HELP", "1", false));

        return categoryModels;
    }

    public static ArrayList<DemoModel> getDemoModels() {
        ArrayList<DemoModel> categoryModels = new ArrayList<>();
        categoryModels.add(new DemoModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        categoryModels.add(new DemoModel("2", R.drawable.ic_picture, "Movie Review", "This moive is probably fairly forgettable, though horror fans...", "15 Km away", false));
        categoryModels.add(new DemoModel("2", R.drawable.ic_demosub, "Image", "This moive is probably fairly forgettable, though horror fans...", "7 Km", false));
        categoryModels.add(new DemoModel("3", R.drawable.ic_picture, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Hyderabar", false));
        categoryModels.add(new DemoModel("4", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "25 Km", false));
        categoryModels.add(new DemoModel("5", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        categoryModels.add(new DemoModel("6", R.drawable.ic_picture, "Movie Review", "This moive is probably fairly forgettable, though horror fans...", "12 Km", false));
        categoryModels.add(new DemoModel("7", R.drawable.ic_picture, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Mohali", false));
        categoryModels.add(new DemoModel("8", R.drawable.ic_picture, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "3 Km", false));
        categoryModels.add(new DemoModel("9", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km", false));
        categoryModels.add(new DemoModel("10", R.drawable.ic_picture, "Movie Review", "This moive is probably fairly forgettable, though horror fans...", "2 Km", false));
        categoryModels.add(new DemoModel("11", R.drawable.ic_picture, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Chandigarh", false));
        categoryModels.add(new DemoModel("12", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "0 Km", false));
        categoryModels.add(new DemoModel("13", R.drawable.ic_picture, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km", false));
        categoryModels.add(new DemoModel("14", R.drawable.ic_picture, "Movie Review", "This moive is probably fairly forgettable, though horror fans...", "3 Km", false));
        categoryModels.add(new DemoModel("16", R.drawable.ic_picture, "Vacancy", "THe Android UI Designer will be responsible for conceiving and conducting user..", "1 hour", false));
        categoryModels.add(new DemoModel("17", R.drawable.ic_picture, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "5 Km", false));
        return categoryModels;
    }

    public static ArrayList<DemoModel> getpDemoModels() {
        ArrayList<DemoModel> categoryModels = new ArrayList<>();
        categoryModels.add(new DemoModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        categoryModels.add(new DemoModel("3", R.drawable.ic_picture, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Hyderabar", false));
        categoryModels.add(new DemoModel("4", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "25 Km", false));
        categoryModels.add(new DemoModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        categoryModels.add(new DemoModel("3", R.drawable.ic_picture, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Hyderabar", false));
        categoryModels.add(new DemoModel("4", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "25 Km", false));
        categoryModels.add(new DemoModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        categoryModels.add(new DemoModel("3", R.drawable.ic_picture, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Hyderabar", false));
        categoryModels.add(new DemoModel("4", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "25 Km", false));
        categoryModels.add(new DemoModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        categoryModels.add(new DemoModel("3", R.drawable.ic_picture, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Hyderabar", false));
        categoryModels.add(new DemoModel("4", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "25 Km", false));
        return categoryModels;
    }

    public static ArrayList<DemoModel> getrecentDemoModels() {
        ArrayList<DemoModel> categoryModels = new ArrayList<>();
        categoryModels.add(new DemoModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        categoryModels.add(new DemoModel("3", R.drawable.ic_picture, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Hyderabar", false));
        categoryModels.add(new DemoModel("4", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "25 Km", false));
        categoryModels.add(new DemoModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        return categoryModels;
    }

    /*demo sub cate*/
    public static ArrayList<CategoryModel> getsubcate() {
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels.add(new CategoryModel("1", R.drawable.ic_demosub, "Art", "20 minutes", false));
        categoryModels.add(new CategoryModel("2", R.drawable.ic_demosub, "Casuses", "45 minutes", false));
        categoryModels.add(new CategoryModel("3", R.drawable.ic_demosub, "Comedy", "1 hour", false));
        categoryModels.add(new CategoryModel("4", R.drawable.ic_demosub, "Crafts", "2 hour", false));
        categoryModels.add(new CategoryModel("1", R.drawable.ic_demosub, "Dance", "20 minutes", false));
        categoryModels.add(new CategoryModel("2", R.drawable.ic_demosub, "Drinks", "45 minutes", false));
        categoryModels.add(new CategoryModel("4", R.drawable.ic_demosub, "Film", "2 hour", false));
        categoryModels.add(new CategoryModel("3", R.drawable.ic_demosub, "Fitness", "1 hour", false));
        categoryModels.add(new CategoryModel("5", R.drawable.ic_demosub, "Food", "25 minutes", false));
        categoryModels.add(new CategoryModel("6", R.drawable.ic_demosub, "Games", "15 minutes", false));
        categoryModels.add(new CategoryModel("7", R.drawable.ic_demosub, "Gardening", "20 minutes", false));
        categoryModels.add(new CategoryModel("8", R.drawable.ic_demosub, "Health", "45 minutes", false));
        categoryModels.add(new CategoryModel("9", R.drawable.ic_demosub, "Home", "1 hour", false));
        categoryModels.add(new CategoryModel("10", R.drawable.ic_demosub, "Literature", "2 hour", false));
        categoryModels.add(new CategoryModel("11", R.drawable.ic_demosub, "Music", "25 minutes", false));
        categoryModels.add(new CategoryModel("12", R.drawable.ic_demosub, "Networking", "15 minutes", false));
        categoryModels.add(new CategoryModel("14", R.drawable.ic_demosub, "Party", "1 hour", false));
        categoryModels.add(new CategoryModel("15", R.drawable.ic_demosub, "Religion", "2 hour", false));
        categoryModels.add(new CategoryModel("16", R.drawable.ic_demosub, "Shopping", "25 minutes", false));
        categoryModels.add(new CategoryModel("17", R.drawable.ic_demosub, "Sports", "15 minutes", false));
        categoryModels.add(new CategoryModel("18", R.drawable.ic_demosub, "Theatre", "15 minutes", false));
        categoryModels.add(new CategoryModel("19", R.drawable.ic_demosub, "Wellness", "15 minutes", false));
        categoryModels.add(new CategoryModel("13", R.drawable.ic_demosub, "Other", "15 minutes", false));
        return categoryModels;
    }

    /*demo notification */
    public static ArrayList<NotificationModel> getnotificationnew() {
        ArrayList<NotificationModel> notificationModels = new ArrayList<>();
        return notificationModels;
    }

    /*demo notification */
    public static ArrayList<NotificationModel> getnotificationearlier() {
        ArrayList<NotificationModel> notificationModels = new ArrayList<>();
        notificationModels.add(new NotificationModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "new", true));
        notificationModels.add(new NotificationModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", true));
        notificationModels.add(new NotificationModel("2", R.drawable.demoimage3, "Movie Review", "This moive is probably fairly forgettable, though horror fans...", "5 Km away", true));
        notificationModels.add(new NotificationModel("2", R.drawable.ic_demosub, "Image", "This moive is probably fairly forgettable, though horror fans...", "5 Km away", true));
        notificationModels.add(new NotificationModel("1", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "Earlier", true));
        notificationModels.add(new NotificationModel("3", R.drawable.ic_demosub, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Hyderabar", false));
        notificationModels.add(new NotificationModel("4", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "25 Km", false));
        notificationModels.add(new NotificationModel("5", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        notificationModels.add(new NotificationModel("6", R.drawable.demoimage3, "Movie Review", "This moive is probably fairly forgettable, though horror fans...", "12 Km", false));
        notificationModels.add(new NotificationModel("7", R.drawable.demoimage3, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Mohali", false));
        notificationModels.add(new NotificationModel("8", R.drawable.demoimage3, "Announcment", "Houston Moms Blog is thrilled to announce our 2nd Annual Birthday!!!", "3 Km", false));
        notificationModels.add(new NotificationModel("9", R.drawable.demoimage3, "in Rent", "1/2/3/4 BHK,Fully Furnished House/Portion/Room/Flat Space", "5 Km away", false));
        notificationModels.add(new NotificationModel("10", R.drawable.demoimage3, "Movie Review", "This moive is probably fairly forgettable, though horror fans...", "2 Km", false));
        notificationModels.add(new NotificationModel("11", R.drawable.demoimage3, "Vacancy", "THe UX Designer will be responsible for conceiving and conducting user..", "Chandigarh", false));
        return notificationModels;
    }
}
