# MarQueeText
    自定义TextView,实现跑马灯效果，可以自定义跑马灯滚动的速度，并给出向左、向右、来回滚动的三种模式。
##背景
    前几天在群里发现有人在问这样功能的TextView，刚好写到了垂直方向的滚动广告就一起写了这个Demo,做一下学习笔记！
##实现方法
    本Demo主要就是自定义TextView实现Runable接口的run方法，通过自定义属性获取刷新时间和模式，在run方法里根据mode属性的值来实现不同的滚动模式！
##项目展示
    ![](https://github.com/Winfred1989/MarQueeText/blob/master/screen/MarQueeText.gif)
