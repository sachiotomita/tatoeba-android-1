package org.tatoeba.mobile.android.views.search_result;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.tatoeba.mobile.android.R;
import org.tatoeba.mobile.android.models.TranslatedSentenceModel;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 7.09.13
 * Time: 15:12
 */


public class SentenceAdapter extends BaseAdapter
{

    // Temporary. Has to be replaced with the actual data array
    private ArrayList<TranslatedSentenceModel> data;

    private Activity activity;
    private static LayoutInflater inflater;

    //public SentenceAdapter(Activity a, ArrayList<HashMap<String, String>> d)

    public SentenceAdapter(Activity a, ArrayList<TranslatedSentenceModel> d)
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
        return data.get(position);
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
            listItemView = inflater.inflate(R.layout.result_list_item_1, null);

        TextView mainSentenceTV = (TextView)listItemView.findViewById(R.id.main_sentence); // main sentence text view

        TranslatedSentenceModel translatedSentence = data.get(position);

        mainSentenceTV.setText(translatedSentence.get_mainSentence().getText()); //

        prepareTranslations(listItemView, translatedSentence);

        return listItemView;
    }

    private void prepareTranslations(View listItemView, TranslatedSentenceModel translatedSentence)
    {
        int translationsNum = translatedSentence.get_translations().size();

        TextView originalTextView = (TextView)listItemView.findViewById(R.id.translations);

        String translationsString = "";
        String text;
        for (int i = 0; i < translationsNum; i++)
        {
            text = translatedSentence.get_translations().get(i).getText();
            translationsString += "* " + text + "\n";
        }

        // Populate the only existing translation TextView.
        originalTextView.setText( translationsString );
    }

    @Override
    public String toString()
    {
        return "SentenceAdapter{" +
                "data=" + data +
                '}';
    }
}


