package ${packagePre}.admin.web.controller;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ${packagePre}.admin.service.${className}Service;
import ${packagePre}.commons.response.DataTablesResponse;
import ${packagePre}.commons.response.Response;
import ${packagePre}.dao.jpa.entity.${className};

/**
 * @author  generator auto create</br>
 */
@RequestMapping("/${controllerUrl}")
@Controller
public class ${className}Controller extends BaseController{

	@Autowired
	private ${className}Service ${moduleCode}Service;
	
	/**
	 * 打开维护页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "${controllerUrl}/index";
	}
	
	/**
	 * 
	 * @param ${moduleCode}
	 * @param draw
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/condition", method = RequestMethod.POST)
	@ResponseBody
	public DataTablesResponse findByCondition(${className} ${moduleCode},  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createStartTime,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date createEndTime, Integer draw, @RequestParam("start") Integer firstIndex, 
			@RequestParam("length") Integer pageSize) {
		Page<${className}> ${moduleCode}List = ${moduleCode}Service.find${className}List(${moduleCode}, firstIndex / pageSize + 1, pageSize);
		return DataTablesResponse.format(draw, ${moduleCode}List);
	}
	
	/**
	 * 打开新增页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("operateType", "add");
		model.addAttribute("id", UUID.randomUUID().toString());
		return "${controllerUrl}/add-edit";
	}
	
	/**
	 * 打开修改页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable String id) {
		${className} ${moduleCode} = ${moduleCode}Service.findById(id);
		model.addAttribute("${moduleCode}", ${moduleCode});
		model.addAttribute("operateType", "edit");
		model.addAttribute("id", ${moduleCode}.getId());
		return "${controllerUrl}/add-edit";
	}
	
	/**
	 * 查看明细
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable String id) {
		model.addAttribute("${moduleCode}", ${moduleCode}Service.findById(id));
		return "${controllerUrl}/view";
	}
	
	/**
	 * 保存
	 * @param ${moduleCode}
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Response save${className}(${className} ${moduleCode}) {
		${className} saved${className} = ${moduleCode}Service.save${className}(${moduleCode});
		return Response.success(saved${className});
	}
	
	@RequestMapping(value = "/find${className}ById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Response find${className}ById(@PathVariable("id")String id) {
		${className} ${moduleCode} = ${moduleCode}Service.findById(id);
		return Response.success(${moduleCode});
	}
	
	/**
	 * 修改
	 * @param ${moduleCode}
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Response edit${className}(${className} ${moduleCode}) {
		${className} saved${className} = ${moduleCode}Service.edit${className}(${moduleCode});
		return Response.success(saved${className});
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Response del${className}(@PathVariable String id) {
		${moduleCode}Service.del${className}(id);
		return Response.success();
	}
}
