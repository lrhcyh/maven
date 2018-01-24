package com.abcft.business.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.abcft.common.core.BaseController;
import com.abcft.common.core.UserSession;
import com.abcft.common.util.AliyunUtils;
import com.abcft.common.util.ControllerUtil;
import com.abcft.common.util.DateUtils;
import com.abcft.common.util.HTTPClientService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Demo Controller(控制层)
 * @author inning
 * @DateTime 2015-7-20 上午11:31:59
 *
 */
@Controller
@SessionAttributes({ "userSession" })
@RequestMapping("/demo")
public class DemoController extends BaseController {
	
	public Demo demo;
	
	public UserInfo userinfo;
	
	/**自动注入*/
	@Autowired
	private DemoDao demoDao;
	
	@Autowired
	private BaseRepository chartDao;
	
	/**
	 * 跳转到新增或修改的页面 
	 * 
	 * */
	@RequestMapping("/goToPage")
	public ModelAndView goToPage(HttpServletRequest request) {
		ModelAndView mv = null;
		String goToPage=request.getParameter("goToPage");
		String id=request.getParameter("id");
		//修改
		if (goToPage.equals("update")) {
			//此为对应返回的jsp页面路径
			mv = new ModelAndView("selfdemo/edit");
			userinfo = demoDao.queryDemoByid(id);
			//将数据bean放入到容器的request中
			mv.addObject("userinfo", userinfo);
			//新增
		} else if (goToPage.equals("insert")) {
			mv = new ModelAndView("selfdemo/add");
		} 
		return mv;
	}
	
	/**
	 * 新增功能
	 * 
	 * */
	@RequestMapping("/insert")
	public void insert(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "0") String data) throws Exception {
		//将页面传入的数据json格式，解析成java bean的get set方法。
		Gson oGson = new GsonBuilder().create();
		Demo demo = oGson.fromJson(data, Demo.class);
		demo.setDemodate(DateUtils.convertTimeToStr(new Date()));
		//返回插入的主键ID，请参照demo的配置
		Long id = this.demoDao.insertDemo(demo);
		//获取用户session
		UserSession user = (UserSession)request.getSession().getAttribute("user");
		//写入日志
//		adminLogDao.saveLog(user.getUserId(), "测试DOMO插入数据"+id);
		//将结果以json的格式返回给页面，通过response
		ControllerUtil.toJson(response, "success");
	}
	
	/**
	 * 修改功能
	 * 
	 * */
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "0") String data) throws Exception {
		//将页面传入的数据json格式，解析成java bean的get set方法。
		Gson oGson = new GsonBuilder().create();
		UserInfo userInfo = oGson.fromJson(data, UserInfo.class);
		
		//update返回更新的记录数量
		//if(this.demoDao.updateDemo(userInfo)>0){
		if(demoDao.updateFreeUserAmouont(userInfo)>0){
			//将结果以json的格式返回给页面，通过response
			ControllerUtil.toJson(response, "success");
		}
	}
	
	
	
	/**
	 * 自己测试查询demo数据
	 * @param params
	 * @author 
	 */
	@RequestMapping("/selfQueryList")
	public ModelAndView selfQueryList(@ModelAttribute UserInfo demo){
		ModelAndView mv = new ModelAndView("selfdemo/list");
		try {
			List<UserInfo> demoList = demoDao.queryDemoList(demo);
			mv.addObject("list", demoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	
	@RequestMapping("/queryList")
	public void adminList(@ModelAttribute UserInfo userInfo,HttpServletRequest request, HttpServletResponse response,@RequestParam("page") Integer pageNo,
			@RequestParam("rows") Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", "ERROR");
		try {
			userInfo.setCurPage(pageNo);
			userInfo.setPageRows(pageSize);
			List<UserInfo> demoList = demoDao.queryDemoList(userInfo);
			
			
			ControllerUtil.pageListToJson(response, demoList,userInfo.getTotalRows(),"","");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	
	@RequestMapping("/findTest")
	public void findTest(HttpServletRequest request,HttpServletResponse response){
		/* List<UserModel> userModels = userDao.findAll();  
		 for (UserModel userModel : userModels) {
			System.out.println(userModel.getSrc_id());
			System.out.println(userModel.getIndustry());
			System.out.println(userModel.getTitle());
		
			
		}*/
		 
	}
	
	
	
	/**
	 * 删除demo数据
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam(defaultValue = "0") Long id) throws Exception {
		try {
			if(this.demoDao.deleteDemo(id)>0){
				ControllerUtil.toJson(response,  "success");
			}else{
				ControllerUtil.toJson(response,  "error_01");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/importPDF")
	public void importPDF(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		try {
			if(!file.isEmpty()){ 
				
				String fileRealName = file.getOriginalFilename();                   //获得原始文件名;  
                int pointIndex =  fileRealName.indexOf(".");                        //点号的位置       
                String fileSuffix = fileRealName.substring(pointIndex);             //截取文件后缀  
                UUID FileId = UUID.randomUUID();               
                String savedFileName = FileId.toString().replace("-","").concat(fileSuffix);       //文件存取名  
                String savedDir = request.getSession().getServletContext().getRealPath("tempPdf"); //获取服务器指定文件存取路径    
                File savedFile = new File(savedDir, savedFileName);  
                boolean isCreateSuccess = savedFile.createNewFile();  
                if(isCreateSuccess){                      
                    file.transferTo(savedFile);  // 保存文件到Tomcat  
                }  
                
                FileInputStream input = new FileInputStream(savedFile);
                int statusCode = AliyunUtils.doPost(input, savedFile);
                System.out.println("statusCode=" + statusCode);
                
                String data = HTTPClientService.sendPost("http://localhost:8080/PdfServer/servlet/InitPvServlet", "name=" + savedDir + "\\" + savedFileName);
                PDFParseUtils.pdfParse(data, savedFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}