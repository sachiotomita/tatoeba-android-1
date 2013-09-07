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
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.result_list_item_1, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration

        TranslatedSentenceModel translatedSentence = data.get(position);

        title.setText(translatedSentence.get_mainSentence().getText());
        artist.setText(translatedSentence.get_translations().get(0).getText());
        duration.setText(translatedSentence.get_translations().get(1).getText());
        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);

        return vi;
    }
}
