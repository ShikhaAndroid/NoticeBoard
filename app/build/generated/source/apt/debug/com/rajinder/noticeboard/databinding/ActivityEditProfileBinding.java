package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityEditProfileBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.v1, 3);
        sViewsWithIds.put(R.id.user_edit, 4);
        sViewsWithIds.put(R.id.img_name1, 5);
        sViewsWithIds.put(R.id.ltxt1, 6);
        sViewsWithIds.put(R.id.fname_edit, 7);
        sViewsWithIds.put(R.id.phone_edit, 8);
        sViewsWithIds.put(R.id.user_address, 9);
        sViewsWithIds.put(R.id.edit_btn, 10);
    }
    // views
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView editBtn;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText fnameEdit;
    @NonNull
    public final android.widget.ImageView imgName1;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView ltxt1;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText phoneEdit;
    @Nullable
    public final android.view.View toolbar;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText userAddress;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView userEdit;
    @NonNull
    public final android.view.View v1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityEditProfileBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.editBtn = (com.rajinder.noticeboard.Utils.MyTextView) bindings[10];
        this.fnameEdit = (com.rajinder.noticeboard.Utils.MyEditText) bindings[7];
        this.imgName1 = (android.widget.ImageView) bindings[5];
        this.ltxt1 = (com.rajinder.noticeboard.Utils.MyTextView) bindings[6];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.phoneEdit = (com.rajinder.noticeboard.Utils.MyEditText) bindings[8];
        this.toolbar = (android.view.View) bindings[2];
        this.userAddress = (com.rajinder.noticeboard.Utils.MyEditText) bindings[9];
        this.userEdit = (com.rajinder.noticeboard.Utils.MyTextView) bindings[4];
        this.v1 = (android.view.View) bindings[3];
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
    public static ActivityEditProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityEditProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityEditProfileBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.activity_edit_profile, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityEditProfileBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityEditProfileBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.activity_edit_profile, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityEditProfileBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityEditProfileBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_edit_profile_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityEditProfileBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}