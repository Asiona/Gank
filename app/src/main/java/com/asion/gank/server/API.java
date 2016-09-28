package com.asion.gank.server;
//
//
//  现在的努力,坚持和汗水,
//       是为了在未来给我爱的人和爱我的人
//             一个温馨快乐幸福的  家
//
//

public interface API {

    /*
    所有干货，支持配图数据返回 （除搜索 Api）。
    例如：
    http://gank.io/api/data/Android/10/1

    搜索 API
    http://gank.io/api/search/query/listview/category/Android/count/10/page/1
    注：
    category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    count 最大 50

    获取某几日干货网站数据:
    http://gank.io/api/history/content/2/1
    注： 2 代表 2 个数据，1 代表：取第一页数据

    获取特定日期网站数据:
    http://gank.io/api/history/content/day/2016/05/11

    获取发过干货日期接口:
    http://gank.io/api/day/history

    分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
    数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    请求个数： 数字，大于0
    第几页：数字，大于0
    例：
    http://gank.io/api/data/Android/10/1
    http://gank.io/api/data/福利/10/1
    http://gank.io/api/data/iOS/20/2
    http://gank.io/api/data/all/20/2

    每日数据： http://gank.io/api/day/年/月/日
    例：
    http://gank.io/api/day/2015/08/06

    随机数据：http://gank.io/api/random/data/分类/个数
    数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
    个数： 数字，大于0
    例：
    http://gank.io/api/random/data/Android/20

     */

    // Metrial Design 风格

    /*
    1级：
    HomeFragment:
        推荐页面  就一个 recyclerview

    AndroidFragment:

    IOSFragment:

    WebFragment:

    VideoFragment:

    MoreFragment:

     */
}
