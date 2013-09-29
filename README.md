tatoeba-android
===============

Android mobile client for tatoeba.org
An early draft.


I've decided to introduce the todo hash-tags.
They are used in the code in order to anchor the exact locations, where the todo has to be implemented.
Here are the examples of the tags: #makeDestinationLanguageWork, #resetSearchAction.

#TODO:
* Change column names in "links" to "originalId" and "translationId". Change the variable names in the code accordingly.
* #resetSearchAction
* Combine the search and browse tabs into one: #makeDestinationLanguageWork
* Now the DB is always opened in read/write mode. Add support for read-only mode.
* Re-work the "Search" fragment, try to use RelativeLayout instead of what's there now :)
* Don't show all the complete translations in the "Results". Display either only flags for the translations or the translations, shortened to one line.
* Make a common list adapter for displaying only a few selected languages. Use language-arrays.xml for the complete language list.
* Display flags for languages instead of the codes.
* Slide left/right in the detailed sentence activity to move to a previous/next one.
* Slide left/right in the results fragment to move between the pages.

