package com.sawyer.kotlinrecyclerviewdemo.activity.multiple

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.sawyer.kotlinrecyclerviewdemo.R
import com.sawyer.kotlinrecyclerviewdemo.activity.BaseActivity
import com.sawyer.kotlinrecyclerviewdemo.util.ToastUti
import kotlinx.android.synthetic.main.activity_mult.*

class MultiActivity : BaseActivity(),MultiAdapter.onMineItemClickListener {

    companion object{
        const val MODE_NORMAL=0
        const val MODE_EDIT=1
    }

    var mEditMode= MODE_NORMAL
    var mList=ArrayList<MultiBean>()
    val adapter=MultiAdapter(this)

    var isSelectAll=false
    var editStatus=false
    var selectedCount=0

    override fun layoutRes()=R.layout.activity_mult

    override fun initView(){
        multRV.layoutManager=LinearLayoutManager(this)
        multRV.adapter=adapter

        //编辑的点击事件
        tvEdit.setOnClickListener {
            mEditMode=if (mEditMode== MODE_NORMAL) MODE_EDIT else MODE_NORMAL
            if (mEditMode== MODE_EDIT){
                tvEdit.text="取消"
                llBottom.visibility= VISIBLE
                editStatus=true

            }else{
                tvEdit.text="编辑"
                llBottom.visibility= GONE
                editStatus=false
//                clearAll()
            }
            adapter.setEditMode(mEditMode)
        }

        //全选的点击事件
        tvSelectAll.setOnClickListener {
            if (!isSelectAll){
                for (i in 0 until adapter.getDataList().size){
                    adapter.getDataList().get(i).isSelect=true
                }
                selectedCount=adapter.getDataList().size
                isSelectAll=true
                tvSelectAll.text="取消全选"
            }else{
                for (i in 0 until adapter.getDataList().size){
                    adapter.getDataList().get(i).isSelect=false
                }
                selectedCount=0
                isSelectAll=false
                tvSelectAll.text="全选"
            }
            adapter.notifyDataSetChanged()
            setBtnBackground(selectedCount)
            tvSelectNum.text="$selectedCount"
        }

        //删除按钮的点击事件
        btnDelete.setOnClickListener{
            val builder= AlertDialog.Builder(this).create()
            builder.setTitle("提示")
            builder.setMessage("是否删除这${selectedCount}个条目？")
            builder.setButton(DialogInterface.BUTTON_POSITIVE,"确定") { _, _ ->

                for (i in adapter.getDataList().size downTo  1){
                    val multiBean=adapter.getDataList().get(i-1)
                    if (multiBean.isSelect){
                        adapter.getDataList().remove(multiBean)
                        selectedCount--
                    }
                }
                selectedCount=0
                tvSelectNum.text="$selectedCount"
                setBtnBackground(selectedCount)
                if (adapter.getDataList().size==0) llBottom.visibility= GONE
                adapter.notifyDataSetChanged()
                builder.dismiss()
                ToastUti.showCenterToast("成功删除",this@MultiActivity)
            }
            builder.setButton(DialogInterface.BUTTON_NEGATIVE,"取消") { _, _ ->
                builder.dismiss()
            }
            builder.show()
        }

    }

    fun clearAll(){
        tvSelectNum.text="0"
        isSelectAll=false
        tvSelectAll.text="全选"
        setBtnBackground(0)
    }

    fun setBtnBackground(size:Int){
        if (size!=0){
            btnDelete.setBackgroundResource(R.drawable.btn_shape_bg)
            btnDelete.isEnabled=true
            btnDelete.setTextColor(Color.WHITE)
        }else{
            btnDelete.setBackgroundResource(R.drawable.btn_noclick_bg)
            btnDelete.isEnabled=false
        }
    }

    override fun initData(){
        for (i in 1..30){
            val multiBean=MultiBean("条目$i",false)
            mList.add(multiBean)
        }
        adapter.notifyAdapter(mList,false)
    }

    override fun onMineItemClick(position: Int, list: ArrayList<MultiBean>) {
        //只有在编辑状态下去操作
        if (editStatus){
            val multiBean=list[position]
            val isSelect=multiBean.isSelect
            multiBean.isSelect=!multiBean.isSelect
            if (isSelect){//当前条目已经被选中的条件下再次被点击
                selectedCount--
                isSelectAll=false
                tvSelectAll.text="全选"
            }else{
                selectedCount++
                if (selectedCount==list.size){
                    isSelectAll=true
                    tvSelectAll.text="取消全选"
                }
            }
            setBtnBackground(selectedCount)
            tvSelectNum.text="$selectedCount"
            adapter.notifyDataSetChanged()
        }
    }

}
