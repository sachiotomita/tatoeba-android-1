package org.tatoeba.mobile.android.service.local_database.data_sources;

import android.content.Context;
import android.database.Cursor;
import org.tatoeba.mobile.android.models.RandomSentenceRequestModel;
import org.tatoeba.mobile.android.models.SentenceModel;
import org.tatoeba.mobile.android.service.local_database.tables.TableSentences;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 31.08.13
 * Time: 13:31
 */


public class RandomSentenceDataSource extends AbstractDataSource
{

    public RandomSentenceDataSource(Context context)
    {
        super(context);
        allColumns = TableSentences.ALL_COLUMNS;
    }

    /**
     * Fetches random sentences according to a provided request.
     * @param requestModel Conditions for the random query.
     * @return null if nothing found, a set of sentences otherwise.
     */
    public SentenceModel[] fetchRandomSentences(RandomSentenceRequestModel requestModel)
    {
        checkDataBase();

        //Log.d("###", "requestModel="+requestModel);

        String selection =  "lang=?";
        String selectionArgs[] = {requestModel.get_language()};

        String groupBy = null;
        String having = null;
        String orderBy = "RANDOM()";
        String limit = Integer.toString( requestModel.get_numberOfResults() );

        Cursor cursor;
        cursor = database.query(TableSentences.TABLE_NAME, allColumns,
                                    selection, selectionArgs,
                                    groupBy, having, orderBy,
                                    limit);

        // No results found, returning null.
        if (cursor.getCount() <1) return null;

        //Log.d("###", "Results in the cursor: " + Integer.toString(cursor.getCount()) );

        SentenceModel sentences[] = new SentenceModel[cursor.getCount()];

        while(cursor.moveToNext())
        {
            sentences[ cursor.getPosition() ] = cursorToSentence(cursor);
        }


        return sentences;
    }

    private SentenceModel cursorToSentence(Cursor cursor)
    {
        SentenceModel sentence = new SentenceModel();
        sentence.setSentenceId(cursor.getInt(0));
        sentence.setLanguage(cursor.getString(1));
        sentence.setText(cursor.getString(2));
        sentence.setOwnerId(cursor.getInt(3));
        return sentence;
    }

}
