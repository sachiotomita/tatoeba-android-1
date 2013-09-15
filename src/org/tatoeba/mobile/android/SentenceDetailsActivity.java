package org.tatoeba.mobile.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 15.09.13
 * Time: 15:24
 */


public class SentenceDetailsActivity extends Activity
{

    private TextView mainSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sentence_details);
    }

}
