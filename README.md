#Amazing Framwwork

## DispatcherServlet
	每一次的访问都会经过这个servlet
 - 首次加载时执行init方法
 - init方法中，加载所有的助手类
 - 助手类
	 - ConfigHelper
		 - 读取配置文件，获取基础配置（包名、数据库相关配置）
	 - ClassHelper
		 - 扫描指定包下的所有类
		 - 可以只获取带Service注解的类
		 - 可以只获取带Controller注解的类
		 - 可以获取所有带指定注解的类
	 - BeanHelper
		 - 将所有带指定注解的类实例化
		 - 存储类与该类实例对象的映射
		 - 可以通过类对象获取该类的实例对象
	 - IocHelper(可能存在bug)
		 - 可以实例化所有带Inject注解的类
		 - 通过遍历已扫描到的bean的所有成员变量，只给带Inject的成员实例化
	 - ControllerHelper
		 - 遍历所有带controller注解的类
		 - 获取controller类中所有带Action注解方法
		 - 取得Action方法的值，Action方法为(请求方法:路径)
		 - 封装请求方法和请求路径为Request对象作为映射
		 - 封装controller类和该方法为处理对象作为值
		 - 通过请求方法和请求路径获取处理对象Handler
