package com.sawyer.kotlinrecyclerviewdemo.activity.slidemenu

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.Scroller
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sawyer.kotlinrecyclerviewdemo.util.LogUtil
import kotlin.math.abs

class SlideRecyclerView: RecyclerView {
    constructor(context: Context):super(context)

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)

    constructor(context: Context,attributeSet: AttributeSet,defStyleAttr: Int):super(context,attributeSet,defStyleAttr)

    companion object{
        const val MINIMUM_VELOCITY=500 //最小速度
    }

    //系统最小滑动距离
//    val mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop   //=8
    val mTouchSlop = 15

    val mScroller = Scroller(context)

    //速度跟踪器
    val mVelocity = VelocityTracker.obtain()

    /**触碰时的首个横坐标*/
    var mX = 0F

    /**触碰时的首个横坐标*/
    var mY= 0F

    /**滑动偏移量*/
    var dx= 0

    /**触碰时的首个横坐标*/
    var mFirstX = 0F

    /**触碰时的首个纵坐标*/
    var mFirstY = 0F

    /**触碰末次的横坐标*/
    var mLastX = 0F

    /**x轴偏移量*/
    var moveX=0F

    /**y轴偏移量*/
    var moveY=0F

    /**x轴速度偏移量*/
    var velocityX=0F

    /**y轴速度偏移量*/
    var velocityY=0F

    /**itemView中默认菜单宽度*/
    private var mMenuWidth:Int?=0

    /**滑动的itemView*/
    private var mMoveView: ViewGroup?=null

    /**末次滑动的itemView*/
    private var mLastView:ViewGroup?=null

    /**是否正在水平滑动*/
    var mMoving=false


    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        mX = e.x
        mY = e.y

        mVelocity.addMovement(e)

        when(e.action) {
            MotionEvent.ACTION_DOWN ->{
                LogUtil.i("onInterceptTouchEvent:==============================down")
                //如果Scroller处于动画中，则终止
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
                mFirstX=mX
                mFirstY=mY
                mLastX=mX

                LogUtil.i("mFirstX=$mFirstX,mFirstY=$mFirstY")
                //获取点击区域所在的itemView
                mMoveView=findChildViewUnder(mX,mY) as? ViewGroup

                //在点击区域以外的itemView开着菜单，则关闭菜单
                if ( mLastView != mMoveView && mLastView?.scrollX != 0){
                    closeMenu()
                }

//                LogUtil.i("MoveView=${mMoveView.toString()},MenuWidth=$mMenuWidth")
//                LogUtil.i("MoveViewChild=${mMoveView?.childCount}")

                //获取itemView中菜单的宽度（规定itemView中为两个子View）
                mMenuWidth = if (mMoveView?.childCount == 2){
                    mMoveView?.getChildAt(1)?.width
                }else {
                    -1
                }
            }

            MotionEvent.ACTION_MOVE ->{
                LogUtil.i("onInterceptTouchEvent:==============================move")
                mVelocity.computeCurrentVelocity(1000)
                velocityX= abs(mVelocity.xVelocity)
                velocityY= abs(mVelocity.yVelocity)
                moveX= abs(mX-mFirstX)
                moveY= abs(mY-mFirstY)

//                LogUtil.i("velocityX=$velocityX,velocityY=$velocityY,moveX=$moveX,moveY=$moveY")

                //满足下列其中一个则视为水平滑动
                //1.水平速度大于竖直速度，且水平速度大于最小速度
                //2.水平位移大于竖直位移，且水平位移大于最小滑动距离
                //必需条件：itemView菜单栏宽度大于0，且recyclerView处于静止状态（即并不在竖直滑动和拖拽）

                val isHorizontalMove=(velocityX >= MINIMUM_VELOCITY && velocityX > velocityY
                        || moveX > moveY && moveX > mTouchSlop)
                        && (mMenuWidth !!> 0)
                        && scrollState==0


//                LogUtil.i("onInterceptTouchEvent:水平滑动=$isHorizontalMove")
                if (isHorizontalMove){
                    //设置其已处于水平滑动状态，并拦截事件
                    mMoving=true
                    return true
                }
            }

            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL ->{
                LogUtil.i("onInterceptTouchEvent:==============================up")
                //itemView以及其子view触发触碰事件(点击、长按等)，菜单未关闭则直接关闭
                colseMenuNow()
            }
        }
        return super.onInterceptTouchEvent(e)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        mX = e.x
        mY = e.y
