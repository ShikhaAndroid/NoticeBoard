package com.rajinder.noticeboard.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.LoadingDialog;
import com.rajinder.noticeboard.models.Category.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.rajinder.noticeboard.Utils.MyActivity.checkPermission;

public class BaseFragmentApp extends Fragment {

    private Context mcontext;
    private boolean loadingVisible = false;
    private LoadingDialog loadingDialog;

    /*Pick image from gallery or camera*/
    String mCurrentPhotoPath;
    public Uri imageUri;
    public int REQUEST_CAMERA = 0, SELECT_FILE = 1, IMAGES = 2;
    public String userChoosenTask;

    public BaseFragmentApp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_fragment_app, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new LoadingDialog(mcontext);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mcontext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*get category info*/
    public static List<Category> getcategory() {
        // This is how you execute a query
        return new Select().from(Category.class).execute();
    }

    /*get user info*/
    public static List<com.rajinder.noticeboard.models.UserInfo.User> getuserinfo() {
        // This is how you execute a query
        return new Select().from(com.rajinder.noticeboard.models.UserInfo.User.class).execute();
    }

    /*Show toast long */
    public void showToastLong(String str) {
        Toast.makeText(mcontext, str, Toast.LENGTH_LONG).show();
    }

    public void showsnackbar(String str, View v) {
        Snackbar.make(v, str, Snackbar.LENGTH_LONG);
    }

    /*/*Show toast Short */
    public void showToastShort(String str) {
        Toast.makeText(mcontext, str, Toast.LENGTH_SHORT).show();
    }

    /*Show loader */
    public void showLoading() {
        if (!loadingVisible) {
            loadingVisible = true;
            if (loadingDialog != null) {
                if (!loadingDialog.isShowing())
                    loadingDialog.show();
            }
        }
    }

    /*hide loader */
    public void hideLoading() {
        if (loadingVisible) {
            loadingVisible = false;
            if (loadingDialog != null) {
                if (loadingDialog.isShowing())
                    loadingDialog.dismiss();
            }
        }
    }

    public void selectImage(final Context mcontext, final String  VIEW_TYPE) {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = checkPermission(mcontext);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result) {
                        try {
                            cameraIntent(VIEW_TYPE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent(VIEW_TYPE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void cameraIntent(String type) throws IOException {
//        filename = Environment.getExternalStorageDirectory().getPath() + "/testfile.jpg";
//        imageUri = Uri.fromFile(createImageFile());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("type", type);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void galleryIntent(String type) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("type", type);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("ss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mcontext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",   /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public File compressImage(String filePath) {
        Bitmap scaledBitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;
            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = null;
        FileOutputStream out = null;
        try {
            f = createImageFile();
            out = new FileOutputStream(f);
//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }
        return inSampleSize;
    }

    public boolean validateImageSize(String path) {
        try {
            File file = new File(path);
            long length = file.length() / 1024; // Size in KB
            if (length > 500)
                return false;
            else
                return true;
        } catch (Exception e){
            Log.d("Exception", "validateImageSize: "+e.getMessage());
            return false;
        }
    }

}
