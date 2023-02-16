#项目介绍
基于redis实现的延迟队列

#工作原理
1. 使用当前时间+延时秒数为score，存到zset中。
2. 使用lua脚本执行：
    - 扫 zrangebyscore 0到当前秒数的元素
    - 元素添加到list
    - 在zset中删除添加到list的元素
    - 给程序返回扫到的元素
3. 程序每秒执行lua脚本，返回到时间的元素，执行业务操作并记录数据库。
4. 删除list中记录完数据库的元素。
5. 补偿：在第3步出现异常的时候，有程序定时扫list中的元素，补偿业务。