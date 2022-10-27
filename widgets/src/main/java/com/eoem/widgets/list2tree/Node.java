package com.eoem.widgets.list2tree;

import lombok.Data;
import java.util.List;

/**
 * 节点类
 * 部分字段添加transient关键字是为了,在Json序列化时不序列化该字段
 * 
 * @author KANYUN
 *
 */

@Data
public class Node {

    private Long id;

    private Long parentId;

    private String name;

    private transient String parentName;

    private transient boolean isDir;

    private transient String path;

    private List<Node> children;

    @Override
    public String toString() {
        return "Node [id=" + id + ", parentId=" + parentId + ", name=" + name + "]";
    }
}