package org.eoem.lang;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.ThreadLocalRandom.current;

public class HowToAtomicReference {
    
    
    /**
     * volatile关键字修饰，每次对DebitCard对象引用的写入操作都会被其他线程看到
     * 创建初始DebitCard，账号金额为0元
     * volatile关键字不保证原子性
     */
    static volatile DebitCard debitCard = new DebitCard("zhangsan", 0);
    
    public static void main(String[] args) {
        main3();
    }
    
    /**
     * 多线程下增加账号金额
     */
    public static void main1() {
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i) {
                @Override
                public void run() {
                    final DebitCard dc = debitCard;
                    // 基于全局DebitCard的金额增加10元并且产生一个新的DebitCard
                    DebitCard newDC = new DebitCard(dc.getAccount(),
                            dc.getAmount() + 10);
                    // 输出全新的DebitCard
                    System.out.println(newDC);
                    // 修改全局DebitCard对象的引用
                    debitCard = newDC;
                    try {
                        TimeUnit.MILLISECONDS.sleep(current().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    
    /**
     * 多线程下加锁增加账号金额
     */
    public static void main2() {
        System.out.println("### 多线程下加锁增加账号金额");
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i) {
                @Override
                public void run() {
                    synchronized (HowToAtomicReference.class) {
                        final DebitCard dc = debitCard;
                        // 基于全局DebitCard的金额增加10元并且产生一个新的DebitCard
                        DebitCard newDC = new DebitCard(dc.getAccount(),
                                dc.getAmount() + 10);
                        // 输出全新的DebitCard
                        System.out.println(newDC);
                        // 修改全局DebitCard对象的引用
                        debitCard = newDC;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(current().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    
    /**
     * 定义AtomicReference并且初始值为DebitCard("zhangsan", 0)
     */
    private static AtomicReference<DebitCard> debitCardRef
            = new AtomicReference<>(new DebitCard("zhangsan", 0));
    
    /**
     * AtomicReference的非阻塞解决方案
     */
    public static void main3() {
        System.out.println("### AtomicReference的非阻塞解决方案");
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i) {
                @Override
                public void run() {
                    // 获取AtomicReference的当前值
                    final DebitCard dc = debitCardRef.get();
                    // 基于AtomicReference的当前值创建一个新的DebitCard
                    DebitCard newDC = new DebitCard(dc.getAccount(),
                            dc.getAmount() + 10);
                    // 基于CAS算法更新AtomicReference的当前值
                    if (debitCardRef.compareAndSet(dc, newDC)) {
                        // 更新成功
                        System.out.println(newDC);
                    } else {
                        // 更新失败
                        System.out.println("###更新失败");
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(current().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
