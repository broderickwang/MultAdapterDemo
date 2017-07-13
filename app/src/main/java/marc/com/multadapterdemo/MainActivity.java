package marc.com.multadapterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import marc.com.multrecycleadapter.MultiTypeSupport;
import marc.com.multrecycleadapter.OnItemClickListner;
import marc.com.multrecycleadapter.OnItemLongPressListner;

public class MainActivity extends AppCompatActivity {
	RecyclerView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		view = (RecyclerView)findViewById(R.id.listview);
		List<String> strs = new ArrayList<>();
		for (int i=0;i<10;i++){
			strs.add(""+i);
		}

		TestAdapter adapter = new TestAdapter(this, strs, new MultiTypeSupport<TestBean>() {
			@Override
			public int getLayoutId(TestBean item, int position) {
				if(position % 2 == 0)
					return R.layout.item;
				else
					return R.layout.item2;
			}
		});

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
		view.setAdapter(adapter);
	}
}