//        LogUtil.i("x=${x.toInt()},y=${y.toInt()}")
        mVelocity.addMovement(e)

        when(e.action) {

            MotionEvent.ACTION_DOWN ->{
                LogUtil.i("onTouchEvent:==============================down")
            }

            MotionEvent.ACTION_MOVE ->{
                LogUtil.i("onTouchEvent:==============================move")
//                LogUtil.i("x=${x.toInt()},y=${y.toInt()}")
                //若已处于水平滑动状态，则随手指滑动，否则进行条件判断
                if (mMoving){
                    dx=(mLastX-mX).toInt()
                    //让itemView在规定区域随手指移动
                    if (mMoveView?.scrollX?.plus(dx)!! >=0 && mMoveView?.scrollX?.plus(dx)!! <= mMenuWidth!!){
                        mMoveView?.scrollBy(dx,0)
                    }
                    mLastX=mX
                    return true
                }else{
                    mVelocity.computeCurrentVelocity(1000)
                    velocityX= abs(mVelocity.xVelocity)
                    velocityY= abs(mVelocity.yVelocity)
                    moveX= abs(mX-mFirstX)
                    moveY= abs(mY-mFirstY)
                    LogUtil.i("moveX=${moveX.toInt()},moveY=${moveY.toInt()},velX=${velocityX.toInt()},velY=${velocityY.toInt()}")

                    //根据水平滑动条件判断，是否让itemView跟随手指滑动
                    //这里重新判断是避免itemView中不拦截ACTION_DOWN事件，则后续ACTION_MOVE并不会走onInterceptTouchEvent()方法
                    val isHorizontalMove=((velocityX >= MINIMUM_VELOCITY && velocityX > velocityY)
                            || moveX > moveY && moveX > mTouchSlop)
                            && mMenuWidth !!> 0
                            && scrollState==0

//                    LogUtil.i("onTouchEvent:水平滑动=$isHorizontalMove")
                    if (isHorizontalMove){
                        dx=(mLastX-mX).toInt()
                        //让itemView在规定区域随手指移动
                        if (mMoveView?.scrollX?.plus(dx)!!>=0 && mMoveView?.scrollX?.plus(dx)!! <= mMenuWidth!!){
                            mMoveView?.scrollBy(dx,0)
                        }
                        mLastX=mX
                        mMoving=true
                        return true
                    }
                }
            }

            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL ->{
                LogUtil.i("onTouchEvent:==============================up")
                if (mMoving){
                    //先前没结束的动画终止，并直接到终点
                    if (!mScroller.isFinished) {
                        mScroller.abortAnimation()
                        mLastView?.scrollTo(mScroller.finalX,0)
                    }
                    mMoving=false
                    //已放手，即现在滑动的itemView成了末次滑动的itemView
                    mLastView = mMoveView
                    mVelocity.computeCurrentVelocity(1000)
                    val mScrollX=mLastView.let { scrollX }
                    //若速度大于正方向最小速度，则关闭菜单栏
                    //若速度小于反方向最小速度，则打开菜单栏
                    //若速度没到判断条件，则对菜单显示的宽度进行判断,进行打开/关闭菜单
                    if (mVelocity.xVelocity >= MINIMUM_VELOCITY){
                        mScroller.startScroll(mScrollX, 0, -mScrollX, 0, 1000)

                    }else if (mVelocity.xVelocity <= -MINIMUM_VELOCITY){
                        dx = mMenuWidth?.minus(mScrollX)!!
                        mScroller.startScroll(mScrollX, 0, dx, 0, 1000)

                    } else if (mScrollX > mMenuWidth?.div(2)!!) {
                         dx = mMenuWidth?.minus(mScrollX)!!
                        mScroller.startScroll(mScrollX, 0, dx, 0, 1000)

                    } else {
                        mScroller.startScroll(mScrollX, 0, -mScrollX, 0, 1000)
                    }

                    invalidate()
                }else{
                    //若不是水平滑动状态，菜单栏开着则关闭
                    closeMenu()
                }

            }
        }
        return super.onTouchEvent(e)
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()){
            if (isInWindow(mLastView)){
                mLastView?.scrollTo(mScroller.currX,0)
                invalidate()
            }else{
                //若处于动画的itemView滑出屏幕，则终止动画，并让其到达结束点位置
                mScroller.abortAnimation()
                mLastView?.scrollTo(mScroller.finalX,0)
            }
        }
    }

    //使用Scroller关闭菜单栏
    private fun closeMenu(){
        mScroller.startScroll(mLastView?.scrollX?:0,0, -(mLastView?.scrollX?:0), 0 ,1000)
        invalidate();
    }

    //立即关闭菜单栏
    private fun colseMenuNow(){
        if (mLastView?.scrollX != 0) {
            mLastView?.scrollTo(0, 0)
        }
    }

    //判断该itemView是否显示在屏幕内
    private fun isInWindow(view:View?)=
        if (layoutManager is LinearLayoutManager){
            val manager=layoutManager as LinearLayoutManager
            val firstPos=manager.findFirstVisibleItemPosition()
            val lastPos=manager.findLastVisibleItemPosition()
            val currentPos= view?.let { manager.getPosition(it) }
            currentPos in firstPos..lastPos
        }else{
            true
        }



}