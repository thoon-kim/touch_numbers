package org.techtown.numbertouch;

import android.os.Bundle;

public interface FragmentCallback {
    public void onFragmentSelected(int position, Bundle bundle);
    public void start();
    public void success();
}