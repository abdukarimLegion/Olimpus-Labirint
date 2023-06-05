package com.fedlo.tom.olimpuslabirint.util

class Spin() : Thread() {
    interface WheelListener {
        fun newImage(img: Int)
    }

    private var imgs = intArrayOf()
    var currentIndex = 0
    private var wheelListener: WheelListener? = null
    private var frameDuration: Long = 0
    private var startIn: Long = 0
    private var isStarted = false


    constructor(wheelListener: WheelListener?, frameDuration: Long, startIn: Long, imgList : IntArray) : this() {
        this.wheelListener = wheelListener
        this.frameDuration = frameDuration
        this.startIn = startIn
        this.imgs = imgList
        currentIndex = 0
        isStarted = true
    }

    fun nextImg() {
        currentIndex++
        if (currentIndex == imgs.size) {
            currentIndex = 0
        }
    }


    fun stopWheel() {
        isStarted = false
    }


    //    override fun run() {
//        try {
//            Thread.sleep(startIn)
//        } catch (ignored: InterruptedException) {
//        }
//        while (isStarted) {
//            try {
//                sleep(frameDuration)
//            } catch (_: InterruptedException) {
//            }
//            nextImg()
//            if (wheelListener != null) {
//                wheelListener!!.newImage(imgs[currentIndex])
//            }
//        }
//    }
    override fun run() {
        try {
            sleep(startIn)
        } catch (ignored: InterruptedException) {
        }
        while (isStarted) {
            try {
                sleep(frameDuration)
            } catch (e: InterruptedException) {
            }
            nextImg()
            if (wheelListener != null) {
                wheelListener!!.newImage(imgs[currentIndex])
            }
        }
    }

}