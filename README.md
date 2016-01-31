# ibatistool<br/>
一个Mybatis/ibatis自动生成工具!<br/>
Email:kinglong_chen@163.com

使用方法:<br/>
1.resources/common中的配置文件:<br/>
(1) ibatistool.properties<br/>
    xml_ret_type表示生成的查询类型的sql的返回类型是使用returnType还是resultMap,0表示用resultMap,1表示用returnType<br/>
    base_package表示生成的文件的基本包名<br/>
    base_path生成文件的位置<br/>
    table指定那个表进行自动生成所需的文件<br/>
(2)jdbc.properties 是数据库的相关配置<br/>

2.resources/application.properties:<br/>
表示使用那个配置文件,目前用的common下的配置项,这么做的目的是，如果有过个项目需要生成所需的文件，可以配置common相同等级的目录下的配置文件<br/>

如有两个项目，pro1，pro2，则可以在resources下配置如下目录结构的配置文件:<br/>
|<br/>
|<br/>
resources-<br/>
        |<br/>
        |<br/>
        common+<br/>
        |<br/>
        pro1-<br/>
        |   |<br/>
        |   ibatistool.properties<br/>
        |   |<br/>
        |   jdbc.properties<br/>
        |<br/>
        pro1-<br/>
            |<br/>
            ibatistool.properties<br/>
            |<br/>
            jdbc.properties<br/>
<br/>
如果想生成pro1相关的文件<br/>
则设置application.properties下的文件为<br/>

jdbc.properties = pro1/jdbc.properties

ibatistool.properties = pro1/ibatistool.properties