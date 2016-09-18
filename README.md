# common-utils
常用工具类
## 初始化
     在Application 类里设置
     
         //设置全局的context
            GlobalContext.setApplication(this);
            
## gradle 导入

     dependencies {
           compile 'com.lwj.fork:common-utils:2.0.4'
       }
###MultipeTypeListAdapter  
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