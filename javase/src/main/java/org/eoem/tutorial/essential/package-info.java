/*
Trail: Essential Java Classes

This trail discusses classes from the Java platform that are essential to most programmers.

<Exceptions> explains the exception mechanism and how it is used to handle errors and other exceptional conditions. This lesson describes what an exception is, how to throw and catch exceptions, what to do with an exception once it has been caught, and how to use the exception class hierarchy.

<Basic I/O> covers the Java platform classes used for basic input and output. It focuses primarily on I/O Streams, a powerful concept that greatly simplifies I/O operations. The lesson also looks at Serialization, which lets a program write whole objects out to streams and read them back again. Then the lesson looks at some file system operations, including random access files. Finally, it touches briefly on the advanced features of the New I/O API.

<Concurrency> explains how to write applications that perform multiple tasks simultaneously. The Java platform is designed from the ground up to support concurrent programming, with basic concurrency support in the Java programming language and the Java class libraries. Since version 5.0, the Java platform has also included high-level concurrency APIs. This lesson introduces the platform's basic concurrency support and summarizes some of the high-level APIs in the java.util.concurrent packages.

<The Platform Environment> is defined by the underlying operating system, the Java virtual machine, the class libraries, and various configuration data supplied when the application is launched. This lesson describes some of the APIs an application uses to examine and configure its platform environment.

<Regular Expressions> are a way to describe a set of strings based on common characteristics shared by each string in the set. They can be used to search, edit, or manipulate text and data. Regular expressions vary in complexity, but once you understand the basics of how they're constructed, you'll be able to decipher (or create) any regular expression. This lesson teaches the regular expression syntax supported by the java.util.regex API, and presents several working examples to illustrate how the various objects interact.

<异常>解释异常机制以及如何使用它来处理错误和其他异常情况。本 节 课 介绍 了 什么是 异常、 如何 引发 和 捕获 异常、 在 异常 被 捕获 后 该 如何处理 异常， 以及 如何 使用 异常 类 层次结构。
跟踪图标
<基本输入/输出>介绍了用于基本输入和输出的 Java 平台类。它主要关注 I/O 流，这是一个功能强大的概念，极大地简化了 I/O 操作。本课还介绍了序列化，它允许程序将整个对象写出到流中并再次读回它们。然后，本课程将介绍一些文件系统操作，包括随机访问文件。最后，它简要介绍了新 I/O API 的高级功能。
<并发>说明如何编写同时执行多个任务的应用程序。Java 平台从头开始设计，以支持并发编程，并在 Java 编程语言和 Java 类库中提供基本的并发支持。从 5.0 版开始，Java 平台还包含高级并发 API。本 节 课 介绍 了 平台 的 基本 并 并 总结 了 包 中 的 一些 高级 API。java.util.concurrent
<平台环境>由底层操作系统、Java 虚拟机、类库以及启动应用程序时提供的各种配置数据定义。本 节 课 介绍 了 应用 程序 用于 检查 和 配置 其 平台 环境 的 一些 API。
<正则表达式>是一种根据字符串集中每个字符串共享的共同特征来描述一组字符串的方法。它们可用于搜索、编辑或操作文本和数据。正则表达式的复杂程度各不相同，但是一旦您了解了它们的构造方式的基础知识，您就可以破译（或创建）任何正则表达式。本 节 课 介绍 了 API 所 支持 的 正则 表达式 语法， 并 提供 了 几个 工作 示例 来 说明 各种 对象 如何 交互。java.util.regex
 */
package org.eoem.tutorial.essential;