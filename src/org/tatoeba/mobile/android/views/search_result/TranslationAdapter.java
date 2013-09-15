package org.tatoeba.mobile.android.views.search_result;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.tatoeba.mobile.android.R;
import org.tatoeba.mobile.android.models.SentenceModel;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 15.09.13
 * Time: 20:30
 */

/**
 * The class represents one entry in the translation list of the sentence detailed view.
 */
public class TranslationAdapter extends BaseAdapter
{

    // Temporary. Has to be replaced with the actual data array
    private ArrayList<SentenceModel> data;

    private Activity activity;
    private static LayoutInflater inflater;

    //public SentenceAdapter(Activity a, ArrayList<HashMap<String, String>> d)

    public TranslationAdapter(Activity a, ArrayList<SentenceModel> d)
    {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if (convertView == null)
            listItemView = inflater.inflate(R.layout.translation_list_item_1, null);

        TextView translationTV = (TextView)listItemView.findViewById(R.id.sentenceTranslation);
        translationTV.setText( data.get(position).getText() );

        TextView languageTV = (TextView)listItemView.findViewById(R.id.languageOfTranslation);
        languageTV.setText( "["+data.get(position).getLanguage()+"]" );

        return listItemView;
    }


}


