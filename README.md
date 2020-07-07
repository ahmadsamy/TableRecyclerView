# TableRecyclerView

TableRecyclerView is a custom view to display table data in a recyclerview for android.

### Installation
Just download TableRecyclerView directory and include it as a module to your project

### How To Use

First Include TableRecyclerView to your xml layout file:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">
<ahmad.egypt.tablerecyclerview.view.TableRecyclerView
        android:id="@+id/table"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        />
</Linearlayout>
```

Then  set TableRecyclerViewAdapter from your activity class:
```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_view)
        
        //initialize adapter with your data
        var adapter= TableRecViewAdapter(getDummyList())
        //set adapter
        table.setAdapter(adapter)
        
    }


    private fun getDummyList():List<RowItem>{
        val list= mutableListOf<RowItem>()
        for (i in 0..50){
            val cells= mutableListOf<String>()
            for (j in 1..6){
                cells.add(if (i==0) "#head$j#" else "cell($i,$j)")
            }
            list.add(RowItem(cells))
        }
        return list
    }
```

Also :you can set custom cell click listener
```kotlin
 private val cellClickCallBack=object:CellClickCallBack{
        override fun onCellClick(content: String?) {
            //your action here
        }

        override fun onCellLongClick(content: String?) {
            //your action here
        }
    }
    
    //then assign it to your adapter
    adapter?.setCellClickCallBack(cellClickCallBack)
```
