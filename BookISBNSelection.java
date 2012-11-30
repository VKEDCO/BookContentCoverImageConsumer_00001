package org.vkedco.mobappdev.book_content_cover_image_consumer_00001;

/**
 ****************************************************
 * Bugs to vladimir dot kulyukin at gmail dot com
 ****************************************************
 */

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BookISBNSelection extends ListActivity {

	ListActivity mThisAct = null;
	Resources mRes = null;
	BookContentCoverImageConsumerApp mApp = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mThisAct = this;
		mRes = getResources();
		mApp = (BookContentCoverImageConsumerApp) getApplication();

		ArrayAdapter<CharSequence> adptr
			= ArrayAdapter.createFromResource(this, 
					R.array.book_isbns,
					android.R.layout.simple_list_item_1);
		setListAdapter(adptr);
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View child,
					int position, long id) {
				Intent i = new Intent(mThisAct, BookCoverImageDisplay.class);
				i.putExtra("isbn", ((TextView) child).getText().toString());
				startActivity(i);
			}
		});
	}
	
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.book_content_cover_image_consumer_activity, menu);
	     return true;
	 }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		switch ( item.getItemId() ) {
		case R.id.menu_item_retrieve_all:
			Cursor rslt = mApp.getAllBookCoverImages();
			mApp.processAllBookCoverImages(rslt);
			Intent i = new Intent(mThisAct, BookCoverImageDisplayAll.class);
			startActivity(i);
			return true;
		default:
			return true;
		}
	 }
}
