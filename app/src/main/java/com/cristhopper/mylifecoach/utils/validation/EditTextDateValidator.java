package com.cristhopper.mylifecoach.utils.validation;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

;

public class EditTextDateValidator extends EditTextValidator {

    private final String TAG = getClass().getSimpleName();

    private final String defaultMessage = "The format of the date you provided is incorrect. Please enter a valid one.";

    public static final String DATE_SHORT = "MM/dd";

    private String mDateFormat;

    public EditTextDateValidator(@NonNull EditText editText, @NonNull String errorMessage, Drawable normalState, Drawable errorState, String dateFormat) {
        super(editText, errorMessage, normalState, errorState);

        // Date format can't be empty
        if (TextUtils.isEmpty(dateFormat))
            throw new RuntimeException();

        this.mDateFormat = dateFormat;
    }

    @Override
    public boolean isValid() {

        // Text is empty
        if (TextUtils.isEmpty(mEditText.getText()))
            return false;

        // Date format
        try {
            SimpleDateFormat mFormatter = new SimpleDateFormat(mDateFormat, Locale.US);
            mFormatter.setLenient(false);
            mFormatter.parse(mEditText.getText().toString());
            return true;
        } catch (ParseException ex) {
            Log.e(TAG, ex.getMessage());
            return false;
        }
    }

    @Override
    public String getDefaultErrorMessage() {
        return defaultMessage;
    }
}
