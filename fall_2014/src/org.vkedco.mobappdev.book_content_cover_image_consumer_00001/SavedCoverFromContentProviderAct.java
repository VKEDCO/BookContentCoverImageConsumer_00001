/**
 ****************************************************
 * Bugs to vladimir dot kulyukin at gmail dot com
 ****************************************************
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

public class SavedCoverFromContentProviderAct extends Activity {
	ImageView mCoverImageView;
	EditText mEdTxtISBN;
	BookContentCoverImageConsumerApp mApp;
	static final String LOGTAG = SavedCoverFromContentProviderAct.class.getSimpleName() + "_LOG";
	
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
        if ( mApp.mCoverImages.containsKey(isbn) ) {
        	mCoverImageView.setImageBitmap(mApp.mCoverImages.get(isbn));
        }
        else {
            Log.d(LOGTAG, "no bitmap retrieved");
        }
        mEdTxtISBN.setText(isbn);
    }

}

