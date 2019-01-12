package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSettingBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.user_name, 1);
        sViewsWithIds.put(R.id.user_email, 2);
        sViewsWithIds.put(R.id.btn_edit, 3);
        sViewsWithIds.put(R.id.btn_change_password, 4);
        sViewsWithIds.put(R.id.btn_custom_noti, 5);
        sViewsWithIds.put(R.id.btn_logout, 6);
    }
    // views
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView btnChangePassword;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView btnCustomNoti;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView btnEdit;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView btnLogout;
    @NonNull
    private final android.support.design.widget.CoordinatorLayout mboundView0;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView userEmail;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView userName;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentSettingBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.btnChangePassword = (com.rajinder.noticeboard.Utils.MyTextView) bindings[4];
        this.btnCustomNoti = (com.rajinder.noticeboard.Utils.MyTextView) bindings[5];
        this.btnEdit = (com.rajinder.noticeboard.Utils.MyTextView) bindings[3];
        this.btnLogout = (com.rajinder.noticeboard.Utils.MyTextView) bindings[6];
        this.mboundView0 = (android.support.design.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.userEmail = (com.rajinder.noticeboard.Utils.MyTextView) bindings[2];
        this.userName = (com.rajinder.noticeboard.Utils.MyTextView) bindings[1];
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
    public static FragmentSettingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSettingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentSettingBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.fragment_setting, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentSettingBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSettingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.fragment_setting, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentSettingBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentSettingBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_setting_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentSettingBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}