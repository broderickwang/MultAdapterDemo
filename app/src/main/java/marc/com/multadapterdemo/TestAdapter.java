package marc.com.multadapterdemo;

import android.content.Context;

import java.util.List;

import marc.com.multrecycleadapter.CommonRecycleAdapter;
import marc.com.multrecycleadapter.MultiTypeSupport;
import marc.com.multrecycleadapter.ViewHolder;

/**
 * Created by 王成达
 * Date: 2017/7/13
 * Time: 16:34
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class TestAdapter extends CommonRecycleAdapter<String>  {
	private int layoutId;
	public TestAdapter(Context context, List<String> datas, int layoutId) {
		super(context, datas, layoutId);
		this.layoutId = layoutId;
	}

	public TestAdapter(Context context, List<String> datas, MultiTypeSupport multiTypeSupport) {
		super(context, datas, multiTypeSupport);
	}

	@Override
	public int getLayoutId(Object item, int position) {
		return layoutId;
	}

	@Override
	public void convert(ViewHolder holder, String item) {
		mDatas.indexOf(item);
		holder.setText(R.id.text_item,item);
	}
}
