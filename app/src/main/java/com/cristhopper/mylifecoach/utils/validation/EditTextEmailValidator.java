package com.cristhopper.mylifecoach.utils.validation;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;

public class EditTextEmailValidator extends EditTextValidator {

    private final String defaultMessage = "The format of the email you provided is incorrect. Please enter a valid one.";

    public EditTextEmailValidator(@NonNull EditText editText, @NonNull String errorMessage, Drawable normalState, Drawable errorState) {
        super(editText, errorMessage, normalState, errorState);
    }

    @Override
    public boolean isValid() {

        if (TextUtils.isEmpty(mEditText.getText()))
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(mEditText.getText()).matches();
    }

    @Override
    public String getDefaultErrorMessage() {
        return defaultMessage;
    }
}
