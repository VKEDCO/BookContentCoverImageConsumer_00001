package org.vkedco.mobappdev.book_content_cover_image_consumer_00001;

/**
 ****************************************************
 * Bugs to vladimir dot kulyukin at gmail dot com
 ****************************************************
 */

import java.util.HashMap;
import android.app.Application;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

public class BookContentCoverImageConsumerApp extends Application {
	
	static final String NEWLINE          = "\n";
	static final String RECORD_SEPARATOR = "***********";
	static final String FORWARD_SLASH    = "/";
	static final String EQLS			 = "=";
	static final String LOGTAG = BookContentCoverImageConsumerApp.class.getSimpleName() + "_LOG";
	
	// ********** book_cover_image table constants ******************
	// constants for book_cover_image column names
	static final String BOOK_COVER_IMAGE_TBL_ID_COL_NAME	= "ID";
	static final String BOOK_COVER_IMAGE_TBL_ISBN_COL_NAME	= "ISBN";
	static final String BOOK_COVER_IMAGE_TBL_IMG_COL_NAME	= "CoverIMG";
	static final String[] BOOK_COVER_IMAGE_TBL_COL_NAMES    = 
	{
		BOOK_COVER_IMAGE_TBL_ID_COL_NAME, 
		BOOK_COVER_IMAGE_TBL_ISBN_COL_NAME,
		BOOK_COVER_IMAGE_TBL_IMG_COL_NAME
	};
	
	// constants for book cover image column numbers
	static final int BOOK_COVER_IMAGE_TBL_ID_COL_NUM		= 0;
	static final int BOOK_COVER_IMAGE_TBL_ISBN_COL_NUM		= 1;
	static final int BOOK_COVER_IMAGE_TBL_IMG_COL_NUM		= 2;
	
	HashMap<String, Bitmap> mCoverImages = null;
	
	public BookContentCoverImageConsumerApp() {
		mCoverImages = new HashMap<String, Bitmap>();
	}
	
	Cursor getAllBookCoverImages() {
    	Uri uri = Uri.parse(getResources().getString(R.string.uri_book_cover_img_all));
    	Cursor rslt = getContentResolver().query(uri, 
    			BookContentCoverImageConsumerApp.BOOK_COVER_IMAGE_TBL_COL_NAMES, 
    			null, 
    			null, 
    			null
    			);
    	return rslt;
    }
	
	 Cursor getBookCoverImageByID(String cover_img_id) {
	    	Uri uri = Uri.parse(getResources().getString(R.string.uri_book_cover_img_all)+ FORWARD_SLASH + 
	    			cover_img_id);
	    	Cursor rslt = getContentResolver().query(uri, 
	    			BookContentCoverImageConsumerApp.BOOK_COVER_IMAGE_TBL_COL_NAMES, 
	    			null, 
	    			null, 
	    			null
	    			);
	    	return rslt;
	 }
	 
	 Cursor getBookCoverImageByISBN(String cover_img_isbn) {
		 Uri uri = Uri.parse(getResources().getString(R.string.uri_book_cover_img_isbn_query) + cover_img_isbn);
		 Cursor rslt = getContentResolver().query(uri, 
	    			BookContentCoverImageConsumerApp.BOOK_COVER_IMAGE_TBL_COL_NAMES, 
	    			null, 
	    			null, 
	    			null
	    			);
		 return rslt;
	 }
	
	// process all book cover images
    void processAllBookCoverImages(Cursor rslt) {
		if ( rslt == null ) return;
		
		if ( rslt.getCount() != 0 ) {
			rslt.moveToFirst();
			int id = 0;
			String isbn = null;
			byte[] image_bytes = null;
			
			while ( rslt.isAfterLast() == false ) {
				id = rslt.getInt(
									rslt
										.getColumnIndex(
												BookContentCoverImageConsumerApp.BOOK_COVER_IMAGE_TBL_ID_COL_NAME)
											);
				isbn = rslt.getString(
											rslt
												.getColumnIndex(
													BookContentCoverImageConsumerApp.BOOK_COVER_IMAGE_TBL_ISBN_COL_NAME)
												);
				if ( !mCoverImages.containsKey(isbn) ) {
					image_bytes = rslt
									.getBlob(
											rslt
												.getColumnIndex(
														BookContentCoverImageConsumerApp.BOOK_COVER_IMAGE_TBL_IMG_COL_NAME)
												);
					mCoverImages.put(isbn, BitmapFactory.decodeByteArray(image_bytes, 0, image_bytes.length));
					Log.d(LOGTAG, "bitmap created for isbn " + isbn);
				}
				else {
					Log.d(LOGTAG, "bitmap already exists for isbn " + isbn);
				}
				
				rslt.moveToNext();
			}
		}
		else {
			Log.d(LOGTAG, "zero entries in cursor");
		}
		
		rslt.close();
	}
    
   
    void processSingleBookCoverImage(Cursor rslt) {
    	processAllBookCoverImages(rslt);
    }
    
}
