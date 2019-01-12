package com.rajinder.noticeboard.databinding;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class SubcategoryRowItemBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cate_image, 1);
        sViewsWithIds.put(R.id.cate_name, 2);
    }
    // views
    @NonNull
    public final android.widget.ImageView cateImage;
    @NonNull
    public final com.rajinder.noticeboard.Utils.MyTextView cateName;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    @Nullable
    private com.rajinder.noticeboard.models.CategoryModel mCategory;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public SubcategoryRowItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.cateImage = (android.widget.ImageView) bindings[1];
        this.cateName = (com.rajinder.noticeboard.Utils.MyTextView) bindings[2];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.category == variableId) {
            setCategory((com.rajinder.noticeboard.models.CategoryModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setCategory(@Nullable com.rajinder.noticeboard.models.CategoryModel Category) {
        this.mCategory = Category;
    }
    @Nullable
    public com.rajinder.noticeboard.models.CategoryModel getCategory() {
        return mCategory;
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
    public static SubcategoryRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SubcategoryRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<SubcategoryRowItemBinding>inflate(inflater, com.rajinder.noticeboard.R.layout.subcategory_row_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static SubcategoryRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SubcategoryRowItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.rajinder.noticeboard.R.layout.subcategory_row_item, null, false), bindingComponent);
    }
    @NonNull
    public static SubcategoryRowItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static SubcategoryRowItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/subcategory_row_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new SubcategoryRowItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): category
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}