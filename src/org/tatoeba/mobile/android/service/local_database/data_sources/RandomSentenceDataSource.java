package org.tatoeba.mobile.android.service.local_database.data_sources;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import org.tatoeba.mobile.android.models.RandomSentenceRequestModel;
import org.tatoeba.mobile.android.models.SentenceLinkModel;
import org.tatoeba.mobile.android.models.SentenceModel;
import org.tatoeba.mobile.android.models.TranslatedSentenceModel;
import org.tatoeba.mobile.android.service.local_database.tables.TableLinks;
import org.tatoeba.mobile.android.service.local_database.tables.TableSentences;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * This file is a part of "Tatoeba for Android" project. See LICENSE in the project root for license.
 * Author: dipdowel
 * Date: 29.09.13
 * Time: 22:00
 */

public class RandomSentenceDataSource extends SentenceDataSourceBase
{

    private RandomSentenceRequestModel _requestModel;

    public RandomSentenceDataSource(Context context)
    {
        super(context);
        allColumns = null;

        Log.d("###", "allColumns="+allColumns);
    }

    /**
     * Fetches random sentences according to a provided request.
     *
     * @param requestModel Conditions for the random query.
     * @return null if nothing found, a set of sentences otherwise.
     */
    public ArrayList<TranslatedSentenceModel> fetchRandomSentences(RandomSentenceRequestModel requestModel)
    {
        _requestModel = requestModel;
        checkDataBase();

        // fetch the random originals.
        String language = _requestModel.get_sourceLanguage();
        int limit = _requestModel.get_numberOfResults();
        SentenceModel[] originals = fetchSentences(true, language, limit, null);
        if (originals == null) return null;

        int originalIDs[] = extractIDs(originals);

        SentenceLinkModel[] links = fetchLinks(originalIDs);

        // fetch the translations with the ids, taken from the links.
        int translationIDs[] = extractTranslationIDs(links);

        language = _requestModel.get_targetLanguage();
        limit = -1;
        SentenceModel[] translations = fetchSentences(false, language, limit, translationIDs);

        return  link(originals,translations,links);

    }


    /**
     * Fetches links for a given list of sentence IDS.
     * @param sentenceIDs
     * @return A set of links for the given or null if nothing was found
     */
    private SentenceLinkModel[] fetchLinks(int[] sentenceIDs)
    {


        String sentenceIDsList = makeSQL_in_list(sentenceIDs);



        String tableName = TableLinks.TABLE_NAME;
        String[] columns = TableLinks.ALL_COLUMNS;
        String selection =  TableLinks.COLUMN_LEFT_ID + " in " + sentenceIDsList;
        String selectionArgs[] = null;
        String groupBy = null;
        String having = null;
        String orderBy = "RANDOM()";
        String limit = null;

        Cursor cursor;
//        SELECT leftLink, rightLink FROM links WHERE leftLink in ? ORDER BY RANDOM()

        cursor = database.query(tableName, columns,
                selection, selectionArgs,
                groupBy, having, orderBy,
                limit);

        SentenceLinkModel links[] = new SentenceLinkModel[cursor.getCount()];

        int index;
        SentenceLinkModel link;
        while(cursor.moveToNext())
        {
            index = cursor.getPosition();
            link = cursorToLink(cursor);
            links[ index ] = link;
        }

        return links;
    }

    /**
     * Fetches random original sentences according to the request model.
     * @return
     */


    /**
     * Fetches sentences according to the provided arguments.
     * @param random Should the selection be random? Set ids to null if true!
     * @param language Language to select the sentences in (e.g. "eng", "fra", "est", etc).
     * @param limit How many items to fetch (use if random == true).
     * @param ids Fetch sentences with these IDs only. Don't use if random == true!!!
     * @return
     */
    private SentenceModel[] fetchSentences(boolean random, String language, int limit, int[] ids)
    {
        // figure out if the sentence IDs were provided
        String idList = null;
        if (ids != null)
            idList = makeSQL_in_list(ids);

        String tableName = TableSentences.TABLE_NAME;
        String[] columns = TableSentences.ALL_COLUMNS;

        // 1. add the language condition
        // 2. specify the sentence IDs, if provided.
        String selection =  TableSentences.COLUMN_LANGUAGE + "='" + language + "'";
        if (idList!=null) selection = selection + " and " + TableSentences.COLUMN_SENTENCE_ID + " in " + idList;

        String selectionArgs[] = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;
        if (random) orderBy = "RANDOM()";

        String rowLimit = null;
        if (limit>0) rowLimit = Integer.toString(limit);

        Cursor cursor;

        cursor = database.query(tableName, columns,
                selection, selectionArgs,
                groupBy, having, orderBy,
                rowLimit);

        // No results found, returning null.
        if (cursor.getCount() <1) return null;

        SentenceModel sentences[] = new SentenceModel[cursor.getCount()];
        int index;
        SentenceModel currentSentence;
        while(cursor.moveToNext())
        {
            index = cursor.getPosition();
            currentSentence = cursorToSentence(cursor);
            sentences[ index ] = currentSentence;
        }

        return sentences;
    }

    /**
     * Extracts translation IDs from a given set of links.
     * @param links
     * @return
     */
    private int[] extractTranslationIDs(SentenceLinkModel[] links)
    {
        int ids[] = new int[links.length];
        for (int i = 0; i < links.length; i++)
        {
            ids[i] = links[i].getRightId();
        }
        return ids;
    }


    /**
     * Extracts sentence IDs from a given set of sentences.
     * @param sentences
     * @return
     */
    private int[] extractIDs(SentenceModel[] sentences)
    {
        int ids[] = new int[sentences.length];
        for (int i = 0; i < sentences.length; i++)
        {
            ids[i] = sentences[i].getSentenceId();
        }
        return ids;
    }


}
