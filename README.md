# common-utils
常用工具类
## 初始化
     在Application 类里设置
     
         //设置全局的context
            GlobalContext.setApplication(this);
            
## gradle 导入

     dependencies {
           compile 'com.lwj.fork:common-utils:2.0.6'
       }
###<center>MultipeTypeListAdapter 
参考 [逃离adapter的地狱－针对多个View type的组合实现方案](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0810/3282.html) 

附RecycleView 的解决方案 [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)
> MultipeTypeListAdapter 针对ListView 含有多种 item的 adapter 
> 
> AdapterDelegate  与 每种 item 一一对应
> 
> AdapterDelegateManager 含有多种 AdapterDelegate 对每种类型进行分离

  TestAdapterDelegate
  
  ```
    public class TestAdapterDelegate implements AdapterDelegate<String> {
    Context context;
    int type;

    public TestAdapterDelegate(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public int getItemViewType() {
        return type;
    }

    @Override
    public boolean isForViewType(@NonNull ArrayList<String> items, int position) {
         if(position%2==0){
            return true;
        }
        return false;    }

    @Override
    public View getView(int position, View convertView, @NonNull ArrayList<String> items) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, android.R.layout.test_list_item, null);
            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(items.get(position) + "type == " + getItemViewType());
        return convertView;
    }

    class ViewHolder {
        public TextView textView;
    }
}
  ```
TestAdapterDelegate1

```
 public class TestAdapterDelegate1  implements AdapterDelegate<String> {
    Context context;
    int type;

    public TestAdapterDelegate1(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public int getItemViewType() {
        return type;
    }

    @Override
    public boolean isForViewType(@NonNull ArrayList<String> items, int position) {
        if(position%2!=0){
            return true;
        }
        return false;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ArrayList<String> items) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, android.R.layout.test_list_item, null);
            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(items.get(position) + "type == " + getItemViewType());
        return convertView;
    }

    class ViewHolder {
        public TextView textView;
    }
}
```
TestAdapter
  
```
public class TestAdapter extends MultipeTypeListAdapter<String> {

    public static final int TYPE_STR_E = 1;
    public static final int TYPE_STR_F = 2;

    public TestAdapter(Context context) {
        super(context);
        delegateManager.addDelegate(new TestAdapterDelegate(context,TYPE_STR_E));
        delegateManager.addDelegate(new TestAdapterDelegate(context,TYPE_STR_F));
    }

}

```
设置 adapter

```
        ArrayList<String> test = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            test.add("test -- index = "+i+"----");
        }
        TestAdapter testAdapter = new TestAdapter(this);
        ListView lv = (ListView) findViewById(R.id.lv_test);
        lv.setAdapter(testAdapter);
        testAdapter.setList(test);
        
```