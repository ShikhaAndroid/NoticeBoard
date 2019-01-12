package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityFilterBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.km_txt, 3);
        sViewsWithIds.put(R.id.seek_km, 4);
        sViewsWithIds.put(R.id.filter_data, 5);
        sViewsWithIds.put(R.id.recycler_cat, 6);
        sViewsWithIds.put(R.id.viewab, 7);
        sViewsWithIds.put(R.id.recycbelow, 8);
        sViewsWithIds.put(R.id.applybtn, 9);
    }
    // views
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView applybtn;
    @NonNull
    public final android.widget.TextView filterData;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView kmTxt;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.LinearLayout recycbelow;
    @NonNull
    public final com.rajinder.noticeboard.Utils.AutofitRecyclerView recyclerCat;
    @NonNull
    public final com.warkiz.widget.IndicatorSeekBar seekKm;
    @Nullable
    public final android.view.View toolbar;
    @NonNull
    public final android.support.design.widget.CollapsingToolbarLayout toolbarLayout;
    @NonNull
    public final android.view.View viewab;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityFilterBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.applybtn = (com.rajinder.noticeboard.Utils.MyTextView) bindings[9];
        this.filterData = (android.widget.TextView) bindings[5];
        this.kmTxt = (com.rajinder.noticeboard.Utils.MyTextView) bindings[3];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.recycbelow = (android.widget.LinearLayout) bindings[8];
        this.recyclerCat = (com.rajinder.noticeboard.Utils.AutofitRecyclerView) bindings[6];
        this.seekKm = (com.warkiz.widget.IndicatorSeekBar) bindings[4];
        this.toolbar = (android.view.View) bindings[2];
        this.toolbarLayout = (android.support.design.widget.CollapsingToolbarLayout) bindings[1];
        this.toolbarLayout.setTag(null);
        this.viewab = (android.view.View) bindings[7];
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
    public static ActivityFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityFilterBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.activity_filter, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityFilterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityFilterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.activity_filter, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityFilterBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityFilterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_filter_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityFilterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}