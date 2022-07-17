package com.noble.module_city.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import com.noble.module_city.R
import android.view.MotionEvent
import android.view.View
import com.noble.module_city.ui.view.IndexView.OnIndexChangeListener

/**
 * @author HQL
 * @desc 字母索引自定义view
 * * 1.把26个字母放入数组
 * * 2.在onMeasure计算每条的高itemHeight和宽itemWidth,
 * * 3.在onDraw和wordWidth,wordHeight,wordX,wordY
 * *
 * * 手指按下文字变色
 * * 1.重写onTouchEvent(),返回true,在down/move的过程中计算
 * * int touchIndex = Y / itemHeight; 强制绘制
 * *
 * * 2.在onDraw()方法对于的下标设置画笔变色
 * *
 * * 3.在up的时候
 * * touchIndex  = -1； //还原默认
 * * 强制绘制
 */
class IndexView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    /**
     * 每条的宽和高
     */
    private var itemWidth = 0
    private var itemHeight = 0
    private val paint: Paint = Paint() //画笔
    private val words = arrayOf(
        "A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z", "#"
    )

    /**
     * 如果我们在布局文件使用该类，将会用这个构造方法实例该类，如果没有就会崩溃
     *
     * @param context
     * @param attrs
     */
    init {  //要实现带有两个参数值的构造方法
        paint.textSize = 40f //字母字体大小
        paint.color = Color.BLACK //默认设置画笔是白色
        paint.isAntiAlias = true //设置抗锯齿
    }

    /**
     * 测量方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        itemWidth = measuredWidth
        itemHeight = measuredHeight / words.size //words.length是26。除以26
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in words.indices) {
            if (touchIndex == i) {
                //设置为灰色(按下去的时候)
                paint.color = Color.GRAY
            } else {  //没有-1走下面代码
                //设置为白色(松开)
                paint.color = resources.getColor(R.color.black)
            }
            val word = words[i] //若取值是A
            val rect = Rect() //矩形
            //画笔
            paint.getTextBounds(word, 0, 1, rect) // 0, 1指的是取一个字母

            //字母的高和宽
            val wordWidth = rect.width()
            val wordHeight = rect.height()

            //计算每个字母在视图上的坐标位置
            val wordX = (itemWidth / 2 - wordWidth / 2).toFloat()
            val wordY =
                (itemHeight / 2 + wordHeight / 2 + i * itemHeight).toFloat() ///i * itemHeight是指往下绘制字母
            canvas.drawText(word, wordX, wordY, paint)
        }
    }

    private var touchIndex = -1 //默认字母的下标位置

    /**
     * 手指按下文字变色
     * 1.重写onTouchEvent(),返回true,在down/move的过程中计算
     * int touchIndex = Y / itemHeight; 强制绘制
     *
     *
     * 2.在onDraw()方法对于的下标设置画笔变色
     *
     *
     * 3.在up的时候
     * touchIndex  = -1； //还原默认
     * 强制绘制
     *
     * @param event
     * @return
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val Y = event.y
                val index = (Y / itemHeight).toInt() //字母索引
                if (index != touchIndex) {
                    touchIndex = index //当前的索引位置
                    invalidate() //强制绘制会导致OnDraw()方法执行
                    if (onIndexChangeListener != null && touchIndex < words.size && touchIndex >= 0) {
                        onIndexChangeListener!!.onIndexChange(words[touchIndex])
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                touchIndex = -1 //还原
                invalidate()
                if (onIndexChangeListener != null) {
                    onIndexChangeListener!!.uplift()
                }
            }
        }
        return true
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("down下拉", parent.parent.javaClass.name)
                parent.parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> parent.parent.parent.requestDisallowInterceptTouchEvent(
                true
            )
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 字母下标索引变化的监听器（做接口）
     */
    interface OnIndexChangeListener {
        /**
         * 当字母下标位置发生变化的时候回调
         *
         * @param word 字母（A~Z）
         */
        fun onIndexChange(word: String?) //没有返回值

        /**
         * 抬起
         */
        fun uplift()
    }

    private var onIndexChangeListener: OnIndexChangeListener? = null

    /**
     * Setter方法
     * 设置字母下标索引变化的监听
     *
     * @param onIndexChangeListener
     */
    fun setOnIndexChangeListener(onIndexChangeListener: OnIndexChangeListener?) {
        this.onIndexChangeListener = onIndexChangeListener
    }
}