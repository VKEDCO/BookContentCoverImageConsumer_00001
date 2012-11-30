package org.vkedco.mobappdev.book_content_cover_image_consumer_00001;

/**
 ****************************************************
 * Bugs to vladimir dot kulyukin at gmail dot com
 ****************************************************
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

public class BookCoverImageDisplay extends Activity {
	ImageView mCoverImageView;
	EditText mEdTxtISBN;
	BookContentCoverImageConsumerApp mApp;
	static final String LOGTAG = BookCoverImageDisplay.class.getSimpleName() + "_LOG";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_cover_image_display);
        mCoverImageView = (ImageView) this.findViewById(R.id.book_cover_img);
        mEdTxtISBN = (EditText) this.findViewById(R.id.edTxtISBN);
        mCoverImageView.setImageDrawable(null);
        mApp = (BookContentCoverImageConsumerApp) getApplication();
        Intent i = getIntent();
        String isbn = i.getStringExtra("isbn");
        Cursor rslt = null;
        if ( isbn != null ) {
        	rslt = mApp.getBookCoverImageByISBN(isbn);
        	if ( rslt != null ) {
        		Log.d(LOGTAG, "cursor not null");
        		mApp.processSingleBookCoverImage(rslt);
        		if ( mApp.mCoverImages.containsKey(isbn) ) {
        			mCoverImageView.setImageBitmap(mApp.mCoverImages.get(isbn));
        		}
        		else {
            		Log.d(LOGTAG, "no bitmap retrieved");
            	}
        	}
        	else {
        		Log.d(LOGTAG, "Cursor is null");
        	}
        	mEdTxtISBN.setText(isbn);
        }
        else {
        	mEdTxtISBN.setText("no isbn specified");
        }
    }

}
