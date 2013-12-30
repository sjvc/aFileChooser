/*
 * Copyright (C) 2012 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ipaulpro.afilechooserexample;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * @author paulburke (ipaulpro)
 */
@SuppressWarnings ("CollectionDeclaredAsConcreteClass")
@com.googlecode.androidannotations.annotations.EActivity (R.layout.example)
public class FileChooserExampleActivity extends Activity {

    private static final String TAG = FileChooserExampleActivity.class.getName ();
   /**
    * <p>onActivityResult request code</p>
    */
    private static final int REQUEST_CODE = 6384;
    @NotNull private static final ArrayList<String> PDF_Files;
    @NotNull private static final ArrayList<String> Calculator_Files;

    static
    {
        PDF_Files = new java.util.ArrayList<String> ();
        PDF_Files.add (".pdf");
        Calculator_Files = new java.util.ArrayList<String> ();
        Calculator_Files.add (".af");
        Calculator_Files.add (".df");
        Calculator_Files.add (".pf");
    }

    @com.googlecode.androidannotations.annotations.res.StringRes (R.string.chooser_title)
    String chooser_title;

    @com.googlecode.androidannotations.annotations.Click (R.id.All_Files)
    void appFiles() {
        android.util.Log.d (TAG, "+ All_Files");

        // Use the GET_CONTENT intent from the utility class
        final Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        final Intent intent = Intent.createChooser(target, chooser_title);
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (@NotNull final ActivityNotFoundException e) {
           // The reason for the existence of aFileChooser
           android.util.Log.e (TAG, "LOG02230:", e);
        }

        android.util.Log.d (TAG, "+ All_Files");
    }
    @com.googlecode.androidannotations.annotations.Click (R.id.PDF_Files)
    void pdfFiles() {
       android.util.Log.d (TAG, "+ pdfFiles");

       final Intent intent = new Intent (this,FileChooserActivity.class);

       intent.putStringArrayListExtra (
           FileChooserActivity.EXTRA_FILTER_INCLUDE_EXTENSIONS,
           PDF_Files);

       android.util.Log.v (TAG, "> intent             = " + intent);

       try {
          startActivityForResult(intent, REQUEST_CODE);
       } catch (@NotNull final ActivityNotFoundException e) {
          // The reason for the existence of aFileChooser
          android.util.Log.e (TAG, "LOG02230:", e);
       }

       android.util.Log.d (TAG, "- pdfFiles");
   }
    @com.googlecode.androidannotations.annotations.Click (R.id.Calculator_Files)
    void calculatorFiles() {
       android.util.Log.d (TAG, "+ calculatorFiles");

       final Intent intent = new Intent (this,FileChooserActivity.class);

       intent.putStringArrayListExtra (
          FileChooserActivity.EXTRA_FILTER_INCLUDE_EXTENSIONS,
          Calculator_Files);

       android.util.Log.v (TAG, "> intent             = " + intent);

       try {
          startActivityForResult(intent, REQUEST_CODE);
       } catch (@NotNull final ActivityNotFoundException e) {
          // The reason for the existence of aFileChooser
          android.util.Log.e (TAG, "LOG02230:", e);
       }

       android.util.Log.d (TAG, "- calculatorFiles");
   }

    @Override
    protected void onActivityResult(
       final int requestCode,
       final int resultCode,
       @Nullable final Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        Log.i(TAG, "Uri = " + uri.toString());
                        try {
                            // Get the file path from the URI
                            final String path = FileUtils.getPath(this, uri);
                           final android.widget.Toast toast = android.widget.Toast.makeText (
                              com.ipaulpro.afilechooserexample.FileChooserExampleActivity.this,
                              "File Selected: " + path, android.widget.Toast.LENGTH_LONG);
                           toast.show ();
                        } catch (@NotNull final Exception e) {
                            Log.e(TAG, "File select error", e);
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

   @Override public String toString ()
   {
      return TAG +
         "{super=" +
         super.toString () +
         ", chooser_title=“" +
         chooser_title +
         '”' +
         '}';
   }
}
