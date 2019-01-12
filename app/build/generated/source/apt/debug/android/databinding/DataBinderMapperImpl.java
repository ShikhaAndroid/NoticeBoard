
package android.databinding;
import com.rajinder.noticeboard.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.rajinder.noticeboard.R.layout.activity_category:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_category_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityCategoryBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_category is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.comment_row_item_no:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/comment_row_item_no_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.CommentRowItemNoBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for comment_row_item_no is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.view_item_row_post:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/view_item_row_post_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ViewItemRowPostBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for view_item_row_post is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.recent_item_row:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/recent_item_row_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.RecentItemRowBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for recent_item_row is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_search:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_search_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivitySearchBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_search is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.allcategory_row_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/allcategory_row_item_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.AllcategoryRowItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for allcategory_row_item is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.change_password_dialog:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/change_password_dialog_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ChangePasswordDialogBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for change_password_dialog is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.fragment_setting:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/fragment_setting_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.FragmentSettingBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for fragment_setting is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.category_row_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/category_row_item_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.CategoryRowItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for category_row_item is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_notification:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_notification_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityNotificationBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_notification is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_register:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_register_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityRegisterBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_register is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_tabcategory:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_tabcategory_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityTabcategoryBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_tabcategory is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.template_filter_category:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/template_filter_category_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.TemplateFilterCategoryBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for template_filter_category is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.category_tab_row_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/category_tab_row_item_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.CategoryTabRowItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for category_tab_row_item is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.comment_row_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/comment_row_item_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.CommentRowItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for comment_row_item is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_subcategory:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_subcategory_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivitySubcategoryBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_subcategory is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_filter_sub:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_filter_sub_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityFilterSubBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_filter_sub is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_edit_profile:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_edit_profile_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityEditProfileBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_edit_profile is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.notification_row_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/notification_row_item_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.NotificationRowItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for notification_row_item is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.subcategory_row_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/subcategory_row_item_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.SubcategoryRowItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for subcategory_row_item is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_auth:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_auth_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityAuthBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_auth is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_login:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_login_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityLoginBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.item_row_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/item_row_item_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ItemRowItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for item_row_item is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_forgot:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_forgot_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityForgotBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_forgot is invalid. Received: " + tag);
                }
                case com.rajinder.noticeboard.R.layout.activity_filter:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/activity_filter_0".equals(tag)) {
                            return new com.rajinder.noticeboard.databinding.ActivityFilterBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for activity_filter is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 884658682: {
                if(tag.equals("layout/activity_category_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_category;
                }
                break;
            }
            case -1023255628: {
                if(tag.equals("layout/comment_row_item_no_0")) {
                    return com.rajinder.noticeboard.R.layout.comment_row_item_no;
                }
                break;
            }
            case 1193595693: {
                if(tag.equals("layout/view_item_row_post_0")) {
                    return com.rajinder.noticeboard.R.layout.view_item_row_post;
                }
                break;
            }
            case 700160926: {
                if(tag.equals("layout/recent_item_row_0")) {
                    return com.rajinder.noticeboard.R.layout.recent_item_row;
                }
                break;
            }
            case 101625572: {
                if(tag.equals("layout/activity_search_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_search;
                }
                break;
            }
            case 1146914606: {
                if(tag.equals("layout/allcategory_row_item_0")) {
                    return com.rajinder.noticeboard.R.layout.allcategory_row_item;
                }
                break;
            }
            case 331196563: {
                if(tag.equals("layout/change_password_dialog_0")) {
                    return com.rajinder.noticeboard.R.layout.change_password_dialog;
                }
                break;
            }
            case 174604759: {
                if(tag.equals("layout/fragment_setting_0")) {
                    return com.rajinder.noticeboard.R.layout.fragment_setting;
                }
                break;
            }
            case 1424601989: {
                if(tag.equals("layout/category_row_item_0")) {
                    return com.rajinder.noticeboard.R.layout.category_row_item;
                }
                break;
            }
            case -1754007257: {
                if(tag.equals("layout/activity_notification_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_notification;
                }
                break;
            }
            case 2013163103: {
                if(tag.equals("layout/activity_register_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_register;
                }
                break;
            }
            case 1860796889: {
                if(tag.equals("layout/activity_tabcategory_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_tabcategory;
                }
                break;
            }
            case -1261934122: {
                if(tag.equals("layout/template_filter_category_0")) {
                    return com.rajinder.noticeboard.R.layout.template_filter_category;
                }
                break;
            }
            case -526119825: {
                if(tag.equals("layout/category_tab_row_item_0")) {
                    return com.rajinder.noticeboard.R.layout.category_tab_row_item;
                }
                break;
            }
            case 2028581102: {
                if(tag.equals("layout/comment_row_item_0")) {
                    return com.rajinder.noticeboard.R.layout.comment_row_item;
                }
                break;
            }
            case 440886980: {
                if(tag.equals("layout/activity_subcategory_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_subcategory;
                }
                break;
            }
            case 1803171957: {
                if(tag.equals("layout/activity_filter_sub_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_filter_sub;
                }
                break;
            }
            case -1158109584: {
                if(tag.equals("layout/activity_edit_profile_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_edit_profile;
                }
                break;
            }
            case -740500488: {
                if(tag.equals("layout/notification_row_item_0")) {
                    return com.rajinder.noticeboard.R.layout.notification_row_item;
                }
                break;
            }
            case 517072943: {
                if(tag.equals("layout/subcategory_row_item_0")) {
                    return com.rajinder.noticeboard.R.layout.subcategory_row_item;
                }
                break;
            }
            case 98995620: {
                if(tag.equals("layout/activity_auth_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_auth;
                }
                break;
            }
            case -237232145: {
                if(tag.equals("layout/activity_login_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_login;
                }
                break;
            }
            case -2011706416: {
                if(tag.equals("layout/item_row_item_0")) {
                    return com.rajinder.noticeboard.R.layout.item_row_item;
                }
                break;
            }
            case -318064225: {
                if(tag.equals("layout/activity_forgot_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_forgot;
                }
                break;
            }
            case -1508187980: {
                if(tag.equals("layout/activity_filter_0")) {
                    return com.rajinder.noticeboard.R.layout.activity_filter;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"category"};
    }
}