package org.vkedco.mobappdev.book_content_cover_image_consumer_00001;

/**
 ****************************************************
 * Bugs to vladimir dot kulyukin at gmail dot com
 ****************************************************
 */

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class BookCoverImageDisplayAll extends ListActivity {
	
	ListActivity mThisAct = null;
	Resources mRes = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mThisAct = this;
		mRes = getResources();

		ArrayAdapter<CharSequence> adptr
			= ArrayAdapter.createFromResource(this, 
					R.array.book_isbns,
					android.R.layout.simple_list_item_1);
		setListAdapter(adptr);
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View child,
					int position, long id) {
				Intent i = new Intent(mThisAct, BookCoverImageDisplayISBN.class);
				i.putExtra("isbn", ((TextView) child).getText().toString());
				startActivity(i);
			}
		});
	}
}
