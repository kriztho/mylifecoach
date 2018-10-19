package com.cristhopper.mylifecoach.utils.validation;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class EditTextPatternValidator extends EditTextValidator {

    public final static String ALPHA_WHITE_SPACE = "^[a-zA-Z. ]{1,100}$";
    public final static String ALPHA_WHITE_SPACE_OR_EMPTY = "^$|^[a-zA-Z. ]{0,100}$";
    public final static String ALPHA_WHITE_SPACE_SHORT_OR_EMPTY = "^$|^[a-zA-Z. ]{0,25}$";
    public final static String ALPHANUMERIC_WHITE_SPACE = "^[a-zA-Z0-9. ]{1,100}$";
    public final static String ALPHANUMERIC_WHITE_SPACE_OR_EMPTY = "^$|^[a-zA-Z0-9. ]{0,100}$";
    public final static String ALPHANUMERIC_WHITE_SPACE_SHORT_OR_EMPTY = "^$|^[a-zA-Z0-9. ]{0,25}$";
    public final static String PLAIN_PHONE_10_DIGITS_OR_EMPTY = "^$|^[0-9]{10}$";
    public final static String ONE_CHARACTER_GENDER_OR_EMPTY = "^$|^[FM]{1}$";
    public final static String ZIPCODE_OR_EMPTY = "^$|^[0-9]{5}$";
    private final String defaultMessage = "The format of the text you provided is incorrect. Please enter a valid one.";

    private String mPattern;

    public EditTextPatternValidator(@NonNull EditText editText, @NonNull String errorMessage, Drawable normalState, Drawable errorState, String mPattern) {
        super(editText, errorMessage, normalState, errorState);
        this.mPattern = mPattern;
    }

    @Override
    public boolean isValid() {

        if (TextUtils.isEmpty(mPattern))
            return false;

        return mEditText.getText().toString().matches(mPattern);
    }

    @Override
    public String getDefaultErrorMessage() {
        return defaultMessage;
    }

    public String getPattern() {
        return mPattern;
    }
}
