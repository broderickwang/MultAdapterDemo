package marc.com.multrecycleadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 王成达
 * Date: 2017/7/13
 * Time: 16:06
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class ViewHolder extends RecyclerView.ViewHolder {

	private SparseArray<View> mViews;

	public ViewHolder(View itemView) {
		super(itemView);
		mViews = new SparseArray<>();
	}

	public <T extends View>T getView(int viewId){
		View v = mViews.get(viewId);
		if(v == null){
			v = itemView.findViewById(viewId);
			mViews.put(viewId,v);
		}
		return (T) v;
	}

	public ViewHolder setText(int viewId,CharSequence text){
		TextView textView = getView(viewId);
		textView.setText(text);
		return this;
	}

	public ViewHolder setTag(int viewId,CharSequence tag){
		TextView textView = getView(viewId);
		textView.setTag(tag);
		return this;
	}

	public ViewHolder setVisibility(int viewId,int visible){
		getView(viewId).setVisibility(visible);
		return this;
	}

	public ViewHolder setImageResource(int viewId,int resourceId){
		ImageView imageView = getView(viewId);
		imageView.setImageResource(resourceId);
		return this;
	}

	public ViewHolder setImageByUrl(int viewId,ImageLoader loader){
		ImageView imageView = getView(viewId);
		if(loader == null){
			throw new NullPointerException("ImageLoader is null!");
		}
		loader.displayImage(imageView.getContext(),imageView,loader.getImagePath());
		return this;
	}


	public static abstract class ImageLoader{
		private String mPath;

		public ImageLoader(String path) {
			this.mPath = path;
		}

		public String getImagePath() {
			return mPath;
		}

		public abstract void displayImage(Context context, ImageView imageView, String imagePath);
	}
}
