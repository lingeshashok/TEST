package com.rattletech.cityofontario.interfaces;

/**
 * Created by android1 on 3/2/2017.
 */

public class CustomModelInterface {
    public interface OnCustomStateListener {
        void stateChanged();
    }

    private static CustomModelInterface mInstance;
    private OnCustomStateListener mListener;
    private String mState;

    private CustomModelInterface() {}

    public static CustomModelInterface getInstance() {
        if(mInstance == null) {
            mInstance = new CustomModelInterface();
        }
        return mInstance;
    }

    public void setListener(OnCustomStateListener listener) {
        mListener = listener;
    }

    public void changeState(String state) {
        if(mListener != null) {
            mState = state;
            notifyStateChange();
        }
    }

    public String getState() {
        return mState;
    }

    private void notifyStateChange() {
        mListener.stateChanged();
    }
}
