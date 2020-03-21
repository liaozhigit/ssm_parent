package cn.liaozhi.controller;



import cn.liaozhi.mail.MailVO;
import cn.liaozhi.mail.service.IMailService;
import cn.liaozhi.pojo.Items;
import cn.liaozhi.service.ItemService;
import cn.liaozhi.vo.QueryVo;
import cn.liaozhi.workflow.service.IProcessOperateService;
import cn.liaozhi.workflow.service.ITaskOperateService;
import cn.liaozhi.workflow.service.impl.RepositoryUploadConsumerService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
//窄化请求映射:为防止你和你的队友在conroller方法起名的时候重名,所以相当于在url中多加了一层目录,防止重名
//例如:当前list的访问路径   localhost:8081/ssm0523-1/items/list.action
@RequestMapping("/items")
public class ItemController {
	static int count = 0;
	private Logger log = Logger.getLogger(ItemController.class);
	@Autowired
	private ItemService itmeService;
	@Autowired
	private RepositoryUploadConsumerService repositoryUploadConsumerService;
	@Autowired
	private IMailService baseMailService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private IProcessOperateService processOperateService;

	@Autowired
	private ITaskOperateService taskOperateService;

	//@RequestMapping(value="/list", method=RequestMethod.GET)
	@RequestMapping("/list")
	public ModelAndView itemsList() throws Exception{
		//测试运行时异常
		//int i= 0/0;

		//测试自定义异常
//		if(true){
//			CustomException customException = new CustomException();
//			customException.setMessage("对不起哦, 您已经抢购过, 不要太贪心哦!");
//			throw customException;
//		}

		List<Items> list = itmeService.list();

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("itemList", list);
		modelAndView.setViewName("itemList");
		return modelAndView;
	}

	/**
	 * springMvc中默认支持的参数类型:也就是说在controller方法中可以加入这些也可以不加,  加不加看自己需不需要,都行.
	 *HttpServletRequest
	 *HttpServletResponse
	 *HttpSession
	 *Model
	 *
	 *通过@PathVariable可以接收url中传入的参数
	 *@RequestMapping("/itemEdit/{id}")中接收参数使用大括号中加上变量名称, @PathVariable中的变量名称要和RequestMapping
	 *中的变量名称相同
	 */
	@RequestMapping("/itemEdit/{id}")
	public String itemEdit(@PathVariable("id") Integer id, HttpServletRequest reuqest,
						   Model model) throws Exception{

		//String idStr = reuqest.getParameter("id");
		Items item = itmeService.findItemsById(id);

		//Model模型:模型中放入了返回给页面的数据
		//model底层其实就是用的request域来传递数据,但是对request域进行了扩展.
		model.addAttribute("item", item);

		//如果springMvc方法返回一个简单的string字符串,那么springMvc就会认为这个字符串就是页面的名称
		return "editItem";
	}

	//springMvc可以直接接收基本数据类型,包括string.spirngMvc可以帮你自动进行类型转换.
	//controller方法接收的参数的变量名称必须要等于页面上input框的name属性值

	//spirngMvc可以直接接收pojo类型:要求页面上input框的name属性名称必须等于pojo的属性名称
	@RequestMapping("/updateitem")
	//public String update(Integer id, String name, Float price, String detail) throws Exception{
	public String update(MultipartFile pictureFile ,Items item, Model model, HttpServletRequest request) throws Exception{
		if(pictureFile.getSize()>0){
			//1. 获取图片完整名称
			String fileStr = pictureFile.getOriginalFilename();
			//2. 使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名
			String newfileName = UUID.randomUUID().toString() + fileStr.substring(fileStr.lastIndexOf("."));
			//3. 将图片保存到硬盘
			pictureFile.transferTo(new File("E:\\image\\" + newfileName));
			//4.将图片名称保存到数据库
			item.setPic(newfileName);
		}

		itmeService.updateItems(item);
		//返回数据
		//request.setAttribute("", arg1);
		//指定返回的页面(如果controller方法返回值为void,则不走springMvc组件,所以要写页面的完整路径名称)
		//request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);

		//重定向:浏览器中url发生改变,request域中的数据不可以带到重定向后的方法中
		//model.addAttribute("id", items.getId());
		//在springMvc中凡是以redirect:字符串开头的都为重定向
		//return "redirect:itemEdit/"+items.getId();
		//	return "redirect:/items/list";

		return "redirect:list";

		//请求转发:浏览器中url不发生改变,request域中的数据可以带到转发后的方法中
		//model.addAttribute("id", items.getId());
		//spirngMvc中请求转发:返回的字符串以forward:开头的都是请求转发,
		//后面forward:itemEdit.action表示相对路径,相对路径就是相对于当前目录,当前为类上面指定的items目录.在当前目录下可以使用相对路径随意跳转到某个方法中
		//后面forward:/itemEdit.action路径中以斜杠开头的为绝对路径,绝对路径从项目名后面开始算
		//return "forward:/items/itemEdit.action";
	}

