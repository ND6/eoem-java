package com.eoem.widgets.list2tree;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 循环构建树 广度优先遍历(BFS)
 * 
 * @author KANYUN
 *
 */
public class FlatPath2Tree {

    /**
     * 同一层级的数据放在Map中,层数为key。需要注意的是这里的层数从 0 开始 不断地 自增 中间是不会出现断序的,即 key 一定 是 1,2,3,4
     * 而不是 1,2,4 如果出现了断续,则说明数据是存在问题,即脏数据问题
     */
    static Map<Integer, List<Node>> levelMap = new HashMap<Integer, List<Node>>();

    /**
     * 定义根节点
     */
    static Node root = new Node();

    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();
        FlatPath2Tree tree = new FlatPath2Tree();

        // 从数据库中获取数据,并进行类型转换开始
        List<Entity> result = Db.use().query("SELECT * FROM daasfolder_copy1");
        List<Node> nodeList = new ArrayList();
        for (Entity entity : result) {
            Node node = new Node();
            node.setId(entity.getLong("id"));
            node.setParentId(entity.getLong("parentid"));
            node.setPath(entity.getStr("path"));
            nodeList.add(node);
        }
        // 从数据库中获取数据,并进行类型转换结束

        // 数据预处理
        tree.preNodeHandler(nodeList);
        // 构建树
        tree.buildTree();

        // 完成打印
        Gson gson = new Gson();
        System.out.println(gson.toJson(root.getChildren()));

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 数据预处理,分析Node节点的层数,判断是否是目录(其实这个判断不一定要像程序中写的那么复杂,有时候数据库里会有相应的字段标识是否是目录)
     * 得到父节点的名字
     * 
     * @param nodes
     */
    private void preNodeHandler(List<Node> nodes) {

        for (Node node : nodes) {
            // 这里使用了split的一个重载方法,因为 "test/".split("/") 默认返回的数组长度是1,省略了最后的空值,详情查阅split的重载方法
            String[] pathInfoList = node.getPath().split("/", -1);
            // 判断是否是目录,split的结果返回是数组,其数组长度肯定大于等于1的,直接判断数组的最后一个元素是否为空即可
            boolean isDir = pathInfoList[pathInfoList.length - 1].equals("");
            // 如果是目录标题为length - 2,否则目录标题为length - 1
            String title = isDir ? pathInfoList[pathInfoList.length - 2] : pathInfoList[pathInfoList.length - 1];
            // 判断有几级目录,如果是目录 -2 ,非 目录 -1
            int level = isDir ? pathInfoList.length - 2 : pathInfoList.length - 1;
            // 获取父目录,先判断level是否为0,如果为0 说明父目录是根目录,接着再判断路径是否是目录
            String parentName = level == 0 ? "/"
                    : isDir ? pathInfoList[pathInfoList.length - 3] : pathInfoList[pathInfoList.length - 2];

            // System.out.println("当前遍历目录的层级为：" + level);
            node.setName(title);
            node.setDir(isDir);
            node.setParentName(parentName);
            if (isDir) {
                // 如果是目录初始化children
                List<Node> children = new ArrayList();
                node.setChildren(children);
            }
            // 将该Node放到Map中去
            List<Node> nodeLevel = levelMap.get(level);
            if (nodeLevel == null) {
                nodeLevel = new ArrayList<>();
                levelMap.put(level, nodeLevel);
            }
            nodeLevel.add(node);
        }
    }

    /**
     * 最终处理树,即处理层级,封装数据
     * 
     * @throws Exception
     */
    public void buildTree() throws Exception {
        root.setChildren(new ArrayList<Node>());
        int maxLevel = levelMap.size();
        System.out.println("maxLevel:" + maxLevel);
        // Set<Integer> keys = levelMap.keySet();
        // for (Integer level : keys) {
        // System.out.println(level);
        // }
        // 需要注意的是,这里是顺序遍历,即首先得到操作的肯定是根节点下的数据,BFS 广度优先遍历,对树一层一层的扫
        for (int level = 0; level < maxLevel; level++) {
            List<Node> nodeLevel = levelMap.get(level);
            for (Node node : nodeLevel) {
                // 得到当前节点的兄弟节点列表
                List<Node> siblingNodes = this.getSiblingNodes(node, level, root);
                // 将当前节点加入到该列表中
                siblingNodes.add(node);
            }
        }

    }

    /**
     * 得到当前节点的兄弟节点列表
     * 
     * @param node
     * @param level
     * @param root
     * @return
     * @throws Exception
     */
    private List<Node> getSiblingNodes(Node node, int level, Node root) throws Exception {
        String patName = node.getParentName();
        List<Node> cutNode = new ArrayList();
        if (level == 0) {
            // 当层级为0时,说明是根节点的数据
            cutNode = root.getChildren();
        } else {
            // 当层级不为0时,说明有父目录.此时先找到父目录,从levelMap中找到父目录列表,再遍历到底哪个是父目录
            List<Node> parentNodeList = levelMap.get(level - 1);
            for (Node parentNode : parentNodeList) {
                // 需要注意的是这里是进行的字符串的判断,name的判断,那么会不会存在name重复的问题呢？其实是有一定概率重复的,如下面的例子
                // 北京市->丰台区->长辛店镇->朱家坟
                // 郑州市->金水区->长辛店镇->朱家坟
                // 长辛店镇是不会挂错节点的,因为还有一个父节点的名字做保证,但是到朱家坟就不一样的了,他们的父节点名称是一样的,那么很有可能会挂错
                // 如果能保证名称不会出现这个问题,那么这代码是可用的,如果不能保证,还会需要进行适量的更改,主要是从Node类的path属性入手,将其改为ID进行组装
                // 如果解决这个问题?就是 Node类中的path属性使用节点id进行拼接,id是不会重复的,所以就不会出现这个问题了
                if (parentNode.isDir() && parentNode.getName().equals(patName)) {
                    return parentNode.getChildren();
                }
            }
            throw new Exception("当前Node节点找不到父节点:" + node.toString());
        }
        return cutNode;

    }

}