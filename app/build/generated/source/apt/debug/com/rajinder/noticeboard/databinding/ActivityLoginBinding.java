package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLoginBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.logo_img, 3);
        sViewsWithIds.put(R.id.title_txt, 4);
        sViewsWithIds.put(R.id.img_user, 5);
        sViewsWithIds.put(R.id.ltxt1, 6);
        sViewsWithIds.put(R.id.user_edit, 7);
        sViewsWithIds.put(R.id.img_pass, 8);
        sViewsWithIds.put(R.id.ltxt2, 9);
        sViewsWithIds.put(R.id.password_edit, 10);
        sViewsWithIds.put(R.id.remembercheck, 11);
        sViewsWithIds.put(R.id.forgot_btn, 12);
        sViewsWithIds.put(R.id.login_btn, 13);
        sViewsWithIds.put(R.id.FrameLayout1, 14);
        sViewsWithIds.put(R.id.login_button, 15);
        sViewsWithIds.put(R.id.btn_facebook, 16);
        sViewsWithIds.put(R.id.FrameLayout3, 17);
        sViewsWithIds.put(R.id.btn_sign_in, 18);
        sViewsWithIds.put(R.id.btn_google, 19);
        sViewsWithIds.put(R.id.FrameLayout2, 20);
        sViewsWithIds.put(R.id.login_twitter, 21);
        sViewsWithIds.put(R.id.btn_twitter, 22);
    }
    // views
    @NonNull
    public final android.widget.FrameLayout FrameLayout1;
    @NonNull
    public final android.widget.FrameLayout FrameLayout2;
    @NonNull
    public final android.widget.FrameLayout FrameLayout3;
    @NonNull
    public final android.widget.ImageView btnFacebook;
    @NonNull
    public final android.widget.ImageView btnGoogle;
    @NonNull
    public final com.google.android.gms.common.SignInButton btnSignIn;
    @NonNull
    public final android.widget.ImageView btnTwitter;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView forgotBtn;
    @NonNull
    public final android.widget.ImageView imgPass;
    @NonNull
    public final android.widget.ImageView imgUser;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView loginBtn;
    @NonNull
    public final com.facebook.login.widget.LoginButton loginButton;
    @NonNull
    public final com.twitter.sdk.android.core.identity.TwitterLoginButton loginTwitter;
    @NonNull
    public final android.widget.ImageView logoImg;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView ltxt1;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView ltxt2;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText passwordEdit;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyCheckBox remembercheck;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView titleTxt;
    @Nullable
    public final android.view.View toolbar;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyEditText userEdit;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityLoginBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds);
        this.FrameLayout1 = (android.widget.FrameLayout) bindings[14];
        this.FrameLayout2 = (android.widget.FrameLayout) bindings[20];
        this.FrameLayout3 = (android.widget.FrameLayout) bindings[17];
        this.btnFacebook = (android.widget.ImageView) bindings[16];
        this.btnGoogle = (android.widget.ImageView) bindings[19];
        this.btnSignIn = (com.google.android.gms.common.SignInButton) bindings[18];
        this.btnTwitter = (android.widget.ImageView) bindings[22];
        this.forgotBtn = (com.rajinder.noticeboard.Utils.MyTextView) bindings[12];
        this.imgPass = (android.widget.ImageView) bindings[8];
        this.imgUser = (android.widget.ImageView) bindings[5];
        this.loginBtn = (com.rajinder.noticeboard.Utils.MyTextView) bindings[13];
        this.loginButton = (com.facebook.login.widget.LoginButton) bindings[15];
        this.loginTwitter = (com.twitter.sdk.android.core.identity.TwitterLoginButton) bindings[21];
        this.logoImg = (android.widget.ImageView) bindings[3];
        this.ltxt1 = (com.rajinder.noticeboard.Utils.MyTextView) bindings[6];
        this.ltxt2 = (com.rajinder.noticeboard.Utils.MyTextView) bindings[9];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.passwordEdit = (com.rajinder.noticeboard.Utils.MyEditText) bindings[10];
        this.remembercheck = (com.rajinder.noticeboard.Utils.MyCheckBox) bindings[11];
        this.titleTxt = (com.rajinder.noticeboard.Utils.MyTextView) bindings[4];
        this.toolbar = (android.view.View) bindings[2];
        this.userEdit = (com.rajinder.noticeboard.Utils.MyEditText) bindings[7];
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
    public static ActivityLoginBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityLoginBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityLoginBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.activity_login, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityLoginBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityLoginBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.activity_login, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityLoginBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityLoginBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_login_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityLoginBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}