	//如果Controller中接收的是Vo,那么页面上input框的name属性值要等于vo的属性.属性.属性.....
	@RequestMapping("/search")
	public String search(QueryVo vo) throws Exception{
		log.info(vo);
		return "";
	}

	@RequestMapping("/add")
	public String add(Items item) throws Exception{
		return "addItem";
	}

	@RequestMapping("/save")
	public String save(MultipartFile pictureFile,Items item) throws Exception{
		if(pictureFile.getSize()>0){
			//1. 获取图片完整名称
			String fileStr = pictureFile.getOriginalFilename();
			//2. 使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名
			String newfileName = UUID.randomUUID().toString() + fileStr.substring(fileStr.lastIndexOf("."));
			//3. 将图片保存到硬盘
			pictureFile.transferTo(new File("E:\\image\\" + newfileName));
			//4.将图片名称保存到数据库
			item.setPic(newfileName);
		}
		String businesskey = itmeService.insertItems(item);
//		QueryVo vo = new QueryVo();
//		vo.setItems(items);
//		this.search(vo);
		Map<String,Object> variables = new HashMap<String,Object>();
		processOperateService.createProcessInstance("helloworldMyProcess", businesskey, variables);
		MailVO mailVo =new MailVO();
		mailVo.setSubject(item.getName());
		mailVo.setContent(item.getDetail());
		baseMailService.sendMail(mailVo);
		  /*class MyTimerTask  extends   TimerTask{

				@Override
				public void run() {

					try {
						count = (count+1)%2;
						MailVO mailVo =new MailVO();
						mailVo.setSubject("证件办理缴费通知");
						mailVo.setContent("你好，你已成功缴费，证件办理在进一步办理审核中。");
						baseMailService.sendMail(mailVo);
						log.info("MyTimerTask+++++++++++"+new Date().getSeconds());
						new Timer().schedule(new MyTimerTask(), 2000+2000*count);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			};
		new Timer().schedule(new MyTimerTask(), 2000);*/
		return "redirect:/items/list";
	}

	@RequestMapping("/delAll")
	public String delAll(QueryVo vo) throws Exception{
		//如果批量删除,一堆input复选框,那么可以提交数组.(只有input复选框被选中的时候才能提交)
		log.info(vo);
		return "";
	}

	@RequestMapping("/updateAll")
	public String updateAll(QueryVo vo) throws Exception{
		log.info(vo);
		return "itemList";
	}

	//导入jackson的jar包在 controller的方法中可以使用@RequestBody,让spirngMvc将json格式字符串自动转换成java中的pojo
	//页面json的key要等于java中pojo的属性名称
	//controller方法返回pojo类型的对象并且用@ResponseBody注解,springMvc会自动将pojo对象转换成json格式字符串
	@RequestMapping("/sendJson")
	@ResponseBody
	public Items json(@RequestBody Items items) throws Exception{

		StringBuffer taskIds =new StringBuffer("完成任务：任务ID=");
		String taskId= null;
		//与正在执行的任务管理相关的Service
		List<Task> list =taskService.createTaskQuery().list();
		if(list!=null && list.size()>0){
			for(Task task:list){
				taskId = task.getId();
				//与正在执行的任务管理相关的Service
				taskOperateService.complete(taskId);
				log.info("完成任务：任务ID："+taskId);
				taskIds.append(taskId);
				taskIds.append("|");
			}
		}
		//	items = itmeService.list().get(0);
		items.setName(taskIds.toString());
		return items;
	}
	public void test1(){};
}
