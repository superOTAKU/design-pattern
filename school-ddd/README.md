# 关于

这是一个单体应用，经典的学生课程管理系统，负责给学生分配班级，课程，教师，以及排课。

# DDD

使用DDD的方式来实现，包括：CQRS, Event Sourcing, Criteria等DDD常用模式。

最基本的要求，识别Bounded Context，将每个模块作为单独的包，模块之间不直接进行交互，
而是通过事件进行交互。

Entity的实现不要和持久层的框架绑定在一起，要做到这一点不容易，如果直接用jdbcTemplate
比较容易实现，如果用SpringDataJpa，为了解耦，可能需要为每个Entity适配一个Adaptor，
在Adaptor上标注JPA的注解，或者通过xml的方式加载配置也可以。

程序要达到最终一致性，而不是强一致性（事件驱动经常是异步的）。