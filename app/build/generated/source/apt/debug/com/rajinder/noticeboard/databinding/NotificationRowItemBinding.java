package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class NotificationRowItemBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.type_layout, 1);
        sViewsWithIds.put(R.id.type_txt, 2);
        sViewsWithIds.put(R.id.main_layout, 3);
        sViewsWithIds.put(R.id.noti_img, 4);
        sViewsWithIds.put(R.id.ll, 5);
        sViewsWithIds.put(R.id.noti_name, 6);
        sViewsWithIds.put(R.id.noti_dis, 7);
        sViewsWithIds.put(R.id.noti_time, 8);
        sViewsWithIds.put(R.id.view, 9);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout ll;
    @NonNull
    public final android.widget.LinearLayout mainLayout;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView notiDis;
    @NonNull
    public final com.mikhaellopez.circularimageview.CircularImageView notiImg;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView notiName;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView notiTime;
    @NonNull
    public final android.widget.LinearLayout typeLayout;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView typeTxt;
    @NonNull
    public final android.view.View view;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public NotificationRowItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.ll = (android.widget.LinearLayout) bindings[5];
        this.mainLayout = (android.widget.LinearLayout) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.notiDis = (com.rajinder.noticeboard.Utils.MyTextView) bindings[7];
        this.notiImg = (com.mikhaellopez.circularimageview.CircularImageView) bindings[4];
        this.notiName = (com.rajinder.noticeboard.Utils.MyTextView) bindings[6];
        this.notiTime = (com.rajinder.noticeboard.Utils.MyTextView) bindings[8];
        this.typeLayout = (android.widget.LinearLayout) bindings[1];
        this.typeTxt = (com.rajinder.noticeboard.Utils.MyTextView) bindings[2];
        this.view = (android.view.View) bindings[9];
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
    public static NotificationRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static NotificationRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<NotificationRowItemBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.notification_row_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static NotificationRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static NotificationRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.notification_row_item, null, false), bindingComponent);
    }
    @NonNull
    public static NotificationRowItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static NotificationRowItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/notification_row_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new NotificationRowItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}