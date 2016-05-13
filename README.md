# BezierCurve
Android中贝塞尔曲线的使用实例

今天要讲解的内容是Android中贝塞尔曲线的应用。可能很多人对贝塞尔曲线不甚了解，这里先对它的概念做一下简单介绍。
       贝塞尔曲线由多个点组成：起始点、终止点以及0到n个相互分离的中间点。根据中间点的不同，可以分为线性贝塞尔曲线、二阶贝塞尔曲线、三阶贝塞尔曲线和高阶贝塞尔曲线。一般的矢量图形软件通过它来精确画出曲线，贝塞尔曲线由线段与节点组成，节点是可拖动的支点，线段像可伸缩的皮筋。对于三阶贝塞尔曲线，它由两个锚点P0、P3和两个中间点P1、P2组成。曲线起始于P0走向P1，并从P2的方向来到P3。曲线一般不会经过P1和P2，这两个点只是提供方向资讯。P0和P1之间的间距，决定了曲线在转而趋进P3之前，走向P2方向的“长度有多长”。
了解了贝塞尔曲线的基本概念，下面来看一下贝塞尔曲线在Android中的具体应用。贝塞尔曲线在Android中主要有三个用途：动画差值器、绘制动画轨迹、实现平滑绘图。

![](https://github.com/viclee2014/BezierCurve/blob/master/app/src/main/res/raw/beziercurve.gif)
