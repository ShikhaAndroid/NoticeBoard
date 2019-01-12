package com.rajinder.noticeboard.Utils;

import com.activeandroid.Configuration;
import com.activeandroid.content.ContentProvider;

import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.UserInfo.User;

public class DatabaseContentProvider extends ContentProvider {

    @Override
    protected Configuration getConfiguration() {
        Configuration.Builder builder = new Configuration.Builder(getContext());
        builder.addModelClass(User.class);
        builder.addModelClass(Category.class);

        return builder.create();
    }
}