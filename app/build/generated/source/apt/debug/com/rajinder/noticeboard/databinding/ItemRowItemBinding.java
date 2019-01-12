package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemRowItemBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.top_linear, 1);
        sViewsWithIds.put(R.id.image, 2);
        sViewsWithIds.put(R.id.main_layout, 3);
        sViewsWithIds.put(R.id.cate_image, 4);
        sViewsWithIds.put(R.id.cat_name, 5);
        sViewsWithIds.put(R.id.cate_dis, 6);
        sViewsWithIds.put(R.id.ratinglayout, 7);
        sViewsWithIds.put(R.id.rating, 8);
        sViewsWithIds.put(R.id.total_user, 9);
        sViewsWithIds.put(R.id.cat_km, 10);
        sViewsWithIds.put(R.id.txt_price, 11);
        sViewsWithIds.put(R.id.layfollow, 12);
        sViewsWithIds.put(R.id.txt_follow, 13);
        sViewsWithIds.put(R.id.txt_following, 14);
        sViewsWithIds.put(R.id.view_linear, 15);
        sViewsWithIds.put(R.id.photo_txt, 16);
    }
    // views
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView catKm;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView catName;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView cateDis;
    @NonNull
    public final android.widget.ImageView cateImage;
    @NonNull
    public final android.widget.ImageView image;
    @NonNull
    public final android.widget.LinearLayout layfollow;
    @NonNull
    public final android.widget.LinearLayout mainLayout;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.LinearLayout photoTxt;
    @NonNull
    public final android.widget.RatingBar rating;
    @NonNull
    public final android.widget.LinearLayout ratinglayout;
    @NonNull
    public final android.widget.LinearLayout topLinear;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView totalUser;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView txtFollow;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView txtFollowing;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView txtPrice;
    @NonNull
    public final android.widget.LinearLayout viewLinear;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemRowItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.catKm = (com.rajinder.noticeboard.Utils.MyTextView) bindings[10];
        this.catName = (com.rajinder.noticeboard.Utils.MyTextView) bindings[5];
        this.cateDis = (com.rajinder.noticeboard.Utils.MyTextView) bindings[6];
        this.cateImage = (android.widget.ImageView) bindings[4];
        this.image = (android.widget.ImageView) bindings[2];
        this.layfollow = (android.widget.LinearLayout) bindings[12];
        this.mainLayout = (android.widget.LinearLayout) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.photoTxt = (android.widget.LinearLayout) bindings[16];
        this.rating = (android.widget.RatingBar) bindings[8];
        this.ratinglayout = (android.widget.LinearLayout) bindings[7];
        this.topLinear = (android.widget.LinearLayout) bindings[1];
        this.totalUser = (com.rajinder.noticeboard.Utils.MyTextView) bindings[9];
        this.txtFollow = (com.rajinder.noticeboard.Utils.MyTextView) bindings[13];
        this.txtFollowing = (com.rajinder.noticeboard.Utils.MyTextView) bindings[14];
        this.txtPrice = (com.rajinder.noticeboard.Utils.MyTextView) bindings[11];
        this.viewLinear = (android.widget.LinearLayout) bindings[15];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemRowItemBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.item_row_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.item_row_item, null, false), bindingComponent);
    }
    @NonNull
    public static ItemRowItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemRowItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_row_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemRowItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}