package com.cristhopper.mylifecoach.utils.validation;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;

public abstract class EditTextValidator {

    final String TAG = getClass().getSimpleName();

    EditText mEditText;
    String mErrorMessage;
    Drawable mNormalState;
    Drawable mErrorState;

    private final static String mDefaultMessage = "Something went wrong. Please check your input.";

    public EditTextValidator(@NonNull EditText editText, @NonNull String errorMessage, Drawable normalState, Drawable errorState) {
        this.mEditText = editText;
        this.mNormalState = normalState;
        this.mErrorMessage = errorMessage;
        this.mErrorState = errorState;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }
    public EditText getEditText() {
        return mEditText;
    }

    public abstract boolean isValid();
    public String getDefaultErrorMessage() {
        return mDefaultMessage;
    }

    private void showError(boolean shouldShow) {

        if (shouldShow && mErrorState != null)
            mEditText.setBackground(mErrorState);
        else if (mNormalState != null)
            mEditText.setBackground(mNormalState);
    }

    public static String validate(EditTextValidator... editTextValidators) {

        int errorCount = 0;
        String errorMessage = mDefaultMessage;

        for (EditTextValidator validator : editTextValidators) {

            // Show error in case necessary
            validator.showError(!validator.isValid());

            String log = validator.getEditText().getHint() + " | " + validator.mEditText.getText() +" | " + validator.isValid();
            if (validator instanceof EditTextPatternValidator)
                log += "|" + ((EditTextPatternValidator) validator).getPattern();

            Log.d(validator.TAG, log + "\n\n");

            // Set error message
            if (!validator.isValid()) {
                errorCount++;
                errorMessage = validator.getErrorMessage();
            }
        }

        // If there was only one error
        if (errorCount == 1)
            return errorMessage;

        // If there were multiple errors
        else if (errorCount > 1)
            return mDefaultMessage;

        // No error
        else
            return null;
    }
}
