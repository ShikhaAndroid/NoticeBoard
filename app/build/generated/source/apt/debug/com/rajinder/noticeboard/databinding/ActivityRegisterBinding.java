package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityRegisterBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.v1, 3);
        sViewsWithIds.put(R.id.img_name1, 4);
        sViewsWithIds.put(R.id.ltxt1, 5);
        sViewsWithIds.put(R.id.fname_edit, 6);
        sViewsWithIds.put(R.id.phone_edit, 7);
        sViewsWithIds.put(R.id.user_edit, 8);
        sViewsWithIds.put(R.id.password_edit, 9);
        sViewsWithIds.put(R.id.term_check, 10);
        sViewsWithIds.put(R.id.register_btn, 11);
        sViewsWithIds.put(R.id.FrameLayout1, 12);
        sViewsWithIds.put(R.id.login_button, 13);
        sViewsWithIds.put(R.id.btn_facebook, 14);
        sViewsWithIds.put(R.id.btn_google, 15);
        sViewsWithIds.put(R.id.FrameLayout2, 16);
        sViewsWithIds.put(R.id.login_twitter, 17);
        sViewsWithIds.put(R.id.btn_twitter, 18);
    }
    // views
    @NonNull
    public final android.widget.FrameLayout FrameLayout1;
    @NonNull
    public final android.widget.FrameLayout FrameLayout2;
    @NonNull
    public final android.widget.ImageView btnFacebook;
    @NonNull
    public final android.widget.ImageView btnGoogle;
    @NonNull
    public final android.widget.ImageView btnTwitter;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText fnameEdit;
    @NonNull
    public final android.widget.ImageView imgName1;
    @NonNull
    public final com.facebook.login.widget.LoginButton loginButton;
    @NonNull
    public final com.twitter.sdk.android.core.identity.TwitterLoginButton loginTwitter;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView ltxt1;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText passwordEdit;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText phoneEdit;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView registerBtn;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyCheckBox termCheck;
    @Nullable
    public final android.view.View toolbar;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText userEdit;
    @NonNull
    public final android.view.View v1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityRegisterBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds);
        this.FrameLayout1 = (android.widget.FrameLayout) bindings[12];
        this.FrameLayout2 = (android.widget.FrameLayout) bindings[16];
        this.btnFacebook = (android.widget.ImageView) bindings[14];
        this.btnGoogle = (android.widget.ImageView) bindings[15];
        this.btnTwitter = (android.widget.ImageView) bindings[18];
        this.fnameEdit = (com.rajinder.noticeboard.Utils.MyEditText) bindings[6];
        this.imgName1 = (android.widget.ImageView) bindings[4];
        this.loginButton = (com.facebook.login.widget.LoginButton) bindings[13];
        this.loginTwitter = (com.twitter.sdk.android.core.identity.TwitterLoginButton) bindings[17];
        this.ltxt1 = (com.rajinder.noticeboard.Utils.MyTextView) bindings[5];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.passwordEdit = (com.rajinder.noticeboard.Utils.MyEditText) bindings[9];
        this.phoneEdit = (com.rajinder.noticeboard.Utils.MyEditText) bindings[7];
        this.registerBtn = (com.rajinder.noticeboard.Utils.MyTextView) bindings[11];
        this.termCheck = (com.rajinder.noticeboard.Utils.MyCheckBox) bindings[10];
        this.toolbar = (android.view.View) bindings[2];
        this.userEdit = (com.rajinder.noticeboard.Utils.MyEditText) bindings[8];
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
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityRegisterBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.activity_register, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.activity_register, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityRegisterBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_register_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityRegisterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}