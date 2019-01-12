package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ViewItemRowPostBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.main_layout, 1);
        sViewsWithIds.put(R.id.cate_image, 2);
        sViewsWithIds.put(R.id.cat_name, 3);
        sViewsWithIds.put(R.id.cate_dis, 4);
        sViewsWithIds.put(R.id.cat_km, 5);
        sViewsWithIds.put(R.id.txt_center, 6);
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
    public final android.widget.LinearLayout mainLayout;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView txtCenter;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ViewItemRowPostBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.catKm = (com.rajinder.noticeboard.Utils.MyTextView) bindings[5];
        this.catName = (com.rajinder.noticeboard.Utils.MyTextView) bindings[3];
        this.cateDis = (com.rajinder.noticeboard.Utils.MyTextView) bindings[4];
        this.cateImage = (android.widget.ImageView) bindings[2];
        this.mainLayout = (android.widget.LinearLayout) bindings[1];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.txtCenter = (com.rajinder.noticeboard.Utils.MyTextView) bindings[6];
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
    public static ViewItemRowPostBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewItemRowPostBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ViewItemRowPostBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.view_item_row_post, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ViewItemRowPostBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewItemRowPostBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.view_item_row_post, null, false), bindingComponent);
    }
    @NonNull
    public static ViewItemRowPostBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewItemRowPostBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/view_item_row_post_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ViewItemRowPostBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}