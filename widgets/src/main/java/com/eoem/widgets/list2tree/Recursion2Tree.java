package com.eoem.widgets.list2tree;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 递归构建树 深度优先遍历(DFS)
 *
 * @author KANYUN
 */
public class Recursion2Tree {
    
    /**
     * 定义根节点
     */
    static Node root = new Node();
    
    /**
     * 所有的节点数据
     */
    static List<Node> nodeList = new ArrayList();
    
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        long startTime = System.currentTimeMillis();
        Recursion2Tree tree = new Recursion2Tree();
        
        // 从数据库中获取数据,并进行类型转换开始
        List<Entity> result = Db.use().query("SELECT * FROM daasfolder_copy1");
        
        for (Entity entity : result) {
            Node node = new Node();
            node.setId(entity.getLong("id"));
            node.setParentId(entity.getLong("parentid"));
            node.setPath(entity.getStr("path"));
            node.setName(entity.getStr("name"));
            nodeList.add(node);
        }
        // 从数据库中获取数据,并进行类型转换结束
        
        // 初始化根节点的children
        root.setChildren(new ArrayList<Node>());
        // 构建根节点
        tree.buildRoot(nodeList);
        // 递归子节点
        tree.buildChildren();
        
        // 完成打印
        Gson gson = new Gson();
        System.out.println(gson.toJson(root.getChildren()));
        
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }
    
    /**
     * 构建顶级树,即找到根节点下的数据
     *
     * @param nodeList
     */
    private void buildRoot(List<Node> nodeList) {
        Iterator<Node> iterator = nodeList.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node.getParentId() == 0) {
                // 找到根节点下的数据,将其添加到root下,并将该节点从所有的节点列表中移除
                root.getChildren().add(node);
                iterator.remove();
            }
        }
        
    }
    
    /**
     * @return void
     * @throws Exception
     * @Author 赵迎旭
     * @Description 构建子节点
     * @Date 14:48 2020/9/18
     * @Param []
     **/
    private void buildChildren() throws Exception {
        // 如果元数据没有被删除完,说明还有数据没有挂到相应的节点上,则继续循环
        while (nodeList.size() > 0) {
            Iterator<Node> iterator = nodeList.iterator();
            build:
            while (iterator.hasNext()) {
                Node node = iterator.next();
                // 是否找到父节点,（注意这里使用的是原子类型,因为原子类型是引用类型）
                AtomicBoolean isFind = new AtomicBoolean(false);
                // 从根节点下的所有一级子节点开始递归遍历DFS
                for (Node pNode : root.getChildren()) {
                    recursion(node, pNode, iterator, isFind);
                    if (isFind.get()) {
                        continue build;
                    }
                }
                
                // 如果该node在上面的递归中没有找到父节点
                // 出现这种问题一般是两个原因：
                // 1.就是数据的顺序是乱的,即当前遍历的节点的父节点还没有挂到树上 处理方法：跳过该Node继续遍历
                // 2.当前节点的父节点,不存在(除非当前节点是根节点下的节点) 处理方法：抛出异常
                if (!isFind.get()) {
                    // 则看剩下的Node集合中是否存在该node的父节点
                    for (Node pNode : nodeList) {
                        if (pNode.getId().equals(node.getParentId())) {
                            // 如果存在则继续外层遍历循环
                            continue build;
                        }
                    }
                    // 否则抛出异常
                    throw new Exception("当前Node节点找不到父节点:" + node.toString());
                }
            }
        }
        
    }
    
    /**
     * @return boolean
     * @Description 递归添加
     * @Date 14:49 2020/9/18
     * @Param [bean, beanList]
     **/
    private void recursion(Node node, Node pNode, Iterator<Node> iterator, AtomicBoolean isFind) {
        Long id = pNode.getId();
        Long parent_id = node.getParentId();
        if (parent_id.equals(id)) {
            if (pNode.getChildren() == null) {
                List<Node> children = new ArrayList<>();
                pNode.setChildren(children);
            }
            pNode.getChildren().add(node);
            iterator.remove();
            isFind.set(true);
            ;
            return;
        }
        
        if (pNode.getChildren() != null) {
            for (Node currentPNode : pNode.getChildren()) {
                recursion(node, currentPNode, iterator, isFind);
            }
        }
        
    }
    
}