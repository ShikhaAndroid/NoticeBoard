package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class RecentItemRowBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.user_image, 1);
        sViewsWithIds.put(R.id.user_image2, 2);
        sViewsWithIds.put(R.id.user_name, 3);
        sViewsWithIds.put(R.id.user_dis, 4);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView userDis;
    @NonNull
    public final com.mikhaellopez.circularimageview.CircularImageView userImage;
    @NonNull
    public final com.mikhaellopez.circularimageview.CircularImageView userImage2;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView userName;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public RecentItemRowBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.userDis = (com.rajinder.noticeboard.Utils.MyTextView) bindings[4];
        this.userImage = (com.mikhaellopez.circularimageview.CircularImageView) bindings[1];
        this.userImage2 = (com.mikhaellopez.circularimageview.CircularImageView) bindings[2];
        this.userName = (com.rajinder.noticeboard.Utils.MyTextView) bindings[3];
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
    public static RecentItemRowBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecentItemRowBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<RecentItemRowBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.recent_item_row, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static RecentItemRowBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecentItemRowBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.recent_item_row, null, false), bindingComponent);
    }
    @NonNull
    public static RecentItemRowBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RecentItemRowBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/recent_item_row_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new RecentItemRowBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}