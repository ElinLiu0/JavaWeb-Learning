## 以下解释来源百度百科

`synchronized` 关键字，代表这个方法加锁,相当于不管哪一个线程（例如线程A），运行到这个方法时,
都要检查有没有其它线程B（或者C、 D等）正在用这个方法(或者该类的其他同步方法)，
有的话要等正在使用`synchronized`方法的线程B（或者C 、D）运行完这个方法后再运行此线程A,
没有的话,锁定调用者,然后直接运行。它包括两种用法：`synchronized 方法和 synchronized 块。  
