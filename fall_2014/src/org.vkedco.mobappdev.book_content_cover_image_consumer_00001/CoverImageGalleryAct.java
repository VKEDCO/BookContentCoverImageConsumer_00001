import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

// Back button is pressed - onPause() is guranteed to be called.
// Home button is not sure - it depends on OEM. As soon as
// you navigate away - onPause(), onStop(), onDestroy(). But
// some OEM manufactures may decide to leave this activity
// around. It does not have to create a Bundle, because
// creating a Bundle is extra work. But as you navigate
// farther and farther away the OEM may decide that onStop()
// will be called.
// onSaveInstanceState() is not part of the lifecycle.
// This is part of the documentation.
@SuppressWarnings("deprecation")
public class CoverImageGalleryAct extends Activity {
	Resources mRes = null;
	BookContentCoverImageConsumerApp mApp = null;
	@SuppressWarnings("deprecation")
	Gallery mGallery = null;
	final static int GAL_SPACING = 250;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_cover_image_gallery);
		mGallery = (Gallery) findViewById(R.id.cover_img_gallery);
		mGallery.setSpacing(CoverImageGalleryAct.GAL_SPACING);
		mRes = getResources();
		mApp = (BookContentCoverImageConsumerApp) getApplication();
		mGallery.setAdapter(new MyGalleryAdapter(getApplicationContext()));
	}

	class MyGalleryAdapter extends BaseAdapter {
		Context mContext = null;
		String[] mImagISBNs = null;

		public MyGalleryAdapter(Context c) {
			mContext = c;
			mImagISBNs = mRes.getStringArray(R.array.book_isbns);
		}

		public int getCount() {
			return mImagISBNs.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(mContext);
			i.setImageBitmap(mApp.getBitmap(mImagISBNs[position]));
			i.setScaleType(ImageView.ScaleType.FIT_XY);
			return i;
		}

	}

}
