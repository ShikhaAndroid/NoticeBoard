package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ChangePasswordDialogBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ltxt1, 1);
        sViewsWithIds.put(R.id.old_password, 2);
        sViewsWithIds.put(R.id.ltxt2, 3);
        sViewsWithIds.put(R.id.new_password, 4);
        sViewsWithIds.put(R.id.forgot_btn, 5);
    }
    // views
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView forgotBtn;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView ltxt1;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView ltxt2;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText newPassword;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText oldPassword;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ChangePasswordDialogBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds);
        this.forgotBtn = (com.rajinder.noticeboard.Utils.MyTextView) bindings[5];
        this.ltxt1 = (com.rajinder.noticeboard.Utils.MyTextView) bindings[1];
        this.ltxt2 = (com.rajinder.noticeboard.Utils.MyTextView) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.newPassword = (com.rajinder.noticeboard.Utils.MyEditText) bindings[4];
        this.oldPassword = (com.rajinder.noticeboard.Utils.MyEditText) bindings[2];
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
    public static ChangePasswordDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ChangePasswordDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ChangePasswordDialogBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.change_password_dialog, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ChangePasswordDialogBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ChangePasswordDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.change_password_dialog, null, false), bindingComponent);
    }
    @NonNull
    public static ChangePasswordDialogBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ChangePasswordDialogBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/change_password_dialog_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ChangePasswordDialogBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}