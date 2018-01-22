package marc.com.multadapterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import marc.com.multrecycleadapter.MultiTypeSupport;
import marc.com.multrecycleadapter.OnItemClickListner;
import marc.com.multrecycleadapter.OnItemLongPressListner;
import marc.com.multrecycleadapter.WrapRecyclerAdapter;
import marc.com.multrecycleadapter.WrapRecyclerView;

public class MainActivity extends AppCompatActivity {
	WrapRecyclerView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		view = (WrapRecyclerView)findViewById(R.id.listview);
		List<String> strs = new ArrayList<>();
		for (int i=0;i<10;i++){
			strs.add(""+i);
		}

		/*TestAdapter adapter = new TestAdapter(this, strs, new MultiTypeSupport<TestBean>() {
			@Override
			public int getLayoutId(TestBean item, int position) {
				if(position % 2 == 0)
					return R.layout.item;
				else
					return R.layout.item2;
			}
		});*/
		TestAdapter adapter = new TestAdapter(this, strs, R.layout.item);

		adapter.setOnClickListner(new OnItemClickListner() {
			@Override
			public void onClice(RecyclerView parent, int position) {
				Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
			}
		});
		adapter.setLongPressListner(new OnItemLongPressListner() {
			@Override
			public boolean LongPressListner(int position) {
				Toast.makeText(MainActivity.this, "long " + position, Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		view.setLayoutManager(new LinearLayoutManager(this));

		WrapRecyclerAdapter adapter1 = new WrapRecyclerAdapter(adapter);
		adapter1.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_view,null,false));

		view.setAdapter(adapter1);

	}
}
