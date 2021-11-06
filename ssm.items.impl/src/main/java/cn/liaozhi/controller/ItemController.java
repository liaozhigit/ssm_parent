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
import java.text.SimpleDateFormat;
import java.util.Date;
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

	//public static final String IMG_PATH ="D:\\javaData\\ssm\\ssm_parent\\images\\";
	public static final String IMG_PATH ="E:\\image\\";
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
		ModelAndView modelAndView = new ModelAndView();

		try {
			log.info("========list start==================");
			List<Items> list = itmeService.list();
			modelAndView.addObject("itemList", list);
			modelAndView.setViewName("itemList");
			log.info("========list end ==================");
		}catch (Exception e){
			e.printStackTrace();
            log.error(e);
		}
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
	public ModelAndView itemEdit(@PathVariable("id") Integer id, HttpServletRequest reuqest,
						   Model model) throws Exception{
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("editItem");
		//String idStr = reuqest.getParameter("id");
		Items item = itmeService.findItemsById(id);
		
		  mv.addObject("id",item.getId());
		   mv.addObject("name",item.getName());
		   mv.addObject("price",item.getPrice());
		   mv.addObject("pic",item.getPic());
		   mv.addObject("detail",item.getDetail());
		   Date createtime = item.getCreatetime();
		   mv.addObject("createtime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(createtime));
		   String img_name = "/pic/"+item.getPic();
		   mv.addObject("img_name",img_name); 
		   return mv;
		

		//Model模型:模型中放入了返回给页面的数据
		//model底层其实就是用的request域来传递数据,但是对request域进行了扩展.
		//model.addAttribute("item", item);

		//如果springMvc方法返回一个简单的string字符串,那么springMvc就会认为这个字符串就是页面的名称
	//	return "editItem";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, HttpServletRequest reuqest,
						   Model model) throws Exception{
		    itmeService.delete(id);
		   return "redirect:/items/list";

		
	}
	

	//springMvc可以直接接收基本数据类型,包括string.spirngMvc可以帮你自动进行类型转换.
	//controller方法接收的参数的变量名称必须要等于页面上input框的name属性值

	//spirngMvc可以直接接收pojo类型:要求页面上input框的name属性名称必须等于pojo的属性名称
	@RequestMapping("/updateitem")
	//public String update(Integer id, String name, Float price, String detail) throws Exception{
	public String update(MultipartFile pictureFile ,Items item, Model model, HttpServletRequest request) throws Exception{
		if(pictureFile.getSize()>0){
			/**
1.后台获取待上传图片的保存路径
（1）在WebContent（使用eclipse的情况下，若使用的IDE为MyEclipse，则为WebRoot）下创建一个保存图片的文件夹，
如：uploadPic，待web项目在服务器上启动后，服务器上就会有对应的文件夹目录存在。
（2）为处理图片上传的方法给定一个参数：request，参数类型为：HttpServletRequest
（3）在处理图片上传的方法中用以下方式获取服务器中保存图片的文件夹路径：
			 */
			String pic_path = request.getSession().getServletContext().getRealPath("uploadPic");  
			log.info("updateitem pic_path=========================="+pic_path);
			//1. 获取图片完整名称
			String fileStr = pictureFile.getOriginalFilename();
			//2. 使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名
			String newfileName = UUID.randomUUID().toString() + fileStr.substring(fileStr.lastIndexOf("."));
			//3. 将图片保存到硬盘
			pictureFile.transferTo(new File(pic_path +"/"+ newfileName));
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
	public ModelAndView search(Items item) throws Exception{
		
		List<Items> itemList =itmeService.findItems(item);

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("itemList", itemList);
		modelAndView.setViewName("itemList");
		return modelAndView;
	}
	
	@RequestMapping("/updateAll")
	public String updateAll(QueryVo vo) throws Exception{
		log.info(vo);
		return "itemList";
	}

	@RequestMapping("/add")
	public String add(Items item) throws Exception{
		return "addItem";
	}
	
	@RequestMapping("/save")
	public  String save(MultipartFile pictureFile,HttpServletRequest request,Items item) throws Exception{

		String pic_path = request.getSession().getServletContext().getRealPath("uploadPic");
		log.info("======save  start======================");
		log.info("save pic_path=========================="+pic_path);
			//获取上传文件的原始名称
			String originalFilename = pictureFile.getOriginalFilename();
			// 上传图片
			if ( originalFilename != null && originalFilename.length() > 0) {
					//2. 使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名
					String newfileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
					//3. 将图片保存到硬盘
					pictureFile.transferTo(new File(pic_path +"/"+ newfileName));
					//4.将图片名称保存到数据库
					item.setPic(newfileName);
			}
			String businesskey = itmeService.insertItems(item);
//			QueryVo vo = new QueryVo();
//			vo.setItems(item);
//			this.search(vo);
//			Map<String,Object> variables = new HashMap<String,Object>();
//			processOperateService.createProcessInstance("helloworld", businesskey, variables);
//			MailVO mailVo =new MailVO();
//			mailVo.setSubject(item.getName());
//			mailVo.setContent(item.getDetail());
//			baseMailService.sendMail(mailVo);
		log.info("======save  end=====================");
			return "redirect:/items/list";
		
	}
	


	public String uploadFile(MultipartFile pictureFile,Items item) throws Exception{
		if(pictureFile.getSize()>0){
			//1. 获取图片完整名称
			String fileStr = pictureFile.getOriginalFilename();
			//2. 使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名
			String newfileName = UUID.randomUUID().toString() + fileStr.substring(fileStr.lastIndexOf("."));
			//3. 将图片保存到硬盘
			pictureFile.transferTo(new File(IMG_PATH + newfileName));
			//4.将图片名称保存到数据库
			item.setPic(newfileName);
		}
		String businesskey = itmeService.insertItems(item);
//		QueryVo vo = new QueryVo();
//		vo.setItems(item);
//		this.search(vo);
//		Map<String,Object> variables = new HashMap<String,Object>();
//		processOperateService.createProcessInstance("helloworld", businesskey, variables);
//		MailVO mailVo =new MailVO();
//		mailVo.setSubject(item.getName());
//		mailVo.setContent(item.getDetail());
//		baseMailService.sendMail(mailVo);
		return "redirect:/items/list";
	}

	@RequestMapping("/delAll")
	public String delAll(QueryVo vo) throws Exception{
		//如果批量删除,一堆input复选框,那么可以提交数组.(只有input复选框被选中的时候才能提交)
		log.info(vo);
		return "";
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

}
