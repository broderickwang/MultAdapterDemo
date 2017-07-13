package marc.com.multrecycleadapter;

/**
 * Created by 王成达
 * Date: 2017/7/13
 * Time: 16:17
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public interface MultiTypeSupport<T> {

	public int getLayoutId(T item,int position);
}
