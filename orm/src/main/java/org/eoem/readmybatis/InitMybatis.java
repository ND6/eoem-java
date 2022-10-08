package org.eoem.readmybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


/**
 * Mybatis 的初始化
 * 步骤一：读取Ibatis的主配置文件，并将文件读成文件流形式(InputStream)。
 * 步骤二：从主配置文件流中读取文件的各个节点信息并存放到Configuration对象中。读取mappers节点的引用文件，并将这些文件的各个节点信息存放到Configuration对象。
 * 步骤三：根据Configuration对象的信息获取数据库连接，并设置连接的事务隔离级别等信息，将经过包装数据库连接对象SqlSession接口返回，DefaultSqlSession是SqlSession的实现类，所以这里返回的是DefaultSqlSession，SqlSession接口里面就是对外提供的各种数据库操作。
 */
public class InitMybatis {
    
    SqlSession init() throws IOException {
        
        String resource = "mybatis.cfg.xml";
    
        Reader reader = Resources.getResourceAsReader(resource);
    
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
    
        SqlSession session = ssf.openSession();
        
        return session;
    }
    
}

