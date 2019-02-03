package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale;

public class PermissionUtils {
    private static final String TAG = PermissionUtils.class.getSimpleName();
    private static int REQUEST_CODE;

    private Activity activity;
    private Fragment fragment;
    private String[] permissions;
    private PermissionCallback mPermissionCallback;
    private boolean showRational;

    private PermissionUtils(Activity activity, Fragment fragment, String[] permissions, int requestCode) {
        this.activity = activity;
        this.fragment = fragment;
        this.permissions = permissions;
        this.REQUEST_CODE = requestCode;
        checkIfPermissionPresentInAndroidManifest();
    }

    public PermissionUtils(Activity activity, String[] permissions, int requestCode) {
        this.activity = activity;
        this.permissions = permissions;
        this.REQUEST_CODE = requestCode;
        checkIfPermissionPresentInAndroidManifest();
    }

    public PermissionUtils(Fragment fragment, String[] permissions, int requestCode) {
        this.fragment = fragment;
        this.permissions = permissions;
        this.REQUEST_CODE = requestCode;
        checkIfPermissionPresentInAndroidManifest();
    }

    private void checkIfPermissionPresentInAndroidManifest() {
        for (String permission : permissions) {
            if (hasPermissionInManifest(permission) == false) {
                throw new RuntimeException("Permission (" + permission + ") Not Declared in manifest");
            }
        }
    }

    public void requestPermission(PermissionCallback permissionCallback) {
        this.mPermissionCallback = permissionCallback;
        if (checkSelfPermission(permissions) == false) {
            showRational = shouldShowRationale(permissions);
            if (activity != null)
                ActivityCompat.requestPermissions(activity, filterNotGrantedPermission(permissions), REQUEST_CODE);
            else
                fragment.requestPermissions(filterNotGrantedPermission(permissions), REQUEST_CODE);
        } else {
            if (mPermissionCallback != null)
                mPermissionCallback.onPermissionGranted();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE) {
            boolean denied = false;
            int i = 0;
            ArrayList<String> grantedPermissions = new ArrayList<String>();
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    denied = true;
                } else {
                    grantedPermissions.add(permissions[i]);
                }
                i++;
            }

            if (denied) {
                boolean currentShowRational = shouldShowRationale(permissions);
                if (showRational == false && currentShowRational == false) {
                    Log.d(TAG, "PERMISSION: Permission Denied By System");
                    if (mPermissionCallback != null)
                        mPermissionCallback.onPermissionDeniedBySystem();
                } else {
                    Log.i(TAG, "PERMISSION: Permission Denied");
                    //Checking if any single individual permission is granted then show user that permission
                    if (!grantedPermissions.isEmpty()) {
                        if (mPermissionCallback != null)
                            mPermissionCallback.onIndividualPermissionGranted(grantedPermissions.toArray(new String[grantedPermissions.size()]));
                    }
                    if (mPermissionCallback != null) {
                        mPermissionCallback.onPermissionDenied();
                    }
                }
            } else {
                Log.i(TAG, "PERMISSION: Permission Granted");
                if (mPermissionCallback != null)
                    mPermissionCallback.onPermissionGranted();
            }
        }
    }

    public void showPermissionDeniedSnackbar(View view, final Context context) {
        Snackbar snackbar = Snackbar.make(view, "Please grant all permissions to proceed!", Snackbar.LENGTH_LONG);/*.setAction("Grant", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
            }
        });*/
        snackbar.show();

    }

    public interface PermissionCallback {
        public void onPermissionGranted();

        public void onIndividualPermissionGranted(String grantedPermission[]);

        public void onPermissionDenied();

        public void onPermissionDeniedBySystem();
    }


    private <T extends Context> T getContext() {
        if (activity != null)
            return (T) activity;
        return (T) fragment.getContext();
    }

    /**
     * Return list that is not granted and we need to ask for permission
     */
    private String[] filterNotGrantedPermission(String[] permissions) {
        List<String> notGrantedPermission = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                notGrantedPermission.add(permission);
            }
        }
        return notGrantedPermission.toArray(new String[notGrantedPermission.size()]);
    }

    /**
     * Check permission is there or not for group of permissions
     */
    public boolean checkSelfPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checking if there is need to show rational for group of permissions
     */
    private boolean shouldShowRationale(String[] permissions) {
        boolean currentShowRational = false;
        for (String permission : permissions) {

            if (activity != null) {
                if (shouldShowRequestPermissionRationale(activity, permission) == true) {
                    currentShowRational = true;
                    break;
                }
            } else {
                if (fragment.shouldShowRequestPermissionRationale(permission) == true) {
                    currentShowRational = true;
                    break;
                }
            }
        }
        return currentShowRational;
    }

    private boolean hasPermissionInManifest(String permission) {
        try {
            Context context = activity != null ? activity : fragment.getActivity();
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    if (p.equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
