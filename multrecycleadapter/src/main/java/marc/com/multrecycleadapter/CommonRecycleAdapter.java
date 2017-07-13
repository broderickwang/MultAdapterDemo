package marc.com.multrecycleadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 王成达
 * Date: 2017/7/13
 * Time: 16:16
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public abstract class CommonRecycleAdapter<T> extends RecyclerView.Adapter<ViewHolder>
									implements MultiTypeSupport{

	protected Context mContext;
	protected LayoutInflater mInflater;

	private List<T> mDatas;

	private int mLayoutId;

	private MultiTypeSupport mMultiTypeSupport;

	private OnItemClickListner mClickListner;
	private OnItemLongPressListner mLongPressListner;

	public CommonRecycleAdapter(Context context, List<T> datas, int layoutId) {
		this.mContext = context;
		this.mDatas = datas;
		this.mLayoutId = layoutId;

		mInflater = LayoutInflater.from(mContext);
	}

	/**
	 * 多布局支持
	 * @param context
	 * @param datas
	 * @param multiTypeSupport
	 */
	public CommonRecycleAdapter(Context context, List<T> datas, MultiTypeSupport multiTypeSupport) {
		this(context,datas,-1);
		this.mMultiTypeSupport = multiTypeSupport;
	}

	public void setOnClickListner(OnItemClickListner mClickListner) {
		this.mClickListner = mClickListner;
	}

	public void setLongPressListner(OnItemLongPressListner mLongPressListner) {
		this.mLongPressListner = mLongPressListner;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if(mMultiTypeSupport != null){
			mLayoutId = viewType;
		}
		View itemView = mInflater.inflate(mLayoutId,parent,false);
		ViewHolder holder = new ViewHolder(itemView);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		if(mClickListner != null){
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mClickListner.onClice((RecyclerView) v.getParent(),position);
				}
			});
		}
		if(mLongPressListner != null){
			holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					return mLongPressListner.LongPressListner(position);
				}
			});
		}
		convert(holder,mDatas.get(position));
	}

	@Override
	public int getItemCount() {
		return mDatas==null?0:mDatas.size();
	}

	@Override
	public int getItemViewType(int position) {
		if(mMultiTypeSupport!=null){
			return mMultiTypeSupport.getLayoutId(mDatas.get(position),position);
		}
		return super.getItemViewType(position);
	}

	public abstract void convert(ViewHolder holder, T item);
}
