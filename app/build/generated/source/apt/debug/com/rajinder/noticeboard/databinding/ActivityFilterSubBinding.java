package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityFilterSubBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 3);
        sViewsWithIds.put(R.id.toolbar_layout, 4);
        sViewsWithIds.put(R.id.titlecatname, 5);
        sViewsWithIds.put(R.id.cate_image, 6);
        sViewsWithIds.put(R.id.cate_name, 7);
        sViewsWithIds.put(R.id.empty_list, 8);
        sViewsWithIds.put(R.id.swifeRefresh, 9);
        sViewsWithIds.put(R.id.recycler_cat, 10);
    }
    // views
    @NonNull
    public final android.widget.ImageView cateImage;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView cateName;
    @NonNull
    public final android.widget.LinearLayout emptyList;
    @NonNull
    public final android.widget.LinearLayout internet;
    @NonNull
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerCat;
    @NonNull
    public final android.support.v4.widget.SwipeRefreshLayout swifeRefresh;
    @NonNull
    public final android.widget.LinearLayout titlecatname;
    @Nullable
    public final android.view.View toolbar;
    @NonNull
    public final android.support.design.widget.CollapsingToolbarLayout toolbarLayout;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityFilterSubBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.cateImage = (android.widget.ImageView) bindings[6];
        this.cateName = (com.rajinder.noticeboard.Utils.MyTextView) bindings[7];
        this.emptyList = (android.widget.LinearLayout) bindings[8];
        this.internet = (android.widget.LinearLayout) bindings[2];
        this.internet.setTag(null);
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.recyclerCat = (android.support.v7.widget.RecyclerView) bindings[10];
        this.swifeRefresh = (android.support.v4.widget.SwipeRefreshLayout) bindings[9];
        this.titlecatname = (android.widget.LinearLayout) bindings[5];
        this.toolbar = (android.view.View) bindings[3];
        this.toolbarLayout = (android.support.design.widget.CollapsingToolbarLayout) bindings[4];
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
    public static ActivityFilterSubBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityFilterSubBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityFilterSubBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.activity_filter_sub, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityFilterSubBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityFilterSubBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.activity_filter_sub, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityFilterSubBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityFilterSubBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_filter_sub_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityFilterSubBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}