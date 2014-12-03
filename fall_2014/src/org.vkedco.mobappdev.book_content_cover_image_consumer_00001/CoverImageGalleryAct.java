import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


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
